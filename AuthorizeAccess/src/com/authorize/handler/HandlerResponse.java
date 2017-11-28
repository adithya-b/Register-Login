package com.authorize.handler;

public interface HandlerResponse {
	public static enum Status {
		SUCCESS, FAILURE
	};

	public Status getStatus();

	public String getMessage();

	public int getStatusCode();
}
