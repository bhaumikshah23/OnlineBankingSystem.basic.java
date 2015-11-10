package com.securecodewarrior.challenges.banking.controller;

import java.awt.AWTException;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.securecodewarrior.challenges.banking.model.User;
import com.securecodewarrior.challenges.banking.service.UsersService;

public class LoginTestCase extends Mockito {
	/**
	 * Login Test Case
	 *
	 *
	 */
	@Test
	public void login() throws ServletException, IOException, AWTException {
		// Set value of login credential
		final String strUserName = "john.miller";
		final String strPassword = "john.password";
		final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		// Pass value to Text field
		Mockito.when(request.getParameter("username")).thenReturn(strUserName);
		Mockito.when(request.getParameter("password")).thenReturn(strPassword);
		// Call validLogin method
		HashMap<String, Object> resultHashMap = new HashMap<String, Object>();
		final User loginModel = new User();
		final UsersService usersService = new UsersService();
		final String userName = request.getParameter("username").trim();
		final String password = request.getParameter("password").trim();
		loginModel.setUsername(userName);
		loginModel.setPassword(password);
		resultHashMap = usersService.validLogin(loginModel);
		// Get Result of Login method
		final int isError = (Integer) resultHashMap.get("isError");
		// Verify dashboard page
		if (isError == 0) {
			// verify(response).sendRedirect("dashboard.jsp");
			try {
				Assert.assertTrue("john.miller".equals(strUserName));
			} catch (final Exception e) {
				System.err.println("Invalid UserName");
			}
			try {
				Assert.assertTrue("john.password".equals(strPassword));
			} catch (final Exception e) {
				System.err.println("Invalid Password");
			}
		}
		// Verify login page
		else {
			Assert.assertTrue(isError == 0);
		}
	}
}
