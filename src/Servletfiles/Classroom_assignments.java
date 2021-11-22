package Servletfiles;

import java.io.IOException;
import java.sql.DriverManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

@WebServlet("/class_assignment")
public class Classroom_assignments extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		String class_code=(String) session.getAttribute("class_code");
		String table="assignments_"+class_code;
		int n=0;
		String[] title;
		Date[] date_posted;
		Date[] due_date;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineclassroom","root","deepa2003");
			Statement st=con.createStatement();
			Statement st1=con.createStatement();
			ResultSet rs=st.executeQuery("select title,date_posted,due_date from `"+table+"`");
			ResultSet rs1=st1.executeQuery("select count(*) from `"+table+"`");
			rs1.next();
			n=rs1.getInt(1);
			title=new String[n];
			date_posted=new Date[n];
			due_date=new Date[n];
			rs.next();
			for(int i=0;i<n;i++) {
				title[i]=rs.getString(1);
				date_posted[i]=rs.getDate(2);
				due_date[i]=rs.getDate(3);
				rs.next();
			}
			RequestDispatcher rd=request.getRequestDispatcher("classroom_assignments.jsp");
			request.setAttribute("title", title);
			request.setAttribute("date_posted", date_posted);
			request.setAttribute("due_date", due_date);
			request.setAttribute("n",n);
			rd.forward(request, response);
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
