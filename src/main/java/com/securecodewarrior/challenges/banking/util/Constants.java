package com.securecodewarrior.challenges.banking.util;

/**
 * This class will provide all Constant Variables.
 *
 * @author kushal shah
 *
 */
public class Constants {

	public static final int THRESHOLDMINUTES=20;
	public static final int LOCKOUTMINUTES=30;
	public static final int THRESHOLDATTEMPTS=3;

	//Error Messages
	public static final String MSG_BAD_LOGIN_INPUT="Username or Password is invalid";
	public static final String MSG_ACCOUNT_LOCKED="User is Locked";
	public static final String MSG_REQUIRED_USERNAME_PASSWORD="Username and Password Required!";

	//Security Question
	public static final String MSG_SECURITY_QUESTION="What's the name of your relevant other?";

	//Different user roles
	public static final int ADMIN_ROLE_ID =100;
	public static final int CUSTOMER_ROLE_ID =101;

	public static final String MSG_UNAUTHORIZED_READ_FILE="User is not authorized to read this file.";

	//Redirect Urls
	public static final String URL_ADMIN_DASHBOARD="/admin/dashboard.jsp";
	public static final String URL_USER_DASHBOARD="/user/dashboard.jsp";
	public static final String URL_LOGIN="/login.jsp";
	public static final String URL_USER_ERROR="/user/error.jsp";
	public static final String URL_ADMIN_ERROR="/admin/error.jsp";



}
