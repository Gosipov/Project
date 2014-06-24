<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Quiz submitted</title>
<link rel = "stylesheet" type = "text/css" href = "QuizCreateStyle.css">
</head>
<body>
	<div class="header">
		<h1> Congratulations! </h1>
	</div>
	<div class = "wrapper centered">
		<p> Congratulations, You've submitted <% out.print((String) request.getSession().getAttribute("quiz_name")); %> </p>
		<p> Your score is <% out.print(request.getSession().getAttribute("score")); %> </p>
		<p> You passed quiz in <% out.print(request.getSession().getAttribute("elapsed") + " milliseconds"); %> </p>
		<br>
		<p> You can see other result here: 
			<a href='QuizDescription?id=<% out.print(request.getSession().getAttribute("quiz_id")); %>'> Quiz Description </a> 
		</p>
		<form action='HomePage' method='post'>
			<button> Return to Homepage </button>
		</form>
	</div>
</body>
</html>