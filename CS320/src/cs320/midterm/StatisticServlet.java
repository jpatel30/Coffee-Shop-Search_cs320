package cs320.midterm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StatisticServlet
 */
@WebServlet("/midterm/StatisticServlet")
public class StatisticServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		ServletContext context = this.getServletContext();
		ArrayList<Quotation> quotationList = (ArrayList<Quotation>) context.getAttribute("Quotations");
		
		out.println("<html><head><title>Quotation Generator</title></head><body>");
		out.println("<table border=1>");
		out.println("<tr><th>Quote</th><th>Author</th><th>Frequency</th></tr>");
		
		for(int i =0; i<quotationList.size();i++){
			out.println("<tr>");
			out.println("<td>" +quotationList.get(i).getQuote()+ "</td>");
			out.println("<td>" +quotationList.get(i).getAuthor()+ "</td>");
			out.println("<td>" +quotationList.get(i).getCount()+ "</td>");
			out.println("<tr>");
		}
		out.println("</table></br>");
		out.println("<a href=\"QuotationAdminServlet\">Admin Servlet</a>");
		out.println("</body></html>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
