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
int no_teacher=(int)request.getAttribute("no_teacher");

String [] classroom_name=new String[no_teacher];
String [] subject_name=new String[no_teacher];
String [] classroom_code=new String[no_teacher];
String exists=(String)request.getAttribute("exists");
classroom_name=(String [])request.getAttribute("classroom_name");
subject_name=(String [])request.getAttribute("subject_name");
classroom_code=(String [])request.getAttribute("classroom_code");
String value;
for(int i=0;i<no_teacher;i++){
	value=classroom_name[i]+"--"+subject_name[i]+"--"+classroom_code[i];
	System.out.print(value);
%>
<form action="classes">
<center><input type="submit" name="class" value=<%=value %> style="background-color:blue;font-size:15px;height:75px;width:620px;"></center>
<br>
</form>
<%
}
%>
<br>
<br>
<br>
<form action="create_classroom.jsp">
<center><input type="image" src="Addicon.png" alt="submit" width=50px height=50px></center>
</form>
</body>
</html>