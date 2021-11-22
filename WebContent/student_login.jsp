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
int no_student=(int)request.getAttribute("no_student");
String [] classroom_name=new String[no_student];
String [] subject_name=new String[no_student];
String [] class_code=new String[no_student];
classroom_name=(String [])request.getAttribute("classroom_name");
subject_name=(String [])request.getAttribute("subject_name");
class_code=(String [])request.getAttribute("class_code");
String value=null;
for(int i=0;i<no_student;i++){
	value=classroom_name[i]+"---"+subject_name[i]+"---"+class_code[i];

%>
<form action="class_student_materials">
<center><input type="submit" value=<%=value %> style="background-color:blue;font-size:15px;height:75px;width:620px;" name="code"></center>
</form>
<%
}
%>
<br>
<br>
<form action="create_student_classroom.jsp">
<center><input type="image" src="Addicon.png" alt="submit" width=50px height=50px></center>
</form>
</body>
</html>