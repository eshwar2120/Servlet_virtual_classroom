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
 * Servlet implementation class Class_student_materials
 */
@WebServlet("/class_student_materials")
public class Class_student_materials extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		String email_id=(String) session.getAttribute("email_id");
		String code=request.getParameter("code");
	    int n=code.length()-8;
	    int m=code.length();
	    String class_code=code.substring(n, m);
	    session.setAttribute("class_code", class_code);
	    String table_name="materials_"+class_code;
	    String[] title1=null;
	    Date[] date=null;
	    int p=0;
	    try {
	    	Class.forName("com.mysql.cj.jdbc.Driver");
	    	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineclassroom","root","deepa2003");
	    	Statement st=con.createStatement();
	    	ResultSet rs=st.executeQuery("select * from "+table_name);
	    	Statement st1=con.createStatement();
	    	ResultSet rs1=st1.executeQuery("select count(*) from "+table_name);
	    	rs1.next();
	    	 p=rs1.getInt(1);
	    	title1=new String[p];
	    	date=new Date[p];
	    	for(int i=0;rs.next();i++) {
	    		title1[i]=rs.getString(1);
	    		date[i]=rs.getDate(2);
	    		
	    	}
	    }
	    catch(Exception e) {
	    	System.out.println(e);
	    }
	    RequestDispatcher rd=request.getRequestDispatcher("class_student_materials.jsp");
	    request.setAttribute("title", title1);
	    request.setAttribute("date", date);
	    request.setAttribute("number", p);
	    rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
