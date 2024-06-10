package com.github.lorenzosmc.security;

import com.github.lorenzosmc.common.annotations.jaxrs.Verified;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Verified
@Provider
@Priority(Priorities.AUTHORIZATION)
public class UserVerificationRequestFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if(!requestContext.hasProperty("isVerified") || !(boolean) requestContext.getProperty("isVerified")){
			requestContext.abortWith(
					Response
							.status(Response.Status.UNAUTHORIZED)
							.entity("The user is not verified")
							.type("text/plain")
							.build()
			);
			return;
		}
	}

}
