package Servletfiles;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

@WebServlet("/grade_marks")
public class Grade_marks extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		String class_code=(String) session.getAttribute("class_code");
		String stu_email_id=(String) session.getAttribute("stu_email_id");
		String title=(String) session.getAttribute("assignment_title");
		String table="assignments_"+title+"_"+class_code+"_stu";
		String status="YES";
		int marks=Integer.parseInt(request.getParameter("marks_awarded"));
		String sql1="update `"+table+"` set grade_status='"+status+"' where email_id='"+stu_email_id+"'";
		String sql="update `"+table+"` set marks='"+marks+"' where email_id='"+stu_email_id+"'";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineclassroom","root","deepa2003");
			Statement st=con.createStatement();
			int u=st.executeUpdate(sql1);
			Statement st1=con.createStatement();
			int u1=st1.executeUpdate(sql);
			System.out.println(marks);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
