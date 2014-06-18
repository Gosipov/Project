<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign Up</title>
<link rel = "stylesheet" type = "text/css" href = "Babdustyle2.css">
</head>
<body>
	<div class ="outer">
		<div class ="middle">
			<div class ="inner">
				<h1> <% out.print((String) request.getServletContext().getAttribute("header1")); %> </h1>
				<h2> <% out.print((String) request.getServletContext().getAttribute("header2")); %> </h2>
				<form action="SignUpRetry" method="post">
					Username: <input type="text" name="username" required> <br>
					Password: <input type="password" name="password" required> <br>
					<input class ="button" type="submit" value="Login"> <br>
				</form>
			</div>
		</div>
	</div>
</body>
</html>