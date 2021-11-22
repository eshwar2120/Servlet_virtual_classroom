<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %>
    <%@ page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html>
<head>
<style>
form {
    display: inline;
}
</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
hello world
<%
int n=(int)request.getAttribute("n");
String [] title=new String[n];
Date [] date_posted=new Date[n];
Date [] due_date=new Date[n];
title=(String[])request.getAttribute("title");
date_posted=(Date[])request.getAttribute("date_posted");
due_date=(Date[])request.getAttribute("due_date");
String s=null;
for(int i=0;i<n;i++){
	s=title[i]+"----DATE_POSTED----"+date_posted[i]+"----DUE_DATE----"+due_date[i];
%>
<form action="classroom_assignments_details">
<center><input type="submit" value="<%=s%>"  style="background-color:red;font-size:15px;height:75px;width:620px;" name="title_name"></center> 

</form><br>
<%
}
%>
<form action="Post_assignment.jsp">
<center><input type="image" src="Addicon.png" alt="submit" width="50px" height="50px"></center>
</form>
</body>
</html>