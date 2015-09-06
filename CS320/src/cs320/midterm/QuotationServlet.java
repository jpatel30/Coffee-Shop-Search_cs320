package cs320.midterm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/midterm/QuotationServlet")
public class QuotationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext context = this.getServletContext();
		ArrayList<Quotation> quoteList = (ArrayList<Quotation>)context.getAttribute("Quotations");
		int noOfQuotes = quoteList.size();
				
		int randomNumber =0;
		randomNumber =  1 + (int)(Math.random()*(noOfQuotes-1));  
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("Quote : " +quoteList.get(randomNumber).getQuote());
		out.println("Author : " +quoteList.get(randomNumber).getAuthor());
		int count = quoteList.get(randomNumber).getCount();
		quoteList.get(randomNumber).setCount(count+1);
		out.println("<html></head></titile><body><form name=\"generate\" method=\"post\" action=\"QuotationServlet\">");
		out.println("<input type=\"submit\" value=\"next\">");
		out.println("<a href=\"QuotationAdminServlet\">Admin Servlet</a>");
		out.println("</form></body></html>");
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
