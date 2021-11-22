package Servletfiles;

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

@WebServlet("/classroom_student_assignments")
public class Classroom_student_assignments extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		String class_code=(String) session.getAttribute("class_code");
		String table="assignments_"+class_code;
		int n=0;
		String[] ass_title=null;
		Date[] date_posted=null;
		Date[] due_date=null;
		String[] teacher_comment=null;
		int[] total_marks=null;
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineclassroom","root","deepa2003");
		Statement st=con.createStatement();
		Statement st1=con.createStatement();
		ResultSet rs=st.executeQuery("select * from `"+table+"`");
		ResultSet rs1=st1.executeQuery("select count(*) from `"+table+"`");
		rs1.next();
		rs.next();
	    n=rs1.getInt(1);
		ass_title=new String[n];
		date_posted=new Date[n];
		due_date=new Date[n];
		teacher_comment=new String[n];
		total_marks=new int[n];
		for(int i=0;i<n;i++) {
			ass_title[i]=rs.getString(1);
			date_posted[i]=rs.getDate(2);
			due_date[i]=rs.getDate(3);
			teacher_comment[i]=rs.getString(4);
			total_marks[i]=rs.getInt(5);
			System.out.println(ass_title[i]+date_posted[i]);
			
			rs.next();
		}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		RequestDispatcher rd=request.getRequestDispatcher("classroom_student_assignments.jsp");
		request.setAttribute("ass_title",ass_title);
		request.setAttribute("date_posted",date_posted);
		request.setAttribute("due_date",due_date);
		request.setAttribute("teacher_comment",teacher_comment);
		request.setAttribute("total_marks",total_marks);
		request.setAttribute("n",n);
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
