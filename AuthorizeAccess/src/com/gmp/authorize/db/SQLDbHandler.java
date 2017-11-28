package com.gmp.authorize.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

import com.gmp.authorize.configs.DataBaseConfiguration;
import com.gmp.authorize.constants.Constants;

public class SQLDbHandler implements DbInterface {

	private static Connection databaseConnection = null;

	public SQLDbHandler() {

		System.out.println("Connecting to database...");
		Connection databaseConnection = getDBConnection();
		executeUpdate(Constants.INITIALIZETable);
		System.out.println("Success");

	}

	public synchronized Connection getDBConnection() {
		if (databaseConnection == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				databaseConnection = DriverManager.getConnection(DataBaseConfiguration.getUrl(),
						DataBaseConfiguration.getUser(), DataBaseConfiguration.getPassword());
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return databaseConnection;

	}

	public ResultSet executeQuery(String query) {
		try {
			System.out.print("The query being executed is" + query);
			Statement statement = databaseConnection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int executeUpdate(String update) {
		try {
			System.out.print("The update being executed is" + update);
			Statement statement = databaseConnection.createStatement();
			int resultSet = statement.executeUpdate(update);
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public boolean checkEntry(String mailId, String hashedPassword) {
		String query;
		if (hashedPassword != null) {
			query = Constants.VALIDATEUSER.replace("#MAILID#", mailId);
			query = query.replace("#PASSWORD#", hashedPassword);
		} else {
			query = Constants.FINDUSER.replace("#MAILID#", mailId);
		}
		ResultSet resultSet = executeQuery(query);
		try {
			int count = 0;
			while (resultSet.next()) {
				count = resultSet.getInt(Constants.COUNT);
			}
			if (count == 1)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean insertEntry(String mailId, String hashedPassword) {
		String update = Constants.INSERTVALUES.replace("#MAILID#", mailId);
		update = update.replace("#PASSWORD#", hashedPassword);
		int result = executeUpdate(update);
		if (result == 1)
			return true;
		return false;
	}

	public void main(String args[]) {
		executeQuery("select * from REGISTRATION");
	}
}
