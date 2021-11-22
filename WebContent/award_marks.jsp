<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>

</style>
</head>
<body>
<%
int n=(int)request.getAttribute("num_files");
Date due_date=(Date)request.getAttribute("due_date");
Date date_submitted=(Date)request.getAttribute("date_submitted");
String [] file_list=new String[n];
file_list=(String [])request.getAttribute("file_list");
int total_marks=(int)request.getAttribute("total_marks");
String stu_name=(String)request.getAttribute("student_name");
%>
<center>
<p style="font-size:25px">Student name: <%=stu_name %><br><br>
Total marks: <%=total_marks %><br><br>
Due date: <%=due_date %><br><br>
Date submitted: <%=date_submitted %><br><br>
</center>
Files:


<%
for(int i=0;i<n;i++){
	
%>
<form action="open_assignment_award">

<input type="submit" value="<%=file_list[i] %>" name="file_name" style="height:30px;width:300px;font-size:15px">
</form>
<br>
<%
}
%>
<center>
<form action="grade_marks">
<p style="font-size:25px">enter the marks:</p><input type="text" name="marks_awarded" style="font-size:25px">
<button type="submit">GRADE MARKS</button>
</form>

</center>
</body>
</html>