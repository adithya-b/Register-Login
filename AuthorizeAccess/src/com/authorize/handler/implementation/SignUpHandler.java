package com.authorize.handler.implementation;

import java.util.Map;

import com.authorize.Hash.HashingHandler;
import com.authorize.cryptomodule.DecryptionHandler;
import com.authorize.db.DbFacade;
import com.authorize.handler.HandlerResponse;
import com.authorize.handler.RequestHandler;
import com.authorize.handler.HandlerResponse.Status;
import com.authorize.json.LoginInput;
import com.google.gson.Gson;

public class SignUpHandler implements RequestHandler {

	public HandlerResponse handlePost(String metaData, Map<String, String[]> parameters) {
		LoginInput input = new Gson().fromJson(metaData, LoginInput.class);
		String mailId = input.getMailId();
		String cryptoPassword = input.getPassword();
		if (mailId == null || cryptoPassword == null || mailId.equals("") || cryptoPassword.equals(""))
			return getResponseObject(Status.FAILURE, "Please provide non-null values for mailid and password", 400);
		String actualPassword = DecryptionHandler.decryptPassword(cryptoPassword);
		String hashedPassword = HashingHandler.getHashedForm(actualPassword);
		boolean result = DbFacade.checkUserEntry(mailId, null);
		if (result == true)
			return getResponseObject(Status.FAILURE, "User Already Exists", 200);
		result = DbFacade.addUserEntry(mailId, hashedPassword);
		if (result == false)
			return getResponseObject(Status.FAILURE, "Unable to Process request Please try again after sometime", 417);
		return getResponseObject(Status.SUCCESS, "User Signup Successfull", 200);

	}

	public HandlerResponse handleGet(Map<String, String[]> parameters) {
		return getResponseObject(Status.FAILURE, "", 405);
	}

	private HandlerResponse getResponseObject(Status status, String message, int statuscode) {
		final Status st = status;
		final String m = message;
		final int c = statuscode;
		return new HandlerResponse() {

			@Override
			public Status getStatus() {
				return st;
			}

			@Override
			public String getMessage() {
				return m;
			}

			public int getStatusCode() {
				return c;
			}
		};
	}

}