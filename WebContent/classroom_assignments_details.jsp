<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
form{
display:inline;
}
</style>
</head>
<body>
<%
String title=(String)request.getAttribute("ass_title");
int assigned=(int)request.getAttribute("assigned");
int turned_in=(int)request.getAttribute("turned_in");
int graded=(int)request.getAttribute("graded");
int not_graded=(int)request.getAttribute("not_graded");
String assign="ASSIGNED";
String turn="TURNED IN";
String grade="GRADED";
String not_grade="NOT GRADED";
%>
<p style="color:red;font-size:55px;text-align:center"><%=title %></p>
<form action="teacher_file_upload" method="post" enctype="multipart/form-data">
<input type="file" name="files" multiple>
<input type="submit" name="submit" value="upload">
</form>
<center>
<form action="assigned_list">
<button type="submit" style="background-color:red;font-size:15px;height:75px;width:120px;"><%=assign %><br><%=assigned %></button>
</form>
<form action="turned_in_list">
<button type="submit" style="background-color:red;font-size:15px;height:75px;width:120px;"><%=turn %><br><%=turned_in %></button>
</form>
<form action="graded_list">
<button type="submit" style="background-color:red;font-size:15px;height:75px;width:120px;"><%=grade %><br><%=graded %></button>
</form>
<form action="not_graded_list">
<button type="submit" style="background-color:red;font-size:15px;height:75px;width:155px;"><%=not_grade %><br><%=not_graded %></button>

</form>
</center>
<%
int n=(int)request.getAttribute("n");
String [] title_list=new String[n];
title_list=(String [])request.getAttribute("title_list");
String s=null;

%>

<%
for(int i=0;i<n;i++){
	s=title_list[i];
%>
<form action="file_open_assignment">
<input type="submit" value="<%=s %>" name="file_name" style="height:30px;width:300px;font-size:15px"><br><br>
</form>
<%
}
%>
</body>
</html>