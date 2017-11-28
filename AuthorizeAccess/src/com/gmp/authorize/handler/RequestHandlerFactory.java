package com.gmp.authorize.handler;

import java.util.HashMap;
import java.util.Map;

public class RequestHandlerFactory {

	private static RequestHandlerFactory instance;

	private Map<String, String> HandlersMap;

	private RequestHandlerFactory() {
		HandlersMap = new HashMap<String, String>();
		HandlersMap.put("login", "com.gmp.authorize.handler.implementation.LoginHandler");
		HandlersMap.put("signup", "com.gmp.authorize.handler.implementation.SignUpHandler");
	}

	public static synchronized RequestHandlerFactory getInstance() {
		if (instance == null) {
			instance = new RequestHandlerFactory();
			return instance;
		} else {
			return instance;
		}
	}

	public RequestHandler getHandler(String type) throws Exception {
		String handlerClassName = HandlersMap.get(type);
		if (handlerClassName == null) {
			throw new ClassNotFoundException("Handler not found for type:" + type);
		}
		Class<?> handlerClass = Class.forName(handlerClassName);
		RequestHandler handler = (RequestHandler) handlerClass.newInstance();
		return handler;
	}

}