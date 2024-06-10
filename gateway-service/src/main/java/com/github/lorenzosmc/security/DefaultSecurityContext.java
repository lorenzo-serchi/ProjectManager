package com.github.lorenzosmc.security;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.SecurityContext;

import java.security.Principal;

//FIXME what is the naming convention?
public class DefaultSecurityContext implements SecurityContext {
	private String principalUsername;
	private String principalRole;
	
	private ContainerRequestContext requestContext;

	public DefaultSecurityContext() {}
	
	public DefaultSecurityContext(String principalUsername, String principalRole, ContainerRequestContext requestContext) {
		this.principalUsername = principalUsername;
		this.principalRole = principalRole;
		this.requestContext = requestContext;
	}

	@Override
	public Principal getUserPrincipal() {
		return new Principal() {

			@Override
			public String getName() {
				return principalUsername;
			}
			
		};
	}

	@Override
	public boolean isUserInRole(String role) {
		if(principalUsername == null || !principalRole.equals(role))
			return false;

		return true;
	}

	@Override
	public boolean isSecure() {
		return requestContext.getSecurityContext().isSecure();
	}

	@Override
	public String getAuthenticationScheme() {
		return SecurityContext.BASIC_AUTH;
	}
}
