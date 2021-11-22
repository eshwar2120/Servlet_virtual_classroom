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
<%
String subject=(String)request.getAttribute("subject_name");
String class_name=(String)request.getAttribute("class_name");
int n=(int)request.getAttribute("number");
String [] title=new String[n];
Date []date=new Date[n];
title=(String[])request.getAttribute("material_title");
date=(Date[])request.getAttribute("date");
%>

<center><form action="material">
<input type="submit" value="MATERRIAL" name="material">
</form>
<form action="class_assignment">
<input type="submit" value="ASSIGNMENT" name="assignment">
</form>
<form action="class_details">
<input type="submit" value="CLASS-DETAILS" name="class_details">
</form>
</center>

<br>
<br>
<center><%=class_name %></center>
<center><%=subject %></center>
<br>
<br>
<%
String s;
for(int i=0;i<n;i++){
	s=title[i]+"----"+date[i];
%>
<form action="classroom_materials_details">
<center><input type="submit" value="<%=s%>"  style="background-color:red;font-size:15px;height:75px;width:620px;" name="title_name"></center> 

</form><br>
<%
}
%>
<form action="Post_material.jsp">
<center><input type="image" src="Addicon.png" alt="submit" width="50px" height="50px"></center>
</form>
</body>
</html>