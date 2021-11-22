<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="orange">
<%
String dum="yes";
String otp_status=(String)request.getAttribute("otp_status");
String status=(String)request.getAttribute("status");
String email_id=(String)request.getAttribute("email_id");
int no_classroom=(int)request.getAttribute("no_classroom");
int no_student=(int)request.getAttribute("no_student");
int no_teacher=(int)request.getAttribute("no_teacher");
if(status.equals(dum)){
%>
<p align="center">logged in </p>
<p align="center">USER DETAILS</p>

<br>
<p align="center" style="font-size:20px">EMAIL-ID:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <input type="text" style="font-size:20px" value=<%=email_id %>>

<p align="center" style="font-size:20px">NO OF STUDENT CLASSROOM  :<input type="text" style="font-size:20px" value=<%=no_student %>>
<p align="center" style="font-size:20px">NO OF TEACHER CLASSROOM  :<input type="text" style="font-size:20px" value=<%=no_teacher %>>
<br>
<br>
<br>
<form action="student">
<center><input type="submit" value="Student" style="background-color:blue;font-size:30px;height:55px;width:120px;"></center>
</form>
<br>
<br>
<form action="teacher">
<center><input type="submit" value="Teacher" style="background-color:red;font-size:30px;height:55px;width:120px;"></center>
</form>
<%
}
else{
%>
<p> ENTER VALID CREDENTIALS</p>
<a href="login.html">back to login page</a>
<%
}
%>


</body>
</html>