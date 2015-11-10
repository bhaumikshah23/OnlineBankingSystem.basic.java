<!DOCTYPE html>
<html>
<head>
<title>Online Inc.</title>
<link href="/css/font-awesome.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="css/custom-styles.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="css/errors.css" rel="stylesheet" type="text/css" media="all" />
</head>
<body>

	<div class="wrapper">
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
		<form action="<%=request.getContextPath()%>/login" method="post"
			class='form'>
		
			<div style="width: 100%; text-align: center">
				<div style="width: 300px; display: inline-block; margin-top: 30px">
					
					<div class="form-group has-feedback">
						<label class="control-label">Username</label> <input type="text"
							class="form-control" name="username" placeholder="Username"
							required="required" maxlength="50"/> <i
							class="glyphicon glyphicon-user form-control-feedback"></i>
					</div>

					<div class="form-group has-feedback">
						<label class="control-label">Password</label> <input
							type="password" class="form-control" name="password"
							placeholder="Password" required="required" maxlength="50" /> <i
							class="glyphicon glyphicon-lock form-control-feedback"></i>
					</div>

					<div class="form-group has-feedback-left">
						<button type="submit" style="width: 100%" class='btn btn-primary'>Login</button>
					</div>
					
				</div>
			</div>
		</form>



	</div>
</body>

</html>