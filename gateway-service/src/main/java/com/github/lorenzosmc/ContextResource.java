package com.github.lorenzosmc;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.lorenzosmc.common.annotations.jaxrs.Secured;
import com.github.lorenzosmc.common.annotations.jaxrs.Verified;
import com.github.lorenzosmc.common.dto.context.inbound.ContextCreationDto;
import com.github.lorenzosmc.common.dto.context.inbound.ContextParticipationCreationDto;
import com.github.lorenzosmc.common.dto.context.outbound.ContextDto;
import com.github.lorenzosmc.common.dto.project.inbound.TaskCreationDto;
import com.github.lorenzosmc.common.dto.project.outbound.TaskDto;
import com.github.lorenzosmc.common.dto.user.outbound.UserDto;
import com.github.lorenzosmc.common.dto.workgroup.inbound.WorkgroupCreationDto;
import com.github.lorenzosmc.common.dto.workgroup.inbound.WorkgroupQueryDto;
import com.github.lorenzosmc.common.dto.workgroup.outbound.WorkgroupDto;
import com.github.lorenzosmc.common.dto.workgroup.outbound.WorkgroupStatusDto;
import com.github.lorenzosmc.common.exception.IllegalUpdateException;
import com.github.lorenzosmc.common.service.ContextService;
import com.github.lorenzosmc.common.service.ProjectService;
import com.github.lorenzosmc.common.service.UserService;
import com.github.lorenzosmc.common.service.WorkgroupService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

import java.util.List;
import java.util.UUID;

@Path("/contexts")
public class ContextResource {
	@Context 
	SecurityContext securityContext;
	
	@Inject
	UserService userService;

	@Inject
	ContextService contextService;
	
	@Inject
	ProjectService projectService;
	
	@Inject
	WorkgroupService workgroupService;
		
	//TODO listContexts() with filters
	
	@POST
	@Secured
	@RolesAllowed("PROFESSOR")
	@Verified
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createContext(ContextCreationDto contextDto) {
		//TODO input validation
		
		//FIXME redundant call to userService
//		String username = securityContext.getUserPrincipal().getName();
//		UserDto userDto = userService.findByUsername(username);
//		UUID userId = userDto.getUuid();

		ContextDto createdContextDto = contextService.createContext(contextDto);
		
		//TODO HATEOAS, list endpoints to:
		//get list of tasks of context
		//add task to context
		//remove task from context
		//add collaborator to context
		//get creator detail
		//...
		return Response
				.status(Response.Status.CREATED)
				.entity(createdContextDto)
				.build();
	}
	
	//TODO retrieveContext()
	
	@PATCH
	@Path("/{contextUuid}")
	@Secured
	@RolesAllowed("PROFESSOR")
	@Verified
	//FIXME no requirement for Content-Type as "application/json-patch+json" in request, 
	//  if 	@Consumes(MediaType.APPLICATION_JSON_PATCH_JSON), then RESTeasy automatically patches
	//https://stackoverflow.com/questions/59051361/is-there-a-way-to-disable-automatic-json-patch-application-in-wildfly
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateContext(@PathParam(value = "contextUuid") UUID contextUuid, JsonPatch jsonPatch) {
		//TODO input validation

		//FIXME redundant call to userService
		UserDto userDto = userService.findByUsername(securityContext.getUserPrincipal().getName());
		UUID userUuid = userDto.getUuid();
		
		
		ContextDto storedContextDto = contextService.findContextByUuid(contextUuid);
		if(storedContextDto == null)
			return Response.status(Response.Status.NOT_FOUND).build();
		
		boolean isProfessorOfContext = contextService.isUserProfessorOfContext(userUuid, contextUuid);
		if(!isProfessorOfContext)
			return Response.status(Response.Status.FORBIDDEN).entity("user is not professor of context").build();
		
		
		ContextDto patchedContextDto, updatedContextDto;
		try {
			patchedContextDto = patchContextDto(storedContextDto, jsonPatch);
			updatedContextDto = contextService.updateContext(contextUuid, patchedContextDto);
		} catch (JsonPatchException e) {return Response.status(Response.Status.BAD_REQUEST).entity("Malformed json-patch").build();
		} catch (IllegalUpdateException e) {return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();}
				
		return Response
				.status(Response.Status.OK)
				.entity(updatedContextDto)
				.build();
	}

	
	@PATCH
	@Path("/{contextUuid}/participants")
	@Secured
	@RolesAllowed({"STUDENT", "COLLABORATOR"})
	@Verified
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addParticipant(@PathParam("contextUuid") String contextUuid, ContextParticipationCreationDto participationDto) {
		//TODO input validation
		
		
		boolean correctPassword = contextService.verifyCredentials(participationDto.getPassword());
		if(!correctPassword )
			return Response.status(Response.Status.FORBIDDEN).entity("incorrect credentials").build();
		
//		contextService.
		
		return null;
	}
	
