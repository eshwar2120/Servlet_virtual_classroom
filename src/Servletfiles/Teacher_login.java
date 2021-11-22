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

/**
 * Servlet implementation class Teacher_login
 */
@WebServlet("/teacher")
public class Teacher_login extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	String exists="yes";
	HttpSession session=request.getSession();
	String o=(String) session.getAttribute("email_id");
	String email_id=o;
	int end=email_id.length()-10;
	   String sub=email_id.substring(0,end);
	String table_name="teacher_login_"+sub;
	int no_teacher=0;
	String [] classroom_name;
	String [] subject_name;
	String [] class_code;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineclassroom","root","deepa2003");
		Statement st=con.createStatement();
		Statement st1=con.createStatement();
		Statement st2=con.createStatement();
		ResultSet rs1=st.executeQuery("SELECT no_teacher FROM user_details WHERE email_id='"+email_id+"'");
		rs1.next();
		no_teacher=rs1.getInt(1);
		classroom_name=new String[no_teacher];
		subject_name=new String[no_teacher];
		class_code=new String[no_teacher];
		ResultSet rs3=st1.executeQuery("SELECT COUNT(*) FROM "+table_name);
		ResultSet rs2=st2.executeQuery("SELECT * FROM "+table_name);
		rs2.next();
		rs3.next();
		int records=rs3.getInt(1);
		for(int i=0;i<records;i++) {
			classroom_name[i]=rs2.getString(1);
			subject_name[i]=rs2.getString(3);
			class_code[i]=rs2.getString(5);
			rs2.next();
		}
	}
	catch(Exception e) {
		classroom_name=new String[no_teacher];
		subject_name=new String[no_teacher];
		class_code=new String[no_teacher];
		exists="no";
		System.out.print(e);
	}
	RequestDispatcher rd=request.getRequestDispatcher("teacher_login.jsp");
	request.setAttribute("classroom_name", classroom_name);
	request.setAttribute("subject_name", subject_name);
	request.setAttribute("classroom_code", class_code);
	request.setAttribute("exists", exists);
	request.setAttribute("no_teacher", no_teacher);
	rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
