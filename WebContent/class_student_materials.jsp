<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<style>
form{
display:inline;
}
</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p>
<center>
<form action="class_student_materials" >
<input type="submit" value="materials">
</form> 
&nbsp&nbsp&nbsp
<form action="classroom_student_assignments">
<input type="submit" value="assignments">
</form>
&nbsp&nbsp&nbsp
<form action="class_details">
<input type="submit" value="class-details">
</form>
</center>
</p>
<%
int n=(int)request.getAttribute("number");
String [] title=new String[n];
Date [] date=new Date[n];
title=(String[])request.getAttribute("title");
date=(Date[])request.getAttribute("date");
String value=null;
for(int i=0;i<n;i++){
value=title[i]+"----"+date[i];
%>

<form action="class_student_materials_details">
<center><input type="submit" value="<%=value%>"  style="background-color:blue;font-size:15px;height:75px;width:620px;" name="title_name"></center> 
<br><br>
</form>

<%

}
%>

</body>
</html>