	@POST
	@Path("/{contextUuid}/tasks")
	@Secured
	@RolesAllowed({"PROFESSOR", "STUDENT"})
	@Verified
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createTask(@Valid TaskCreationDto taskDto) {
		//TODO input validation
		
		//FIXME redundant call to userService
		UserDto userDto = userService.findByUsername(securityContext.getUserPrincipal().getName());
		UUID userId = userDto.getUuid();
		String role = userDto.getRole();
		UUID contextUuid = taskDto.getContextUuid();
		
		boolean contextExists = contextService.findContextByUuid(contextUuid) == null;
		if(!contextExists)
			return Response.status(Response.Status.NOT_FOUND).build();
		
		if(role.equals("PROFESSOR")) {
			//FIXME redundant call (use creatorUuid)
			boolean isProfessorOfContext = contextService.isUserProfessorOfContext(userId, contextUuid);
			if(!isProfessorOfContext)
				return Response.status(Response.Status.FORBIDDEN).entity("user is not professor of context").build();
		}else if (role.equals("STUDENT")){
			//FIXME ask contextService.isUserLeaderInContext instead
			WorkgroupQueryDto query = new WorkgroupQueryDto();
			query.setContextUuid(contextUuid);
			query.setCreatorUuid(userId);
			query.setVerified(true);
			query.setStatus(WorkgroupStatusDto.NOT_WORKING);
			List<WorkgroupDto> studentWorkgroups = workgroupService.findAll(query);
		
			if(studentWorkgroups == null || studentWorkgroups.isEmpty())
				return Response.status(Response.Status.FORBIDDEN).entity("user is not leader in any workgroup of context").build();
		}
		
		
		TaskDto createdTask = projectService.createTask(taskDto, contextUuid, userId);
				
		return Response
				.status(Response.Status.CREATED)
				.entity(createdTask)
				.build();
	}
	
	@POST
	@Path("/{contextUuid}/workgroups")
	@Secured
	@RolesAllowed("STUDENT")
	@Verified
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createWorkgroup(@PathParam("contextUuid") UUID contextUuid, WorkgroupCreationDto workgroupDto) {
		//TODO input validation
		
		//FIXME redundant call to userService
		UserDto userDto = userService.findByUsername(securityContext.getUserPrincipal().getName());
		UUID userUuid = userDto.getUuid();
		String role = userDto.getRole();

		if(contextService.findContextByUuid(contextUuid) == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		
		boolean isParticipantOfContext = contextService.isUserParticipantOfContext(userUuid, contextUuid);
		if(!isParticipantOfContext) 
			return Response.status(Response.Status.FORBIDDEN).entity("user is not participant of context").build();
		
		
		WorkgroupDto createdWorkgroupDto = workgroupService.create(workgroupDto, contextUuid, userUuid);
		
		return Response
				.status(Response.Status.CREATED)
				.entity(createdWorkgroupDto)
				.build();
	}
	
	@GET
	@Path("/{contextUuid}/workgroups")
	@Secured
	@RolesAllowed({"ADMIN", "PROFESSOR", "COLLABORATOR", "STUDENT"})
	@Produces(MediaType.APPLICATION_JSON)
	public Response listWorkgroups(@PathParam("contextUuid") UUID contextUuid, @BeanParam WorkgroupQueryDto query) {
		//TODO input validation
		query.setContextUuid(contextUuid);

		//FIXME redundant call to userService
		String username = securityContext.getUserPrincipal().getName();
		UserDto userDto = userService.findByUsername(username);
		UUID userUuid = userDto.getUuid();

		if(securityContext.isUserInRole("PROFESSOR")) {
			boolean isProfessorOfContext = contextService.isUserProfessorOfContext(userUuid, query.getContextUuid());
			if(!isProfessorOfContext)
				return Response.status(Response.Status.FORBIDDEN).entity("user is not professor of context").build();
		}else if(securityContext.isUserInRole("COLLABORATOR")){
			boolean isInContext = contextService.isUserParticipantOfContext(userUuid, query.getContextUuid());
			if(!isInContext)
				return Response.status(Response.Status.FORBIDDEN).entity("user is not participant of context").build();
		}else if(securityContext.isUserInRole("STUDENT")) {
			boolean isInWorkgroup = userUuid.equals(query.getMemberUuid()) || userUuid.equals(query.getContextUuid());
			if(!isInWorkgroup)
				return Response.status(Response.Status.FORBIDDEN).entity("user is not participant of workgroup").build();
		}
		
		
		List<WorkgroupDto> workgroupDtos = workgroupService.findAll(query);
		
		return Response
				.status(Response.Status.OK)
				.entity(workgroupDtos)
				.build();
	}
	
	//TODO joinContext(with credentials)
	
	private ContextDto patchContextDto(ContextDto contextDto, JsonPatch jsonPatch) throws JsonPatchException {
		//TODO input validation
		//FIXME reuse ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();
		
		ContextDto tmpContextDto = new ContextDto(contextDto);
		JsonNode tmpContextJson = objectMapper.valueToTree(tmpContextDto);
		JsonNode updatedContextJson = jsonPatch.apply(tmpContextJson);
		ContextDto updatedContextDto = null;
		try {
			updatedContextDto = objectMapper.convertValue(updatedContextJson, ContextDto.class);
		}catch(IllegalArgumentException e) {e.printStackTrace();}
		
		return updatedContextDto;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
