package com.securecodewarrior.challenges.banking.util;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.securecodewarrior.challenges.banking.security.auth.PasswordHash;


/**
 *
 * AppUtils class containing utility functions used across the application
 * 1. generate password using PBKDF2WithHmacSHA1
 * 2. verify password
 * @author kushal shah
 *
 */
public class AppUtils {

/**
 * This method will generate password using PBKDF2WithHmacSHA1
 * @param password
 * @return
 */
public static String generatePassword(final String password){
	try {
	return PasswordHash.createHash(password);
	}
	catch (final NoSuchAlgorithmException e) {
	return null;
	} catch (final InvalidKeySpecException e) {
	return null;
	}
}
/**
 * This method will verify password
 *
 * @param password
 * @param db_password
 * @return
 */
public static boolean isValidPassword(final String password,final String db_password){
	boolean isvalid=false;
	try {
		if(PasswordHash.validatePassword(password, db_password))
		{
		isvalid=true;
		}
	}
	catch (final NoSuchAlgorithmException e) {
	isvalid=false;
	}
	catch (final InvalidKeySpecException e) {
	isvalid=false;
	}
	return isvalid;
}
}
