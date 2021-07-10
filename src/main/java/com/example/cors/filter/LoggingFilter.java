package com.example.cors.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

@Provider
public class LoggingFilter
		implements ContainerRequestFilter, ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {

		MultivaluedMap<String, String> headers = requestContext.getHeaders();
		System.out.println("headers=" + headers);
		String method = requestContext.getMethod();
		System.out.println("method=" + method);
	}

	@Override
	public void filter(ContainerRequestContext requestContext,
			ContainerResponseContext responseContext) throws IOException {

		MultivaluedMap<String, Object> headers = responseContext.getHeaders();
		System.out.println("headers=" + headers);
		Object entity = responseContext.getEntity();
		System.out.println("body=" + entity);
	}
}
