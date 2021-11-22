package Servletfiles;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;


@WebServlet("/generate_qrcode")
public class Qrcode extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=(String)request.getAttribute("student_name");
		String marks=(String)request.getAttribute("student_marks");
		String file_name=name+".png";
		try {
		    
		    ByteArrayOutputStream out=QRCode.from(marks).to(ImageType.PNG).stream();
		    File obj=new File("E:\\"+file_name);
		    FileOutputStream fos=new FileOutputStream(obj);
		    fos.write(out.toByteArray());
		    fos.flush();
		    String filepath="E:\\firstpage.jpg";
		    Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+filepath);
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
