package com.github.lorenzosmc;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.lorenzosmc.common.annotations.jaxrs.Secured;
import com.github.lorenzosmc.common.dto.project.inbound.ProjectCreationDto;
import com.github.lorenzosmc.common.dto.user.inbound.UserAuthenticationDto;
import com.github.lorenzosmc.common.dto.user.inbound.UserCreationDto;
import com.github.lorenzosmc.common.dto.user.inbound.UserQueryDto;
import com.github.lorenzosmc.common.dto.user.outbound.UserDto;
import com.github.lorenzosmc.common.exception.IllegalUpdateException;
import com.github.lorenzosmc.common.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Path("/users")
public class UserResource {
	@Context 
	SecurityContext securityContext;

	@Inject
	UserService userService;

	@GET
	@Secured
	@RolesAllowed("ADMIN")
	@Produces(MediaType.APPLICATION_JSON)
	//TODO add role filtering
	public Response listUsers(@BeanParam UserQueryDto query) {
		//TODO input validation
		
		List<UserDto> userDtos = userService.findAll(query);
			
		return Response
				.status(Response.Status.OK)
				.entity(userDtos)
				.build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	// TODO add input validation checks (e.g. null, no ["ADMIN", "ADMIN", ...], ...)
	public Response createUser(UserCreationDto userDto) {
		//TODO input validation (check if all fields are not null)
		UserDto createdUserDto;
		try {
			createdUserDto = userService.create(userDto);
		} catch (IllegalArgumentException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		// FIXME does this return a JWT? Probably...
		return Response.status(201).entity(createdUserDto).build();
	}

	@GET
	@Path("/{userUuid}")
	@Secured
	// not GUEST
	@RolesAllowed({ "ADMIN", "PROFESSOR", "COLLABORATOR", "STUDENT" })
	@Produces(MediaType.APPLICATION_JSON)
//	//TODO make another method for smaller UserDto (e.g. without profile picture, or make it so 
//	just an id is sent or something... perhaps HATEOAS a path/service to retrieve profile picture)
	public Response retrieveUser(@PathParam(value = "userUuid") UUID userUuid) {
		//TODO input validation
		
//		FIXME redundant call to userService
//		UserDto authenticatedUserDto = userService.findByUsername(securityContext.getUserPrincipal().getName());
//		Long authenticatedUserId = authenticatedUserDto.getUuid();
//		
//		FIXME can any user view any other user's UserDto?
//		if(!authenticatedUserId.equals(userId))
//			return Response.status(Response.Status.FORBIDDEN).entity("user cannot view other user's details").build();
		
		UserDto userDto = userService.findByUuid(userUuid);

		if(userDto == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		//TODO HATEOAS to
		// /users/{userId}/createdContexts
		// /users/{userId}/appointments
		return Response
				.status(Response.Status.OK)
				.entity(userDto)
				.build();
	}

	@PATCH
	@Path("/{userUuid}")
	@Secured
	// not GUEST
	@RolesAllowed({ "ADMIN", "PROFESSOR", "COLLABORATOR", "STUDENT" })
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	// FIXME don't allow frontend to do update without checks. Can break things.
	// FIXME do like in ContextEndpoint
	public Response updateUser(@PathParam(value = "userUuid") UUID userUuid, JsonPatch jsonPatch) {
		//TODO input validation
		UserDto storedUserDto = userService.findByUuid(userUuid);
		UserDto patchedUserDto, updatedUserDto;

		//TODO check if user exists first? 404 if it doesnt
		try {
			patchedUserDto = patchUserDto(storedUserDto, jsonPatch);
			updatedUserDto = userService.update(userUuid, patchedUserDto);
		} catch (JsonPatchException e) {return Response.status(Response.Status.BAD_REQUEST).entity("Malformed json-patch").build();
		} catch (IllegalUpdateException e) { return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();}

		return Response
				.status(Response.Status.OK)
				.entity(updatedUserDto)
				.build();
	}
	
	@GET
	@Path("/{userUuid}/workgroups")
	@Secured
	@RolesAllowed("STUDENT")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listWorkgroups(@PathParam("userUuid") String userUuid) {
		//TODO implement
		//use workgroupService.findAll() specifying userUuid as participantUuid
		return null;
	}
	
	@POST
	@Path("/{userUuid}/projects")
	@Secured
	@RolesAllowed("PROFESSOR")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createProject(@PathParam("userUuid") String userUuid) {
		//TODO implement
		//use projectService.createProject()
		return null;
	}
	
	@POST
	@Path("/{userUuid}/projects/{projectId}/tasks")
	@Secured
	@RolesAllowed("PROFESSOR")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addTask(ProjectCreationDto projectDto, @PathParam("userUuid") String userUuid) {
		//TODO implement
		//use projectService.addTaskToProject()
		return null;
	}
	

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	// FIXME align with OAuth 2.0
	// TODO figure out how the user uses the JWT... expiration, ...,
	// SecurityContext, etc...
	// TODO move to authenticationService
	public Response login(UserAuthenticationDto userDto) {
		//TODO input validation
		
		// FIXME is ForbiddenException the correct one to throw? Should an exception
		// even be thrown?
		if (!userService.verifyCredentials(userDto))
			throw new NotAuthorizedException("Invalid credentials");

		// FIXME change secret key (better way than to hardcode?)
		Algorithm algorithm = Algorithm.HMAC256("secretkey");

		// FIXME this is just a boilerplate JWT... personalize
		String jwt = JWT.create()
							.withClaim("username", userDto.getUsername())
							.withIssuedAt(new Date())
							.withJWTId(UUID.randomUUID().toString())
							.withExpiresAt(new Date(System.currentTimeMillis() + 50000L))
//							.withNotBefore(new Date(System.currentTimeMillis() + 1000L))
						.sign(algorithm);

		JsonObject jwtJson = Json.createObjectBuilder().add("jwt", jwt).build();

		return Response.ok().entity(jwtJson).build();
	}

	@GET
	@Path("/verifyjwt/{jwt}")
	// TODO move to another endpoint
	@Produces(MediaType.APPLICATION_JSON)
	// FIXME align with OAuth 2.0
	// FIXME this goes in userService to verify identity of user (?)
	// FIXME this is just for testing... is it needed other than for that?
	public Response verifyJwt(@PathParam(value = "jwt") String jwtToken) {
		//TODO input validation
		// FIXME change secret key to a harder secret to guess
		// FIXME better way than to hardcode?
		Algorithm algorithm = Algorithm.HMAC256("secretkey");

		// FIXME change issuer, and move to SecurityContext(?)
		JWTVerifier verifier = JWT.require(algorithm).build();

		String responseMessage;
		// FIXME deal with each individual exception
		try {
			verifier.verify(jwtToken);
			responseMessage = "Valid JWT";
		} catch (JWTVerificationException e) {
			System.out.println(e.getMessage());
			// FIXME better message (e.g. "The Token has expired on 2024-02-29T12:10:24Z."
			// is in stdout)
			responseMessage = "Invalid JWT";
		}

		return Response.ok().entity(responseMessage).build();
	}

	//TODO listContexts() GET /users/{userId}/contexts?created=true/false&participating=true/false
	
	//FIXME move into UserController
	private UserDto patchUserDto(UserDto userDto, JsonPatch jsonPatch) throws JsonPatchException {
		// FIXME reuse ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();

		//TODO check if user exists first? 404 if it doesnt
		UserDto tmpUserDto = new UserDto(userDto);
		JsonNode tmpUserJson = objectMapper.valueToTree(tmpUserDto);
		JsonNode updatedUserJson = jsonPatch.apply(tmpUserJson);
		UserDto updatedUserDto = objectMapper.convertValue(updatedUserJson, UserDto.class);

		return updatedUserDto;
	}
}
