<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="grey">
<CENTER>
<form action="add_assignment" method="post" >
ENTER THE ASSIGNMENT NAME:<input type="text" name="assignment_title" style="height:15px;width:150px">
<br><br><br>
ENTER THE TOTAL MARKS:<input type="text" name="total_marks" style="height:15px;width:150px">
<br><br>
ENTER THE DUE DATE:<input type="text" name="due_date" style="height:15px;width:150px">

<input type="submit" value="CREATE ASSIGNMENT">
</form>
</CENTER>
</body>
</html>