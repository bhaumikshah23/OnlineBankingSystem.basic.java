<%@page import="com.securecodewarrior.challenges.banking.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib prefix="core" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dashboard Page</title>
<link href="../css/font-awesome.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="../css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="../css/custom-styles.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="../css/errors.css" rel="stylesheet" type="text/css" media="all" />
</head>

<body>
<div class="errorPage">

			<div class="explanation">
				<br />Secure online banking services.
			</div>

			<%
				if (session.getAttribute("error") != null) {
			%>
			<div class="alert alert-danger">
				<%=session.getAttribute("error")%>
			</div>
			<%
				}
				session.removeAttribute("error");
			%>
</div>
<%
User user=(User)session.getAttribute("JSESSIONUSER");
user.getUsername();
%>
Back to <a href="<%=request.getContextPath() %>/admin/dashboard.jsp">Dashboard</a>
</body>
</html>