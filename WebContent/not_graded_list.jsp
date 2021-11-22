<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
table {
  width: 50%;
  border: 1px solid black;
}

th {
  height: 70px;
  border: 1px solid black;
}
td{
border: 1px solid black;
}
</style>
</head>
<body>
<%
int n=(int)request.getAttribute("num_ass");
String [] name=new String[n];
String [] email_id=new String[n];

name=(String [])request.getAttribute("name");
email_id=(String [])request.getAttribute("email_ids");
%>
<center>
<table>
<th>NAME</th>
<th>EMAIL-ID</th>

<%
for(int i=0;i<n;i++){
%>
<tr>
<td><%=name[i] %></td>
<td>
<form action="award_marks">
<input type="submit" name="stu_email_id" value=<%=email_id[i] %>></form>
</td>

</tr>
<%
}
%>
</table>
</center>
</body>
</html>