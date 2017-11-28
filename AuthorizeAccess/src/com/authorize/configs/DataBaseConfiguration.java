package com.authorize.configs;

public class DataBaseConfiguration {
	private static final String URL = "jdbc:mysql://localhost/Registrations";

	private static final String USER = "root";

	private static final String PASSWORD = "amagi123";

	private static final String DATABASE = "sql";

	public static String getUrl() {
		return URL;
	}

	public static String getUser() {
		return USER;
	}

	public static String getPassword() {
		return PASSWORD;
	}

	public static final String getDatabase() {
		return DATABASE;
	}
}
