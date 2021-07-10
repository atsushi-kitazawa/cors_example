
package com.example.cors.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/hello")
public class HelloResource {

	@GET
	@Path("/{name}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response hello(@PathParam("name") String name) {
		String msg = "hello " + name + "!";

		return Response.status(Status.OK).entity(msg).build();
	}
}
