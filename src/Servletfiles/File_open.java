package Servletfiles;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class File_open
 */
@WebServlet("/file_open")
public class File_open extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session=request.getSession();
String class_code=(String) session.getAttribute("class_code");
	    
	    String title=(String) session.getAttribute("title");
	    String file_path="F:\\onlineclassroom\\"+class_code+"\\materials\\"+title;
		file_path=file_path+"\\";

		String file_name=request.getParameter("file_name");
		String file_object=file_path+"\\"+file_name;
		Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+file_object);
		System.out.println(file_object);
		System.out.println(file_name);
		System.out.println(file_path);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
