package com.securecodewarrior.challenges.banking.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.securecodewarrior.challenges.banking.util.Constants;
import com.securecodewarrior.challenges.banking.util.Log;


/**
 *
 * This Servlet responsible for logout user and invalidate session.
 *
 * @author kushal shah
 *
 */
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

		Log.info(this.getClass().getName() + "==> Method : doGet ==> Enter");
		final HttpSession session = request.getSession();
		if (session.getAttribute("JSESSIONUSER") != null) {

			session.invalidate();

		}

		Log.info(this.getClass().getName() + "==> Method : doGet ==> Exit");

		response.sendRedirect(request.getContextPath()+Constants.URL_LOGIN);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);

	}

}
