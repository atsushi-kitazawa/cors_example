package com.example.cors.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(1001)
public class CorsFilter
		implements ContainerRequestFilter, ContainerResponseFilter {

	private static final String OPTION_METHOD = "OPTIONS";

	private static final String ORIGIN_HEADER = "Origin";
	private static final String ACCESS_CONTROL_ALLOW_ORIGIN_HEADER = "Access-Control-Allow-Origin";
	private static final String ACCESS_CONTROL_ALLOW_METHOD_HEADER = "Access-Control-Allow-Method";
	private static final String ACCESS_CONTROL_ALLOW_HEADERS_HEADER = "Access-Control-Allow-Headers";
	private static final String ACCESS_CONTROL_REQUEST_METHOD_HEADER = "Access-Control-Request-Method";
	private static final String ACCESS_CONTROL_REQUEST_HEADERS_HEADER = "Access-Control-Request-Headers";

	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {

		String method = requestContext.getMethod();
		MultivaluedMap<String, String> reqHeaders = requestContext.getHeaders();

		// preflight request
		if (OPTION_METHOD.equals(method) && reqHeaders.keySet()
				.containsAll(Arrays.asList(ORIGIN_HEADER,
						ACCESS_CONTROL_REQUEST_METHOD_HEADER,
						ACCESS_CONTROL_REQUEST_HEADERS_HEADER))) {

			Response response = Response.status(Status.NO_CONTENT)
					.header(ACCESS_CONTROL_ALLOW_ORIGIN_HEADER,
							reqHeaders.get(ORIGIN_HEADER))
					.header(ACCESS_CONTROL_ALLOW_METHOD_HEADER, "POST")
					.header(ACCESS_CONTROL_ALLOW_HEADERS_HEADER, "X-PINGOTHER")
					.build();
			requestContext.abortWith(response);
		}
	}

	@Override
	public void filter(ContainerRequestContext requestContext,
			ContainerResponseContext responseContext) throws IOException {

		MultivaluedMap<String, String> reqHeaders = requestContext.getHeaders();
		if (reqHeaders.keySet().contains(ORIGIN_HEADER)) {
			responseContext.getHeaders().add(ACCESS_CONTROL_ALLOW_ORIGIN_HEADER,
					"*");
		}

	}
}
