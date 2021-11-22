<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
int n=(int)request.getAttribute("n");
String [] ass_title=new String[n];
Date [] date_posted=new Date[n];
Date [] due_date=new Date[n];
String s=null;
ass_title=(String [])request.getAttribute("ass_title");
date_posted=(Date [])request.getAttribute("date_posted");
due_date=(Date [])request.getAttribute("due_date");
for(int i=0;i<n;i++){
	s=ass_title[i]+"----DUE DATE----"+due_date[i];
%>
<form action="classroom_student_assignments_details">
<center><input type="submit" value="<%=s%>"  style="background-color:orange;font-size:15px;height:75px;width:620px;" name="title_name"></center> 
<br><br>
</form>
<%
}
%>
</body>
</html>