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
import java.time.LocalDate;
/**
 * Servlet implementation class Classroom_materials
 */
@WebServlet("/classes")
public class Classroom_materials extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String class_details=request.getParameter("class");
		int len=class_details.length();
		int start=len-8;
		String class_code=class_details.substring(start, len);
		String material_table="materials_"+class_code;
		String [] material_title=null;
		Date [] date=null;
		String class_name=null;
		String subject_name=null;
		int n=0;
		HttpSession session=request.getSession();
		session.setAttribute("class_code",class_code);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineclassroom","root","deepa2003");
			Statement st=con.createStatement();
			
			ResultSet rs1=st.executeQuery("select count(*) from "+material_table+" where title is not null");
			rs1.next();
			 n=rs1.getInt(1);
			material_title=new String[n];
			date=new Date[n];
			ResultSet rs=st.executeQuery("select * from "+material_table+" where title is not null");
			rs.next();
			for(int i=0;i<n;i++) {
				material_title[i]=rs.getString(1);
				date[i]=rs.getDate(2);
				rs.next();
			}
			Statement st1=con.createStatement();
			String details=class_code+"_details";
			String role="teacher";
			ResultSet rs2=st1.executeQuery("select class_name,subject_name from "+details+" where role='"+role+"'");
			rs2.next();
			 class_name=rs2.getString(1);
			 subject_name=rs2.getString(2);
		}
		catch(Exception e) {
			System.out.print(e);
		}
		
		
		
		request.setAttribute("material_title", material_title);
		request.setAttribute("date", date);
		request.setAttribute("class_name",class_name);
		request.setAttribute("subject_name", subject_name);
		request.setAttribute("number", n);
		RequestDispatcher rd=request.getRequestDispatcher("classroom_materials.jsp");
		
		rd.forward(request, response);
}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
