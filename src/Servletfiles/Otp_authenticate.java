package Servletfiles;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/otp_authenticate")
public class Otp_authenticate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		String email_id=(String) session.getAttribute("email_id");
		String password=(String) session.getAttribute("password");
		String otp=(String) session.getAttribute("otp");
		String input_otp=request.getParameter("input_otp");
		String otp_status="no";
		if(otp.equals(input_otp)) {
			otp_status="yes";
		}
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
		request.setAttribute("otp_status",otp_status);
		RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
