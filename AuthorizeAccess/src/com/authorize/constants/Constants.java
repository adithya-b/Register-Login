package com.authorize.constants;

public class Constants {
	public static final String TYPE = "type";

	public static final String INITIALIZETable = "CREATE TABLE IF NOT EXISTS REGISTRATIONDETAILS"
			+ "(mailid VARCHAR(255) not NULL, " + " password VARCHAR(255), " + " PRIMARY KEY ( mailid ))";

	public static final String INSERTVALUES = "INSERT INTO REGISTRATIONDETAILS(mailid,password) "
			+ "VALUES ('#MAILID#', '#PASSWORD#')";

	public static final String VALIDATEUSER = "SELECT COUNT(mailid) AS TotalCount FROM REGISTRATIONDETAILS"
			+ " WHERE mailid = '#MAILID#' AND  password = '#PASSWORD#'";
	public static final String FINDUSER = "SELECT COUNT(mailid) AS TotalCount FROM REGISTRATIONDETAILS"
			+ " WHERE mailid = '#MAILID#'";
	public static final String COUNT = "TotalCount";

}
