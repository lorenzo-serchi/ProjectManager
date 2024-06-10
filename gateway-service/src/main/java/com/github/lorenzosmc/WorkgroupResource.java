package com.github.lorenzosmc;

import com.github.lorenzosmc.common.annotations.jaxrs.Secured;
import com.github.lorenzosmc.common.dto.workgroup.inbound.WorkgroupCreationDto;
import com.github.lorenzosmc.common.dto.workgroup.inbound.WorkgroupQueryDto;
import com.github.lorenzosmc.common.dto.workgroup.outbound.WorkgroupDto;
import com.github.lorenzosmc.common.service.ContextService;
import com.github.lorenzosmc.common.service.UserService;
import com.github.lorenzosmc.common.service.WorkgroupService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/workgroups")
public class WorkgroupResource {

	@Inject
	UserService userService;
	
	@Inject
	ContextService contextService;
	
	@Inject
	WorkgroupService workgroupService;
	
	@GET
	@Secured
	@RolesAllowed({"ADMIN"})
	@Produces(MediaType.APPLICATION_JSON)
	public Response listWorkgroups(@BeanParam WorkgroupQueryDto query) {
//		TODO input validation
		List<WorkgroupDto> workgroupDtos = workgroupService.findAll(query);
		
		return Response
				.status(Response.Status.OK)
				.entity(workgroupDtos)
				.build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(WorkgroupCreationDto workgroupDto) {
		//TODO input validation 
		
		
		return null;
	}
	
	@GET
	@Path("/{workgroupUuid}")
	@Produces(MediaType.APPLICATION_JSON)
	public WorkgroupDto retrieveWorkgroup(@PathParam(value = "workgroupUuid") String workgroupUuid) {
		//TODO input validation
		//TODO implementv
		//TODO check if workgroup exists? 404 if it doesnt
		return null;
	}

	@PATCH
	@Path("/{workgroupUuid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response partialUpdateWorkgroup(@PathParam(value = "workgroupUuid") String workgroupUuid, WorkgroupDto workgroupDto) {
		//TODO input validation
		//TODO check if workgroup exists first? 404 if it doesnt
		//TODO implement
		return null;
	}
	
	//TODO addMember() POST /{workgroupId}/members

	//TODO removeMember() DELETE /{workgroupId}/members/{memberId}
}
