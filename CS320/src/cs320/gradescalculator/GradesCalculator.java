package cs320.gradescalculator;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GradesCalculator
 */
@WebServlet("/gradescalculator/GradesCalculator")
public class GradesCalculator extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		ServletContext context = this.getServletContext();
		PrintWriter out = response.getWriter();
		
		double marks = (Double) context.getAttribute("finalMarks");
		String grade = (String) context.getAttribute("yourGrade");
		String gender = (String) context.getAttribute("gender");
		String[] hobbies = (String[]) context.getAttribute("hobbies");
		
		out.println("My Final Marks : "+marks);
		out.println("<br/>My Grade : "+grade);
		out.println("</br>Gender : "+gender);
		out.println("<br/>My Hobbies :");
		for(int i=0;i<hobbies.length;i++)
			out.println(hobbies[i]);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
