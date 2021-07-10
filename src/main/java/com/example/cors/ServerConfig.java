package com.example.cors;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

public class ServerConfig {

	public static final String HTTP = "http://";
	public static final String HOST = "localhost";
	public static final String SLASH = "/";
	public static final int PORT = 9998;
	
    public static URI getBaseURI() {
    	String url = HTTP + HOST + SLASH;
        return UriBuilder.fromUri(url).port(PORT).build();
    }
}
