package com.tzawartko.vertx.exceptions;

/**
 * Exception throwing when some connection problems with website may occur.
 * 
 * @author Tomasz Zawartko
 *
 */
public class WebSiteConnectionException extends RuntimeException {

	private static final long serialVersionUID = 1246631078047418975L;

	public WebSiteConnectionException(String websiteURL, int port) {
		super("Can not connect to " + websiteURL + " on port " + port);
	}

	public WebSiteConnectionException(String message) {
		super(message);
	}
}
