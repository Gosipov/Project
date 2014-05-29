<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign Up</title>
</head>
<body>
<h1> <% out.print((String) request.getParameter("header1")); %></h1>
<h2> <% out.print((String) request.getParameter("header2")); %></h2>
<form action="Login" method="post">
	Username: <input type="text" name="username"> <br>
	Password: <input type="password" name="password">
	<input type="submit" value="Login"> <br>
</form>
<a href="signup.html">Register </a>
</body>
</html>