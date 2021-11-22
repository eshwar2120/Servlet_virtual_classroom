package Servletfiles;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*
;
@WebServlet("/class_details")
public class Classroom_details extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         HttpSession session=request.getSession();
         String class_code=(String) session.getAttribute("class_code");
         String table_name=class_code+"_details";
         String role1="teacher";
         String role2="student";
         int n=0;
         String[] name=null;
         String[] role=null;
         String[] email_id=null;
         String class_name=null;
         String teacher_name=null;
         String subject_name=null;
         try {
        	 Class.forName("com.mysql.cj.jdbc.Driver");
        	 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineclassroom","root","deepa2003");
        	 Statement st=con.createStatement();
        	 ResultSet rs=st.executeQuery("select * from "+table_name+" where role='"+role2+"'");
        	 Statement st1=con.createStatement();
        	 ResultSet rs1=st1.executeQuery("select * from "+table_name+" where role='"+role1+"'");
        	 Statement st2=con.createStatement();
        	 ResultSet rs2=st2.executeQuery("select count(*) from "+table_name+" where role='"+role2+"'");
        	 rs2.next();
        	 n=rs2.getInt(1);
        	 name=new String[n];
        	 role=new String[n];
        	 email_id=new String[n];
        	 rs.next();
        	 rs1.next();
        	 for(int i=0;i<n;i++) {
        		
        		 name[i]=rs.getString(1);
        		 role[i]=rs.getString(2);
        		 email_id[i]=rs.getString(3);
        		 rs.next();
        	 }
        	 class_name=rs1.getString(5);
        	 subject_name=rs1.getString(6);
        	 teacher_name=rs1.getString(1);
        	 System.out.print(class_name);
        	 RequestDispatcher rd=request.getRequestDispatcher("classroom_details.jsp"); 
             request.setAttribute("class_name", class_name);
             request.setAttribute("subject_name", subject_name);
             request.setAttribute("teacher_name", teacher_name);
             request.setAttribute("name", name);
             request.setAttribute("role", role);
             request.setAttribute("email_id", email_id);
             request.setAttribute("class_code", class_code);
             request.setAttribute("no_students", n);
             rd.forward(request, response);
         }
         catch(Exception e) {
        	 System.out.print(e);
         }
         RequestDispatcher rd=request.getRequestDispatcher("classroom_details.jsp");
         request.setAttribute("name", name);
         request.setAttribute("class_name", class_name);
         request.setAttribute("subject_name", subject_name);
         request.setAttribute("email_id", email_id);
         request.setAttribute("class_code", class_code);
         request.setAttribute("role", role);
         request.setAttribute("teacher_name", teacher_name);
         request.setAttribute("no_students", n);
         rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
