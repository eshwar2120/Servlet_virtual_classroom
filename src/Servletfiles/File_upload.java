package Servletfiles;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
import java.time.LocalDate;
/**
 * Servlet implementation class File_upload
 */
@WebServlet("/file_upload")
public class File_upload extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String file_name=null;
		HttpSession session=request.getSession();
		
	    String class_code=(String) session.getAttribute("class_code");
	    
	    String title=(String) session.getAttribute("title");
	    String path="F:\\onlineclassroom\\"+class_code+"\\materials\\"+title;
		path=path+"\\";
	    String material_table="materials_"+title+"_"+class_code;
	    
        try {
        	ServletFileUpload sf=new ServletFileUpload(new DiskFileItemFactory());
			List<FileItem> file=sf.parseRequest(new ServletRequestContext(request));
			for(FileItem i:file) {
				String f=path+i.getName();
				i.write(new File(f));
				file_name=file.get(0).getName();
			}
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineclassroom","root","deepa2003");
			PreparedStatement pt=con.prepareStatement("insert into `"+material_table+"` values(?,?,?)");
			pt.setString(1,file_name );
			pt.setString(2, path);
			String teacher=null;
			String student=null;
			Date date=null;
			LocalDate dd=LocalDate.now();
			date=Date.valueOf(dd);
			pt.setDate(3, date);
			
			int u=pt.executeUpdate();
			
        }
        catch(Exception e) {
        	System.out.println(e);
        	System.out.println(path);
        	System.out.println(file_name);
        	System.out.println(title);
        }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
