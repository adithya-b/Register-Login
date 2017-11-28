package com.authorize.handler;

import java.util.Map;

public interface RequestHandler {
	public HandlerResponse handlePost(String metaData, Map<String, String[]> parameters);

	public HandlerResponse handleGet(Map<String, String[]> parameters);
}