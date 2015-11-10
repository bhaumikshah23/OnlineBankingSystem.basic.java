package com.securecodewarrior.challenges.banking.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.securecodewarrior.challenges.banking.model.User;
import com.securecodewarrior.challenges.banking.util.Constants;

/**
 * Servlet Filter implementation class AuthenticationFilter
 *
 * Filter implementation to intercept all requests and attempt to authenticate
 * the user by redirecting them to login.jsp.
 *
 * @author kushal shah
 *
 */
@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    /**
     * Default constructor.
     */
    public AuthenticationFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain) throws IOException, ServletException {

		final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
		final String uri = request.getRequestURI();
		final HttpSession session=request.getSession();

		if(uri.contains("login")||uri.contains("login.jsp")||uri.contains("/css") || uri.contains("/js")&&!uri.contains("/font-awesome")|| uri.contains("/fonts")){

			if(session.getAttribute("JSESSIONUSER")!=null) {

				final boolean isAdmin=redirectToValidDashboard((User)session.getAttribute("JSESSIONUSER"));
				if(isAdmin)
				{
					response.sendRedirect(request.getContextPath()+Constants.URL_ADMIN_DASHBOARD);
				}
				else
				{
					response.sendRedirect(request.getContextPath()+Constants.URL_USER_DASHBOARD);
				}
			}
			else
			{
				chain.doFilter(request,response);
			}

		}
		else if(session.getAttribute("JSESSIONUSER")!=null){

			final boolean isAdmin=redirectToValidDashboard((User)session.getAttribute("JSESSIONUSER"));
			if(uri.contains("/admin")) {
					if(isAdmin)
					{
						chain.doFilter(request, response);
					}
					else
					{
						session.setAttribute("error", Constants.MSG_UNAUTHORIZED_READ_FILE);
						response.sendRedirect(request.getContextPath()+Constants.URL_USER_ERROR);
					}

			}
			else if(uri.contains("/user"))
			{
				if(isAdmin)
				{
					session.setAttribute("error", Constants.MSG_UNAUTHORIZED_READ_FILE);
					response.sendRedirect(request.getContextPath()+Constants.URL_ADMIN_ERROR);
				}
				else
				{
					chain.doFilter(request, response);
				}
			}
			else
			{
				chain.doFilter(request, response);
			}

		}
		else{
			response.sendRedirect(request.getContextPath()+Constants.URL_LOGIN);
		}

	}

	/**
	 *
	 * check user has access to given url or not
	 * @param user
	 * @return
	 */
	private boolean redirectToValidDashboard(final User user) {

		boolean isAdmin=true;
		final int role_id=user.getRole_id();
		if(role_id!=Constants.ADMIN_ROLE_ID)
		{
			isAdmin=false;
		}
		return isAdmin;

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(final FilterConfig fConfig) throws ServletException {
	}

}
