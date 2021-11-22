package Servletfiles;

import java.io.IOException;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Classroom_assignments_details
 */
@WebServlet("/classroom_assignments_details")
public class Classroom_assignments_details extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	  String tt=request.getParameter("title_name");
	  String title=tt.substring(0,tt.length()-55);
	  HttpSession session=request.getSession();
	  session.setAttribute("assignment_title", title);
	  int assigned=0;
	  int turned_in=0;
	  int graded=0;
	  int not_graded=0;
	  String class_code=(String) session.getAttribute("class_code");
	  String table="assignments_"+title+"_"+class_code+"_tea";
	  String[] title_list=null;
	  String table1="assignments_"+title+"_"+class_code+"_stu";
	  int n=0;
	  try {
	  Class.forName("com.mysql.cj.jdbc.Driver");
	  Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineclassroom","root","deepa2003");
	  Statement st=con.createStatement();
	  Statement st1=con.createStatement();
		ResultSet rs=st.executeQuery("select file_name from `"+table+"`");
	   ResultSet rs1=st1.executeQuery("select count(file_name) from `"+table+"`");
	   rs1.next();
	   rs.next();
	    n=rs1.getInt(1);
	   title_list=new String[n];
	   for(int i=0;i<n;i++) {
		   title_list[i]=rs.getString(1);
		   rs.next();
	   }
	   Statement st2=con.createStatement();
	   Statement st3=con.createStatement();
	   Statement st4=con.createStatement();
	   Statement st5=con.createStatement();

	   ResultSet rs2=st2.executeQuery("select count(*) from `"+table1+"` where status='NO'");
	   ResultSet rs3=st3.executeQuery("select count(*) from `"+table1+"` where status='YES'");
	   ResultSet rs4=st4.executeQuery("select count(*) from `"+table1+"` where grade_status='NO'");
	   ResultSet rs5=st5.executeQuery("select count(*) from `"+table1+"` where grade_status='YES'");
       rs2.next();
       rs3.next();
       rs4.next();
       rs5.next();
       assigned=rs2.getInt(1);
       turned_in=rs3.getInt(1);
       graded=rs5.getInt(1);
       not_graded=rs4.getInt(1);
	  }
	  catch(Exception e) {
		  ;
	  }
	  System.out.println(title);
	  RequestDispatcher rd=request.getRequestDispatcher("classroom_assignments_details.jsp");
	  System.out.println(title);
	 request.setAttribute("title_list",title_list);
	 request.setAttribute("n",n);
	 request.setAttribute("ass_title",title);
	 request.setAttribute("assigned",assigned);
	 request.setAttribute("turned_in",turned_in);
	 request.setAttribute("graded",graded);
	 request.setAttribute("not_graded",not_graded);
	  rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
