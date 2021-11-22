package Servletfiles;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/open_assignment_award")
public class Open_assignment_award extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String file_name=request.getParameter("file_name");
		HttpSession session=request.getSession();
		String class_code=(String) session.getAttribute("class_code");
		String title=(String) session.getAttribute("assignment_title");
		String stu_email_id=(String)session.getAttribute("stu_email_id");
		String path="F:\\onlineclassroom\\"+class_code+"\\assignments\\"+title+"\\"+stu_email_id;
		String file_open=path+"\\"+file_name;
		System.out.println(stu_email_id);
		Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+file_open);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
