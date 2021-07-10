
package com.example.cors;

import java.io.IOException;

import org.glassfish.grizzly.http.server.HttpServer;

public class RestServerMain {

	public static void main(String[] args) throws IOException {
		HttpServer httpServer = HttpServerFactory.createHttpServer();
		httpServer.start();
		System.out.println(String.format(
				"Jersey app started with WADL available at " + "%sapplication.wadl\nHit enter to stop it...",
				ServerConfig.getBaseURI()));
		System.in.read();
		httpServer.shutdown();
	}
}