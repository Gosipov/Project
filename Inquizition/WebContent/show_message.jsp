<%@page import="java.sql.ResultSet"%>
<%@page import="com.mysql.jdbc.Statement"%>
<%@page import="helpers.Message" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% 
	String id = request.getParameter("id");
	Statement db = (Statement) request.getServletContext().getAttribute("database");
	ResultSet rs = db.executeQuery("SELECT * from messages where id = " + id);
	Message m = new Message(rs);
	m.markAsRead(); //since we're on this page, the message has been read
%>
<title><% out.print(m.getSubject()); %></title>
</head>
<body>
<p>From: <%m.getSender(); %></p>
<p>Subject: <%m.getSubject(); %></p>
<p><%m.getText(); %></p>
<!--replying prompt: -->
<form id="form1" name="form1" method="post" action="sendMessage.java">
<input type="text" name="input" id="textfield" />
<textarea name="text" id="textarea" cols="45" rows="5"></textarea>
<input type="submit" name="button" id="button" value="Send" />
</form>
</body>
</html>