package com.github.lorenzosmc.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lorenzosmc.common.annotations.jaxrs.Secured;
import com.github.lorenzosmc.common.dto.user.inbound.UserAuthenticationDto;
import com.github.lorenzosmc.common.dto.user.outbound.UserDto;
import com.github.lorenzosmc.common.service.UserService;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class SecurityRequestFilter implements ContainerRequestFilter {
	@Inject
	private UserService userService;
	
	public SecurityRequestFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		AuthenticationInfo authInfo = new AuthenticationInfo();
		if(!authInfo.extractFromAuthorizationHeader(authorizationHeader)) {
			requestContext.abortWith(
					Response
							.status(Response.Status.UNAUTHORIZED)
							.entity("This Request is UNAUTHORIZED")
							.type("text/plain")
							.build()
			);
			return;
		}
		
		String principalUsername, principalPassword = null;
		if(authInfo.isBasic() || authInfo.isBearer()) {
			if(authInfo.isBasic()) {
				String[] basicCredentials = authInfo.getCredentials().split(":", 2);
				principalUsername = basicCredentials[0];
				principalPassword = basicCredentials[1];
	
				UserAuthenticationDto userCredentials = new UserAuthenticationDto();
				userCredentials.setUsername(principalUsername);
				userCredentials.setPassword(principalPassword);
				if(!userService.verifyCredentials(userCredentials)) {
					requestContext.abortWith(
							Response
									.status(Response.Status.UNAUTHORIZED)
									.entity("This Request is UNAUTHORIZED")
									.type("text/plain")
									.build()
					);
					return;
				}
			}else{
				//FIXME KeyStore
				//FIXME how does this deal with wrong syntax for JWT?
				//FIXME deal with errors/exceptions
				//assumes JWT as token
				String jwt = authInfo.getCredentials();
				
				Algorithm algorithm = Algorithm.HMAC256("secretkey");
				JWTVerifier verifier = JWT.require(algorithm).build();
				DecodedJWT decodedJWT = verifier.verify(jwt);
				
				String base64Payload = decodedJWT.getPayload();
	 			byte[] bytes = Base64.getDecoder().decode(base64Payload);
				String payload = new String(bytes);
				ObjectMapper objectMapper= new ObjectMapper();
				JsonNode node = objectMapper.readTree(payload);
				//FIXME what if it doesn't
				if(!node.has("username")) {
					requestContext.abortWith(
							Response
									.status(Response.Status.BAD_REQUEST)
									.entity("No 'username' field in JWT!")
									.type("text/plain")
									.build()
					);
					return;
				}
				
				principalUsername = node.get("username").textValue();
			}
			
			UserDto userDto = userService.findByUsername(principalUsername);
			String principalRole = userDto.getRole();
			requestContext.setProperty("isVerified", userDto.isVerified()); 
			
			requestContext.setSecurityContext(new DefaultSecurityContext(principalUsername, principalRole, requestContext));
		}
		
	}
}

class AuthenticationInfo{
	private String schemeName;
	private String credentials;
	private static final String BASIC_AUTHENTICATION_SCHEME = "basic";
	private static final String BEARER_AUTHENTICATION_SCHEME = "bearer";
	
	//FIXME handle exceptions/errors
	public boolean extractFromAuthorizationHeader(String authorizationHeader) {
		boolean validHeader = false;

		if(authorizationHeader != null && !authorizationHeader.isEmpty()) {
			String[] tmp = authorizationHeader.split(" ", 2);
			
			String schemeName= tmp[0].toLowerCase();
			
			String credentials = tmp[1].trim();
			switch(schemeName) {
				case BASIC_AUTHENTICATION_SCHEME: 
					byte[] bytes = Base64.getDecoder().decode(credentials);
					this.credentials = new String(bytes, Charset.forName("UTF-8"));
					this.schemeName = schemeName;
					validHeader = true;
					break;
				case BEARER_AUTHENTICATION_SCHEME:
					//FIXME shouldn't token also be base64 encoded? Not jwt, because it uses base64url encoding already(?)
					this.schemeName = schemeName;
					this.credentials = credentials;
					validHeader = true;
					break;
			}
		}
		
		return validHeader;
	}
	
	public boolean isBasic() {
		return schemeName.equals(BASIC_AUTHENTICATION_SCHEME);
	}
	
	public boolean isBearer() {
		return schemeName.equals(BEARER_AUTHENTICATION_SCHEME);
	}
	
	public String getCredentials() {
		return credentials;
	}
	
	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}
	
	public String getSchemeName() {
		return schemeName;
	}
	
	public boolean setSchemeName(String schemeName) {
		boolean validSchemeName = false;
		
		if(schemeName.equals(BASIC_AUTHENTICATION_SCHEME) || schemeName.equals(BEARER_AUTHENTICATION_SCHEME)) {
			this.schemeName = schemeName;
			validSchemeName = true;
		}
		
		return validSchemeName;
	}
}

