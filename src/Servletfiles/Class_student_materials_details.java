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
 * Servlet implementation class Class_student_materials_details
 */
@WebServlet("/class_student_materials_details")
public class Class_student_materials_details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		
		String sub=request.getParameter("title_name");
		int n=sub.length();
		int m=n-14;
		String title=sub.substring(0, m);
		String class_code=(String) session.getAttribute("class_code");
		String table="materials_"+title+"_"+class_code;
		String [] file_name=null;
		int p=0;
		session.setAttribute("title", title);
		String teacher_comm=null;
		String tab="materials_"+class_code;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineclassroom","root","deepa2003");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select count(*) from `"+table+"`");
			rs.next();
			 p=rs.getInt(1);
			Statement st1=con.createStatement();
			ResultSet rs1=st1.executeQuery("select * from `"+table+"`");
			file_name=new String[n];
			for(int i=0;rs1.next();i++) {
				file_name[i]=rs1.getString(1);
			}
			Statement st2=con.createStatement();
			ResultSet rs2=st2.executeQuery("select * from "+tab+" where title='"+title+"'");
			rs2.next();
			teacher_comm=rs2.getString(3);
			System.out.println(teacher_comm);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		RequestDispatcher rd=request.getRequestDispatcher("class_student_materials_details.jsp");
		request.setAttribute("file_name",file_name);
		request.setAttribute("number", p);
		request.setAttribute("title", title);
		request.setAttribute("class", class_code);
		request.setAttribute("teacher", teacher_comm);
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
