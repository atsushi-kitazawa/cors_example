package com.example.cors;

import java.io.IOException;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.example.cors.exception.DefaultErrorHelper;
import com.example.cors.exception.DefaultErrorHelper.ResponseMediaType;

public class HttpServerFactory {
	protected static HttpServer createHttpServer() throws IOException {
		ResourceConfig resourceConfig = new ResourceConfig()
				.packages("com.example.cors.resource")
				.packages("com.example.cors.filter")
				.register(JacksonFeature.class);

		System.out.println("Starting grizzly2...");
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(
				ServerConfig.getBaseURI(), resourceConfig, false);
		server.getServerConfiguration()
				.setDefaultErrorPageGenerator(DefaultErrorHelper
						.getDefaultErrorResponse(ResponseMediaType.JSON));
		return server;
	}
}
