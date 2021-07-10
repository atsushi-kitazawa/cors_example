package com.example.cors.resource;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/cors")
public class CorsPageResource {

	@GET
	@Path("page")
	@Produces(MediaType.TEXT_HTML)
	public Response getPage() {
		return Response.status(Status.OK).entity(corsPage()).build();
	}

	private String corsPage() {
		String result = null;
		try {
			result = Files
					.lines(Paths.get(CorsPageResource.class.getClassLoader()
							.getResource("cors.html").toURI()),
							Charset.forName("UTF-8"))
					.collect(Collectors
							.joining(System.getProperty("line.separator")));
		} catch (Exception e) {
			result = e.getMessage();
		}
		return result;
	}
}
