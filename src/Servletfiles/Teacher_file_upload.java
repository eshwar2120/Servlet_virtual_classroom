package Servletfiles;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.sql.*;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
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

/**
 * Servlet implementation class Teacher_file_upload
 */
@WebServlet("/teacher_file_upload")
public class Teacher_file_upload extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		String class_code=(String) session.getAttribute("class_code");
		String assignment_title=(String) session.getAttribute("assignment_title");
		String path="F:\\onlineclassroom\\"+class_code+"\\assignments\\"+assignment_title+"\\faculty\\";
		String file_name=null;
		String table="assignments_"+assignment_title+"_"+class_code+"_tea";
		String ass_table="assignments_"+assignment_title+"_"+class_code+"_"+"tea";
		LocalDate dt=LocalDate.now();
		Date date=Date.valueOf(dt);
		int n=0;
		String[] title_list=null;
		try {
        	ServletFileUpload sf=new ServletFileUpload(new DiskFileItemFactory());
			List<FileItem> file=sf.parseRequest(new ServletRequestContext(request));
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineclassroom","root","deepa2003");
			for(FileItem i:file) {
				String f=path+i.getName();
				i.write(new File(f));
				file_name=file.get(0).getName();
				PreparedStatement pt=con.prepareStatement("insert into "+ass_table+" values(?,?,?)");
				pt.setString(1, file_name);
				pt.setString(2,path);
				pt.setDate(3,date);
				int u=pt.executeUpdate();
				File obj=new File(path);
				
				String[] ti=obj.list();
				 n=ti.length;
				   title_list=new String[n];
				   for(int j=0;j<n;j++) {
					   title_list[j]=ti[j];
				   }
			}
		
		}
			catch(Exception e) {
				System.out.println(e);
			}

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
