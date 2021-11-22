package Servletfiles;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;
import java.util.SplittableRandom;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Otp extends HttpServlet {
	
       
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email_id=request.getParameter("email-id");
		String password=request.getParameter("password");
		HttpSession session=request.getSession();
		session.setAttribute("email_id", email_id);
		session.setAttribute("password",password);
		String status="no";
		int no_classroom=0;
		int no_student=0;
		int no_teacher=0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineclassroom","root","deepa2003");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("SELECT status FROM login_table WHERE email_id='"+email_id+"' AND password='"+password+"'");
			rs.next();
			status=rs.getString(1);
			ResultSet rs1=st.executeQuery("SELECT * FROM user_details WHERE email_id='"+email_id+"'");
			rs1.next();
		    no_classroom=rs1.getInt(2);
		    no_student=rs1.getInt(3);
		    no_teacher=rs1.getInt(4);
		}
		catch(Exception e) {
			System.out.print(e);;
		}
		
		request.setAttribute("status", status);
		request.setAttribute("email_id", email_id);
		request.setAttribute("password",password);
		request.setAttribute("no_classroom", no_classroom);
		request.setAttribute("no_student", no_student);
		request.setAttribute("no_teacher", no_teacher);
		
		RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}
		
		
		//System.out.print(status);
		//System.out.print(email_id);
		
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
