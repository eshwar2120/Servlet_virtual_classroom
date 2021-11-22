package Servletfiles;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
/**
 * Servlet implementation class Add_assignment
 */
@WebServlet("/add_assignment")
public class Add_assignment extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
	
		String assignment_title=request.getParameter("assignment_title");
		
		String total=request.getParameter("total_marks");
		
		int total_marks=Integer.parseInt(request.getParameter("total_marks"));
		
		String dd_post=request.getParameter("due_date");
		System.out.println(dd_post);
		String class_code=(String) session.getAttribute("class_code");
		session.setAttribute("assignment_title", assignment_title);
		String assignments="assignments_"+class_code;
		String teacher_comment=null;
		LocalDate date=LocalDate.now();
		Date date_posted=Date.valueOf(date);
		String table1="assignments_"+assignment_title+"_"+class_code+"_tea";
		String table2="assignments_"+assignment_title+"_"+class_code+"_stu";
		String table3=class_code+"_details";
		Date due_date=Date.valueOf(dd_post);
	
		try {
			String role="student";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineclassroom","root","deepa2003");
			PreparedStatement pt=con.prepareStatement("insert into `"+assignments+"` values(?,?,?,?,?)");
			pt.setString(1, assignment_title);
			pt.setDate(3, due_date);
			pt.setDate(2, date_posted);
			pt.setString(4, teacher_comment);
			pt.setInt(5,total_marks);
		    int u=pt.executeUpdate();
		    Statement st=con.createStatement();
		    int u1=st.executeUpdate("create table `"+table1+"`(file_name varchar(50),file_path varchar(200),date_posted date)");
			Statement st1=con.createStatement();
			int u2=st1.executeUpdate("create table `"+table2+"`(email_id varchar(50),name varchar(50),marks int(3),due_date date,date_submitted date,status varchar(3),grade_status varchar(3))");
		    PreparedStatement pt1=con.prepareStatement("insert into `"+table2+"` values(?,?,?,?,?,?,?)");
		    Statement st2=con.createStatement();
		    Statement st3=con.createStatement();
		    ResultSet rs=st2.executeQuery("select name,email_id from `"+table3+"` where role='"+role+"'");
		    ResultSet rs1=st3.executeQuery("select count(*) from `"+table3+"` where role='"+role+"'");
		    rs1.next();
		    rs.next();
		    int n=rs1.getInt(1);
		    String[] name=new String[n];
		    String[] email_id=new String[n];
		    for(int i=0;i<n;i++) {
		    	name[i]=rs.getString(1);
		    	email_id[i]=rs.getString(2);
		    	rs.next();
		    }
		    int mar=0;
		    Date ds=null;
		    String status="NO";
		    con.setAutoCommit(false);
		    for(int i=0;i<n;i++) {
		    pt1.setString(2, name[i]);
		    pt1.setString(1, email_id[i]);
		    pt1.setInt(3, mar);
		    pt1.setDate(4,due_date);
		    pt1.setDate(5, ds);
		    pt1.setString(6, status);
		    pt1.setString(7, status);
		    pt1.addBatch();
		    }
		    int[] u3=pt1.executeBatch();
		    con.commit();
		    con.setAutoCommit(true);
		    
		    String ass_directory="F:\\onlineclassroom\\"+class_code+"\\assignments\\"+assignment_title;
		    File obj1=new File(ass_directory);
		    if(obj1.mkdir()) {
		    	;
		    }
		    else {
		    	System.out.println("folder exists");
		    }
		    String directory="F:\\onlineclassroom\\"+class_code+"\\assignments\\"+assignment_title+"\\faculty";
		    File obj=new File(directory);
		    if(obj.mkdir()) {
		    	;
		    }
		    else {
		    	System.out.println("file exists");
		    }
		    
		    File[] obj2=new File[n];
		   for(int i=0;i<n;i++) {
			   System.out.println(n+"hi");
			   String folder="F:\\onlineclassroom\\"+class_code+"\\assignments\\"+assignment_title;
			   folder=folder+"\\"+email_id[i];
			   obj2[i]=new File(folder);
			   if(obj2[i].mkdir()) {
				   ;
			   }
			   else {
				   System.out.println("folder exists");
			   }
		   }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		

	}

}
