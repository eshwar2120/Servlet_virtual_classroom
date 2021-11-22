package Servletfiles;

import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

@WebServlet("/classroom_student_assignments_details")
public class Classroom_student_assignments_details extends HttpServlet {
	

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		String class_code=(String) session.getAttribute("class_code");
		String email_id=(String) session.getAttribute("email_id");
		String tt=request.getParameter("title_name");
		String ass_title=tt.substring(0,tt.length()-26);
		System.out.println(ass_title);
		session.setAttribute("assignment_title",ass_title);
		String path=null;
		String path1=null;
		String[] list_file=null;
		String[] list_file_stu=null;
		int n=0,n1=0;
		String stu_email_id=(String) session.getAttribute("email_id");
		session.setAttribute("stu_email_id",stu_email_id);
		String table="assignments_"+ass_title+"_"+class_code+"_stu";
		String status=null;
		int marks=0;
		Date due_date=null;
		Date date_submit=null;
		String grade_status=null;
		int done=0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineclassroom","root","deepa2003");
			Statement st=con.createStatement();
			path="F:\\onlineclassroom\\"+class_code+"\\assignments\\"+ass_title+"\\faculty";
			path1="F:\\onlineclassroom\\"+class_code+"\\assignments\\"+ass_title+"\\"+stu_email_id;
			File obj=new File(path);
			list_file=obj.list();
			File obj1=new File(path1);
			list_file_stu=obj1.list();
			n1=list_file_stu.length;
			n=list_file.length;
			ResultSet rs=st.executeQuery("select * from `"+table+"` where email_id='"+email_id+"'");
			rs.next();
			status=rs.getString(6);
			grade_status=rs.getString(7);
			marks=rs.getInt(3);
			due_date=rs.getDate(4);
			date_submit=rs.getDate(5);
			System.out.println(status);
			System.out.println(ass_title);
	
			session.setAttribute("assignment_title_submit",ass_title);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		RequestDispatcher rd=request.getRequestDispatcher("Class_student_assignments_details.jsp");
		request.setAttribute("list_file", list_file);
		request.setAttribute("n",n);
		request.setAttribute("status",status);
		request.setAttribute("marks",marks);
		request.setAttribute("grade_status",grade_status);
		request.setAttribute("due_date",due_date);
		request.setAttribute("date_submit",date_submit);
		request.setAttribute("assignment_title",ass_title);
		request.setAttribute("n1",n1);
		request.setAttribute("list_file_stu",list_file_stu);

		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
