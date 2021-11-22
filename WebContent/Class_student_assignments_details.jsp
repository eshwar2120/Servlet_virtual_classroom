<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %>
    <%@ page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
#submit_status{
float:right;
}
.display{
float:center;
}
</style>
</head>
<body>
<%

String title=(String)request.getAttribute("assignment_title");
int n=(int)request.getAttribute("n");
int n1=(int)request.getAttribute("n1");
String [] list_file=new String[n];
String [] list_file_stu=new String[n1];
list_file=(String [])request.getAttribute("list_file");
list_file_stu=(String [])request.getAttribute("list_file_stu");
String status=(String)request.getAttribute("status");
int marks=(int)request.getAttribute("marks");
String grade_status=(String)request.getAttribute("grade_status");
Date due_date=(Date)request.getAttribute("due_date");
Date date_submit=(Date)request.getAttribute("date_submit");
System.out.println(title);
int ss=0;
LocalDate dt=LocalDate.now();
Date today=Date.valueOf(dt);
String submission="ACCEPTED";
System.out.println(today);
if(date_submit!=null){
if(date_submit.compareTo(due_date)>0){
	ss=1;
}
}
else{
	if(today.compareTo(due_date)>0){
		ss=1;
	}
	ss=0;
}
if(ss==1){
	submission="DONE LATE";
}
%>
<p style="color:red;font-size:25px;text-align:center"><%=title %></p>
<p style="color:purple;font-size:25px;text-align:center"><%=submission %>
</p>
<br>
<br>

<%
String s=null;
for(int i=0;i<n;i++){
	s=list_file[i];
	System.out.println(s);
%>
<form action="file_open_assignment">
<input type="submit" value="<%=s %>" name="file_name" style="height:30px;width:300px;font-size:15px">
</form>
<%
}
String no="NO";


if(status.equals(no)){
%>
<p>due date <%=due_date %><br>
   

</p>
<div id="submit_status">
<center>
<p style="font-size:25px;text-align:center">due date: <%=due_date %><br>
marks: <%=marks %><br>
grade status: <%=grade_status %>

</p>
</center>
<form action="assignment_student_submit" method="post" enctype="multipart/form-data">
<input type="file" name="files" multiple>
<br><br><br>
<input type="submit" value="SUBMIT" name="ass_submit" style="height:30px;width:300px;font-size:15px;ALIGN:RIGHT">
</form>
<%
}
else{
%>

<center>
<p style="font-size:25px">due date: <%=due_date %><br>
marks: <%=marks %><br>
grade status: <%=grade_status %>

</p>
<%
for(int i=0;i<n1;i++){
%>
<form action="open_assignment_award">
<input type="submit" value="<%=list_file_stu[i] %>" name="file_name" style="height:30px;width:300px;font-size:15px">

</form>
<br><br>
<%
}
%>
<input type="submit" value="SUBMITED" name="ass_submit" style="height:30px;width:300px;font-size:15px;background-color:red"></center>

<%
}
%>
</div>
</body>
</html>