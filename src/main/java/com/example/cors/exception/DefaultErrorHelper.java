package com.example.cors.exception;

import org.glassfish.grizzly.http.server.ErrorPageGenerator;
import org.glassfish.grizzly.http.server.Request;

public class DefaultErrorHelper {

	public enum ResponseMediaType {
		JSON, HTML;
	}
	
	public static ErrorPageGenerator getDefaultErrorResponse(ResponseMediaType type) {

		return new ErrorPageGenerator() {
			@Override
			public String generate(Request request, int status, String reasonPhrase, String description,
					Throwable exception) {
				String response = null;
				switch (type) {
				case JSON:
					response = jsonResponse();
					break;
				case HTML:
					response = htmlResponse();
					break;
				default:
					break;
				}
				return response;
			}
		};
	}

	private static String jsonResponse() {
		return "{\"message\" : \"error json.\"";
	}

	private static String htmlResponse() {
		return "<html><body>erro page.</body></html>";
	}
}
