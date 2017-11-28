package com.authorize.db;

import java.util.HashMap;
import java.util.Map;

import com.authorize.configs.DataBaseConfiguration;

public class DbFacade {

	private static Map<String, String> DbHandlersMap;

	static {
		DbHandlersMap = new HashMap<String, String>();
		DbHandlersMap.put("sql", "com.gmp.authorize.db.SQLDbHandler");
	}

	public static boolean checkUserEntry(String mailId, String hashedPassword) {
		DbInterface dbHandler = getDbHandler();
		return dbHandler.checkEntry(mailId, hashedPassword);

	}

	public static boolean addUserEntry(String mailId, String hashedPassword) {
		DbInterface dbHandler = getDbHandler();
		return dbHandler.insertEntry(mailId, hashedPassword);

	}

	public static boolean DeleteUserEntry(String mailId, String hashedPassword) {
		// Can be implemented as extension
		return false;
	}

	public static DbInterface getDbHandler() {
		String handlerClassName = DbHandlersMap.get(DataBaseConfiguration.getDatabase());
		Class<?> handlerClass;
		DbInterface dbHandler = null;
		try {
			handlerClass = Class.forName(handlerClassName);
			dbHandler = (DbInterface) handlerClass.newInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return dbHandler;

	}

}
