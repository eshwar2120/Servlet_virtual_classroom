package Servletfiles;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*
;/**
 * Servlet implementation class Signup
 */
@WebServlet("/signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    String username=(String) request.getParameter("user");
	    String password=(String) request.getParameter("pass");
	    String confirm=(String) request.getParameter("conpass");
	    String status="yes";
	    int no_classroom=0;
	    int no_student=0;
	    int no_teacher=0;
	    if(password.equals(confirm)){
	    	try{
	    		Class.forName("com.mysql.cj.jdbc.Driver");
	    		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineclassroom","root","deepa2003");
	    		PreparedStatement pt=con.prepareStatement("INSERT INTO login_table VALUES(?,?,?)");
	    		pt.setString(1, username);
	    		pt.setString(2, password);
	    		pt.setString(3, status);
	    		int u=pt.executeUpdate();
	    		PreparedStatement pt1=con.prepareStatement("INSERT INTO user_details VALUES(?,?,?,?)");
	    		pt1.setString(1, username);
	    		pt1.setInt(2, no_classroom);
	    		pt1.setInt(3, no_student);
	    		pt1.setInt(4, no_teacher);
	    		int y=pt1.executeUpdate();
	    		Statement st=con.createStatement();
	    	
	    		   int end=username.length()-10;
	    		   String sub=username.substring(0,end);
	    		String table_name="teacher_login_"+sub;
	    		String table_name1="student_login_"+sub;
	    		String query="create table "+table_name+" (classroom_name varchar(25),date_creation date,subject varchar(25),faculty_name varchar(25),class_code varchar(10),primary key(class_code))";
	    		   int y1=st.executeUpdate(query);
	    		 Statement st1=con.createStatement();
	    		 String query1="create table "+table_name1+" (classroom_name varchar(25),date_enroll date,subject varchar(25),class_code varchar(10),primary key(class_code))";
	    		 int y2=st1.executeUpdate(query1);
	    		HttpSession session=request.getSession();
	    		session.setAttribute("email_id", username);
	    		PrintWriter pw=response.getWriter();
	    		pw.print("<html>");
	    		pw.print("<body>");
	    		pw.print("<a href="+"loginpage.html"+">back to login page</a>");
	    		pw.print("</body>");
	    		pw.print("</html>");
	    		pt.close();
	    	    con.close();
	    				
	    				
	    			
	    	}
	    	catch(Exception e){
	    		System.out.print(e);
	        PrintWriter pw=response.getWriter();
	        pw.print("<html>");
	        pw.print("<body>");
	        pw.print("<p><h1>emailid already exists</h1></p>");
	        pw.print("<a href="+"loginpage.html"+">back to login page"+"</a>");
	        pw.print("</body>");
	        pw.print("</html>");
	    	}
	    }
	    else {
	    	PrintWriter pw=response.getWriter();
	        pw.print("<html>");
	        pw.print("<body>");
	        pw.print("<p><h1>enter proper confirm password</h1></p>");
	        pw.print("<a href="+"loginpage.html"+">back to login page"+"</a>");
	        pw.print("</body>");
	        pw.print("</html>");
	    }

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
