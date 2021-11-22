<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
int n=(int)request.getAttribute("number");
String [] file_name=new String[n];
file_name=(String[])request.getAttribute("file_name");
String title=(String)request.getAttribute("title");
String value=null;

String teacher=(String)request.getAttribute("teacher");
System.out.println(teacher);
%>
<center><font size="14"><%=title %></font></center>
<br>
<br>
<p>TEACHER COMMENT : <%=teacher %></p>
<%
for(int i=0;i<n;i++){
value=file_name[i];

%>
<form action="file_open">
<input type="submit" value="<%=value%>"  style="background-color:blue;font-size:15px;height:55px;width:220px;" name="file_name">
</form>
<%
}
%>
</body>
</html>