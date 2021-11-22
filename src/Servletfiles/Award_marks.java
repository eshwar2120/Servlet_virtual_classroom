package Servletfiles;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

@WebServlet("/award_marks")
public class Award_marks extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String student_email_id=request.getParameter("stu_email_id");
		HttpSession session=request.getSession();
		session.setAttribute("stu_email_id",student_email_id);
		String class_code=(String) session.getAttribute("class_code");
		String assignment_title=(String)session.getAttribute("assignment_title");
		String table="assignments_"+assignment_title+"_"+class_code+"_stu";
		String path="F:\\onlineclassroom\\"+class_code+"\\assignments\\"+assignment_title+"\\"+student_email_id;
		String[] file_list=null;
		String table1="assignments_"+class_code;
		int n=0;
		Date due_date=null;
		int total_marks=0;
		String stu_name=null;
		Date date_submitted=null;
		try {
			File obj=new File(path);
			
			file_list=obj.list();
			n=file_list.length;
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineclassroom","root","deepa2003");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select total_marks,due_date from `"+table1+"` where title='"+assignment_title+"'");
			rs.next();
			total_marks=rs.getInt(1);
			due_date=rs.getDate(2);
			Statement st1=con.createStatement();
			ResultSet rs1=st1.executeQuery("select date_submitted,name from `"+table+"` where email_id='"+student_email_id+"'");
			rs1.next();
			stu_name=rs1.getString(2);
			date_submitted=rs1.getDate(1);
			
		}
		catch(Exception e) {
			System.out.println(e);
			System.out.println(n);
			System.out.println(file_list[0]);
			System.out.println(assignment_title);
			System.out.println(class_code);
			System.out.println(student_email_id);
		}
		RequestDispatcher rd=request.getRequestDispatcher("award_marks.jsp");
		request.setAttribute("due_date",due_date);
		request.setAttribute("date_submitted",date_submitted);
		request.setAttribute("student_name",stu_name);
		request.setAttribute("total_marks",total_marks);
		request.setAttribute("num_files",n);
		request.setAttribute("file_list",file_list);
        rd.forward(request, response);

		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
