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

import java.time.LocalDate;
import java.sql.*;

@WebServlet("/create_classroom")
public class Create_teacher_classroom extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String class_name=request.getParameter("classroom_name");
		String subject_name=request.getParameter("subject_name");
		String faculty_name=request.getParameter("faculty_name");
		String role="teacher";
		LocalDate date0=LocalDate.now();
		Date date=Date.valueOf(date0);
		HttpSession session=request.getSession();
		String email_id=(String) session.getAttribute("email_id");
		String day=(date.toString()).substring(8,10);
		String month=(date.toString()).substring(5,7);
		
		String class_code=day+faculty_name.substring(0,2)+month+subject_name.substring(0,2);
		class_code=class_code.toLowerCase();
		int end=email_id.length()-10;
		   String sub=email_id.substring(0,end);
		String table_name="teacher_login_"+sub;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineclassroom","root","deepa2003");
			PreparedStatement pt=con.prepareStatement("INSERT INTO "+table_name+" VALUES(?,?,?,?,?)");
		   
		    pt.setString(1, class_name);
		    pt.setDate(2,date);
		    pt.setString(3, subject_name);
		    pt.setString(4, faculty_name);
		    pt.setString(5, class_code);
		    int u=pt.executeUpdate();
		    Statement st1=con.createStatement();
		    ResultSet rs=st1.executeQuery("SELECT no_classroom,no_teacher FROM user_details WHERE email_id='"+email_id+"'");
		    rs.next();
		    int no_classroom=rs.getInt(1)+1;
		    int no_teacher=rs.getInt(2)+1;
		    Statement st2=con.createStatement();
		    int u1=st2.executeUpdate("UPDATE user_details SET no_classroom="+no_classroom+" WHERE email_id='"+email_id+"'");
		    int u2=st2.executeUpdate("UPDATE user_details SET no_teacher="+no_teacher+" WHERE email_id='"+email_id+"'");
		    Statement st3=con.createStatement();
		    String new_table_name=class_code+"_details";
		    String query="create table `"+new_table_name+"` (name varchar(30),role varchar(10),email_id varchar(35),date_use date,class_name varchar(25),subject_name varchar(25),primary key(email_id))";
		    int u3=st3.executeUpdate(query);
		    String query2="insert into `"+new_table_name+"` values (?,?,?,?,?,?)";
		    PreparedStatement pt1=con.prepareStatement(query2);
		    pt1.setString(1, faculty_name);
		    pt1.setString(3, email_id);
		    pt1.setDate(4, date);
		    pt1.setString(2, role);
		    Date f4;
		    pt1.setString(5, class_name);
		    pt1.setString(6, subject_name);
		    int p1=pt1.executeUpdate();
	
		    File obj=new File("F:\\onlineclassroom\\"+class_code);
		    File obj1=new File("F:\\onlineclassroom\\"+class_code+"\\materials");
		    File obj2=new File("F:\\onlineclassroom\\"+class_code+"\\assignments");
		   
		    if(obj.mkdir()) {
		    	;
		    }
		    else {
		    	System.out.print("class already exists");
		    }
		    if(obj1.mkdir()) {
		    	;
		    }
		    else {
		    	System.out.print("file exists");
		    }
		    if(obj2.mkdir()) {
		    	;
		    }
		    else {
		    	System.out.println("file exists");
		    }
		    Statement st5=con.createStatement();
		    String material="materials_"+class_code;
		    String assignment="assignments_"+class_code;
		    int u4=st5.executeUpdate("create table `"+material+"` (title varchar(25),date_posted date,teacher_comment varchar(250))");
		    Statement st6=con.createStatement();
		    int u5=st6.executeUpdate("create table `"+assignment+"` (title varchar(75),date_posted date,due_date date,teacher_comment varchar(100),total_marks int)");
		    
		    RequestDispatcher rd=request.getRequestDispatcher("hjjk.jsp");
		    rd.forward(request, response);
		    
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
