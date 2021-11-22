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

@WebServlet("/student")
public class Student_login extends HttpServlet {
	
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String [] class_name =null;
		String [] subject_name =null;
		String[] class_code=null;
		HttpSession session=request.getSession();
		String email_id=(String) session.getAttribute("email_id");
		
		int end=email_id.length()-10;
		   String sub=email_id.substring(0,end);
		String table_name="student_login_"+sub;
		int n=0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineclassroom","root","deepa2003");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select classroom_name,subject,class_code from "+table_name);
			rs.next();
			Statement st1=con.createStatement();
			ResultSet rs1=st1.executeQuery("select count(*) from "+table_name);
			rs1.next();
			 n=rs1.getInt(1);
			class_name=new String[n];
			subject_name=new String[n];
			class_code=new String[n];
			for(int i=0;i<n;i++) {
				class_name[i]=rs.getString(1);
				subject_name[i]=rs.getString(2);
				class_code[i]=rs.getString(3);
				rs.next();
			}
			
		}
		catch(Exception e) {
			System.out.print(e);
		}
		RequestDispatcher rd=request.getRequestDispatcher("student_login.jsp");
		request.setAttribute("classroom_name", class_name);
		request.setAttribute("subject_name", subject_name);
		request.setAttribute("no_student", n);
		request.setAttribute("class_code", class_code);
		rd.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
