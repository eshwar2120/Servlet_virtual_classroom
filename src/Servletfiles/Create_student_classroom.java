package Servletfiles;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;
import java.time.LocalDate;
/**
 * Servlet implementation class Create_student_classroom
 */
@WebServlet("/create_student_classroom")
public class Create_student_classroom extends HttpServlet {
	

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String class_code=request.getParameter("class_code");
		String name=request.getParameter("name");
		String table_name=class_code+"_"+"details";
		String role="student";
		HttpSession session=request.getSession();
		String email_id=(String) session.getAttribute("email_id");
		LocalDate date0=LocalDate.now();
		Date date=Date.valueOf(date0);
		int end=email_id.length()-10;
		String sub1=email_id.substring(0,end);
		String table_name1="student_login_"+sub1;
		String ass_class_code="assignments_"+class_code;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineclassroom","root","deepa2003");
			
			
			Statement st3=con.createStatement();
			String teacher="teacher";
			ResultSet rs1=st3.executeQuery("select class_name,subject_name from "+table_name+" where role='"+teacher+"'");
			rs1.next();
			String classname=rs1.getString(1);
			String subject=rs1.getString(2);
			PreparedStatement pt1=con.prepareStatement("insert into "+table_name1+" values(?,?,?,?)");
			pt1.setString(1, classname);
			pt1.setString(3, subject);
			pt1.setDate(2, date);
			pt1.setString(4, class_code);
			int u3=pt1.executeUpdate();
			PreparedStatement pt=con.prepareStatement("insert into "+table_name+" values(?,?,?,?,?,?)");
			pt.setString(1, name);
			pt.setString(2, role);
			pt.setString(3, email_id);
			pt.setDate(4, date);
			pt.setString(5, classname);
			pt.setString(6, subject);
			int u=pt.executeUpdate();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select no_classroom,no_student from user_details where email_id='"+email_id+"'");
			rs.next();
			int no_classroom=rs.getInt(1);
			no_classroom=no_classroom+1;
			int no_student=rs.getInt(2);
			no_student=no_student+1;
			Statement st1=con.createStatement();
			int u1=st1.executeUpdate("update user_details set no_classroom="+no_classroom+" where email_id='"+email_id+"'");
			Statement st2=con.createStatement();
			int u2=st2.executeUpdate("update user_details set no_student="+no_classroom+" where email_id='"+email_id+"'");
			Statement st4=con.createStatement();
			ResultSet rs4=st4.executeQuery("select title from `"+ass_class_code+"`");
			Statement st5=con.createStatement();
			ResultSet rs5=st5.executeQuery("select count(title) from `"+ass_class_code+"`");
			rs5.next();
			rs4.next();
			int n=rs5.getInt(1);
			String[] title=new String[n];
			for(int i=0;i<n;i++) {
				title[i]=rs4.getString(1);
				rs4.next();
			}
			
			File[] obj2=new File[n];
			for(int i=0;i<n;i++) {
				String directory="F:\\onlineclassroom\\"+class_code+"\\assignments\\";
				directory=directory+title[i]+"\\"+email_id;
				 obj2[i]=new File(directory);
				 System.out.println("hi");
				if(obj2[i].mkdir()) {
					;
				}
				else {
					System.out.print("not created");
				}
				String student_ass="assignments_"+title[i]+"_"+class_code+"_stu";
				String assi="assignments_"+class_code;
				Statement st6=con.createStatement();
				ResultSet rs6=st6.executeQuery("select date_posted,due_date from "+assi+" where title='"+title[i]+"'");
				rs6.next();
				Date date_posted=null;
				Date due_date=rs6.getDate(2);
				PreparedStatement pt5=con.prepareStatement("insert into `"+student_ass+"` values(?,?,?,?,?,?,?)");
				pt5.setString(1,email_id);
				pt5.setString(2,name);
				int marks=0;
				String status="NO";
				pt5.setInt(3,marks);
				pt5.setDate(4,due_date);
				pt5.setDate(5,date_posted);
				pt5.setString(6,status);
				pt5.setString(7, status);
				int u5=pt5.executeUpdate();
				System.out.println(n);
				System.out.println(u5);
			}
		}
		catch(Exception e) {
			System.out.print(e);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
