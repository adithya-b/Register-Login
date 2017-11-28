package com.authorize.db;

public interface DbInterface {

	boolean checkEntry(String mailId, String hashedPassword);

	boolean insertEntry(String mailId, String hashedPassword);
}
