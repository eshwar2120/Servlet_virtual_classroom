package Servletfiles;

import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.sql.*;
@WebServlet("/add_material")
public class Add_material extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String title=request.getParameter("material_title");
		String teacher_comment=request.getParameter("teacher_comment");
		HttpSession session=request.getSession();
		String class_code=(String) session.getAttribute("class_code");
		String table="materials_"+class_code;
		String new_table="materials_"+title+"_"+class_code;
		LocalDate local_date=LocalDate.now();
		Date date=Date.valueOf(local_date);
		String file_name=null;
		String path=null;
		String tea_comment=null;
		String stu_comment=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineclassroom","root","deepa2003");
			PreparedStatement pt=con.prepareStatement("insert into "+table+" values(?,?,?)");
			pt.setString(1, title);
			pt.setDate(2, date);
			pt.setString(3, teacher_comment);
			
			int u=pt.executeUpdate();
			Statement st=con.createStatement();
			String query="create table `"+new_table+"`(file_name varchar(75),path varchar(95),data_posted date)";
			int u1=st.executeUpdate(query);
			
			String directory="F:\\onlineclassroom\\"+class_code+"\\materials\\"+title;
			File obj=new File(directory);
			if(obj.mkdir()) {
				;
			}
			else {
				System.out.println("exists");
			}
			System.out.println(title);
		}
		catch(Exception e) {
			System.out.println(e);
			System.out.println(title);
		}
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
