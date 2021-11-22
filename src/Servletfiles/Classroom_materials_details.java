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

@WebServlet("/classroom_materials_details")
public class Classroom_materials_details extends HttpServlet {
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String full=request.getParameter("title_name");
		int len=full.length();
		int sub=len-14;
		String title=full.substring(0,sub);
		HttpSession session=request.getSession();
		String class_code=(String) session.getAttribute("class_code");
		String table_name="materials_"+title+"_"+class_code;
		session.setAttribute("title", title);
		
		String[] file_name=null;
		String[] file_path=null;
		
		int n=0;
		Date[] date=null;
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineclassroom","root","deepa2003");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from `"+table_name+"`");
			rs.next();
			Statement st1=con.createStatement();
			ResultSet rs1=st1.executeQuery("select count(*) from `"+table_name+"`");
			rs1.next();
			 n=rs1.getInt(1);
			 if(n>0) {
			 file_name=new String[n];
			 file_path=new String[n];
			 
			 date=new Date[n];
			for(int i=0;i<n;i++) {
				file_name[i]=rs.getString(1);
				file_path[i]=rs.getString(2);
				
				date[i]=rs.getDate(3);
				rs.next();
			}
			
			RequestDispatcher rd=request.getRequestDispatcher("classroom_materials_details.jsp");
			request.setAttribute("file_name", file_name);
			request.setAttribute("file_path", file_path);
			
			request.setAttribute("date", date);
			request.setAttribute("title", title);
			request.setAttribute("number", n);
			rd.forward(request, response);
			System.out.print(file_name[0]);
			System.out.print(file_path[0]);
			
			System.out.print(date[0]);
			 }
			 else {
				 RequestDispatcher rd=request.getRequestDispatcher("classroom_materials_details.jsp");
				 request.setAttribute("title", title);
					request.setAttribute("date", date);
					request.setAttribute("number", n);
					request.setAttribute("file_name", file_name);
					request.setAttribute("file_path", file_path);
				 rd.forward(request, response);
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
