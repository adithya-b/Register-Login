package com.authorize.cryptomodule;

import com.authorize.configs.EncryptionConfiguration;

public class DecryptionHandler {

	public static String decryptPassword(String password) {
		String sharedKey = EncryptionConfiguration.getSharedKey();
		// Library call to decryption library with shared key.
		return password;
	}

}
