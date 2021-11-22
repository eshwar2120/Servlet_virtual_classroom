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

/**
 * Servlet implementation class Graded_list
 */
@WebServlet("/graded_list")
public class Graded_list extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		String class_code=(String) session.getAttribute("class_code");
		String assignment_title=(String) session.getAttribute("assignment_title");
		String table="assignments_"+assignment_title+"_"+class_code+"_stu";
		String[] name=null;
		String[] email_id=null;
		int[] marks_list=null;
		int n=0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineclassroom","root","deepa2003");
			Statement st=con.createStatement();
			Statement st1=con.createStatement();
			ResultSet rs=st.executeQuery("select name,email_id,marks from `"+table+"` where grade_status='YES'");
			ResultSet rs1=st1.executeQuery("select count(name) from `"+table+"` where grade_status='YES'");
			rs.next();
			rs1.next();
			n=rs1.getInt(1);
			name=new String[n];
			email_id=new String[n];
			marks_list=new int[n];
			for(int i=0;i<n;i++) {
				name[i]=rs.getString(1);
				email_id[i]=rs.getString(2);
				marks_list[i]=rs.getInt(3);
				rs.next();
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		RequestDispatcher rd=request.getRequestDispatcher("graded_list.jsp");
		request.setAttribute("name",name);
		request.setAttribute("email_ids",email_id);
		request.setAttribute("num_ass",n);
		request.setAttribute("marks_list",marks_list);
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
