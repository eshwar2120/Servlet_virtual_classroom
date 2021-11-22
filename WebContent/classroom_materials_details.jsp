<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="grey">
<%

String title=(String)request.getAttribute("title");
int n=(int)request.getAttribute("number");
String[] file_name=new String[n];
String[] file_path=new String[n];

file_name=(String[])request.getAttribute("file_name");
file_path=(String[])request.getAttribute("file_path");

%>
<center><p style="color:white">MATERIAL NAME:  <%=title %></p>
<br>
TEACHER COMMENT:
<textarea rows="2" cols="15">gjdgfsf</textarea>
</center>
<%
for(int i=0;i<n;i++){
%>
<form action="file_open">
<input type="submit" value="<%=file_name[i] %>" name="file_name" style="height:30px;width:300px;font-size:15px">
</form>
<%
}
%>
<br>
<form action="file_upload" method="post" enctype="multipart/form-data">
<input type="file" name="files" multiple>
<input type="submit" name="submit" value="upload">
</form>
</body>
</html>