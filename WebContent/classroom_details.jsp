<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style >
table, th, td {
  border: 1px solid black;
}
body{
background-color:rgb(56, 49, 105);
}
</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String class_name=(String)request.getAttribute("class_name");
String subject_name=(String)request.getAttribute("subject_name");
String teacher_name=(String)request.getAttribute("teacher_name");
int no_students=(int)request.getAttribute("no_students");
String class_code=(String)request.getAttribute("class_code");
String[] name=(String[])request.getAttribute("name");
String[] role=(String[])request.getAttribute("role");
String[] email_id=(String[])request.getAttribute("email_id");
%>
<center><p>CLASS-DETAILS</p>
<p>CLASS-NAME : <%=class_name %></p>
<p>SUBJECT-NAME : <%=subject_name %></p>
<p>FACULTY-NAME : <%=teacher_name %></p>
<p>NO OF STUDENTS : <%=no_students %></p>
<p>CLASS CODE : <input type="text" id="code" value=<%=class_code %>> <button onclick="copied()">COPY</button></p>
<table>
<tr>
<th>STUDENT NAME</th>
<th>ROLE</th>
<th>EMAIL-ID</th>
</tr>
<%
for(int i=0;i<no_students;i++){
	
%>
<tr>
<td><%=name[i] %></td>
<td><%=role[i] %></td>
<td><%=email_id[i] %></td>
</tr>
<%
}

%>
</table>
<script type="text/javascript">
function copied(){
	var text=document.getElementById("code");
	text.select();
	document.execCommand("copy");
	alert("Copied the text: " + text.value);

}
</script>
</body>
</html>