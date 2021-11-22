package Servletfiles;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.DriverManager;

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

import java.time.LocalDate;
import java.util.List;
import java.sql.*;

@WebServlet("/assignment_student_submit")
public class Assignment_student_submit extends HttpServlet {

       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		String assignment_title=(String)session.getAttribute("assignment_title_submit");
		String class_code=(String) session.getAttribute("class_code");
		String email_id=(String) session.getAttribute("email_id");
		String file_name=null;
		LocalDate dt=LocalDate.now();
		Date date_submit=Date.valueOf(dt);
		String status="YES";
		String table="assignments_"+assignment_title+"_"+class_code+"_stu";
		String path="F:\\onlineclassroom\\"+class_code+"\\assignments\\"+assignment_title+"\\"+email_id+"\\";
		try {
		ServletFileUpload sf=new ServletFileUpload(new DiskFileItemFactory());
		List<FileItem> file=sf.parseRequest(new ServletRequestContext(request));
		for(FileItem i:file) {
			String f=path+i.getName();
			i.write(new File(f));
			file_name=file.get(0).getName();
		}
		System.out.println(table);
	
		String sql="update `"+table+"` set date_submitted='"+date_submit+"' where email_id='"+email_id+"'";
		String sql1="update `"+table+"` set status='"+status+"' where email_id='"+email_id+"'";
		System.out.println(sql1);
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineclassroom","root","deepa2003");
		Statement st=con.createStatement();
		int u=st.executeUpdate(sql);
		Statement st1=con.createStatement();
		int u1=st1.executeUpdate(sql1);
		
		}
		catch(Exception e) {
			System.out.print(e);
			System.out.println(table);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
