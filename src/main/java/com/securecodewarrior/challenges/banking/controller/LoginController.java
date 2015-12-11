package com.securecodewarrior.challenges.banking.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.securecodewarrior.challenges.banking.model.User;
import com.securecodewarrior.challenges.banking.service.UsersService;
import com.securecodewarrior.challenges.banking.util.Constants;


/**
 *
 * This Controller willPerform authentication process for user login.
 *
 * This controller does following things :
 * 1. check for username and password is null or not
 * 2. check username and password is in existing database or not
 *
 * @author kushal shah
 *
 */
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	/**
	 * This method will call when user try to login in application
	 *
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(final HttpServletRequest request,final HttpServletResponse response) throws ServletException, IOException
	{

		System.out.println("Between");
		System.out.println("master branch");
		final UsersService usersService = new UsersService();
		HashMap<String, Object> resultHashMap = new HashMap<String, Object>();
		final HttpSession session = request.getSession();
		final String userName = request.getParameter("username").trim();
		final String password = request.getParameter("password").trim();

		// if fields are empty or not
		if (userName != null && password != null)
		{
			final User loginModel = new User();

			loginModel.setUsername(userName);
			loginModel.setPassword(password);

			//check whether user valid or not
			resultHashMap = usersService.validLogin(loginModel);

			//check whether get error or not
			final int isError = (Integer) resultHashMap.get("isError");

			if (isError != 1)
			{
				final User successUser=(User) resultHashMap.get("successUser");
				if(successUser.getRole_id()!=Constants.ADMIN_ROLE_ID) {
					session.setAttribute("JSESSIONUSER",
							successUser);
					response.sendRedirect(request.getContextPath()+Constants.URL_USER_DASHBOARD);
				}
				else
				{
					session.setAttribute("JSESSIONUSER",
							successUser);
					response.sendRedirect(request.getContextPath()+Constants.URL_ADMIN_DASHBOARD);
				}

			}
			else
			{
				session.setAttribute("error", resultHashMap.get("errormessage"));
				response.sendRedirect(request.getContextPath()+Constants.URL_LOGIN);
			}

		}
		else
		{
			session.setAttribute("error", Constants.MSG_REQUIRED_USERNAME_PASSWORD);
			response.sendRedirect(request.getContextPath()+Constants.URL_LOGIN);
		}
	}

}
