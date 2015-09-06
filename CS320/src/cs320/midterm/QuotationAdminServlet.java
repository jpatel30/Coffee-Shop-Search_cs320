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


@WebServlet("/midterm/QuotationAdminServlet")
public class QuotationAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	public void init() throws ServletException {
		ServletContext context = this.getServletContext();
   		
   		if(context.getAttribute("Quotations") == null){
   			ArrayList<Quotation> quotationList = new ArrayList<Quotation>();
   			
   			quotationList.add(new Quotation("Hello World","Palak",0));
   			quotationList.add(new Quotation("Welcome to Java Programming","Ankit",0));
   			quotationList.add(new Quotation("Welcome to Web Programming","Pranav",0));
   			quotationList.add(new Quotation("Welcome to Operating System","Darshan",0));
   			quotationList.add(new Quotation("This is a good day","Rushab",0));
   			quotationList.add(new Quotation("Today is midterm","Dashang",0));
   			context.setAttribute("Quotations", quotationList);
   		}
   		
   		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		ServletContext context = this.getServletContext();
		ArrayList<Quotation> quotationList = (ArrayList<Quotation>) context.getAttribute("Quotations");
		
		out.println("<html><head><title>Quotation Generator</title></head><body>");
		out.println("<table border=1>");
		out.println("<tr><th>Quote</th><th>Author</th><th>Edit</th></tr>");
		
		String quote = request.getParameter("quote");
		String author = request.getParameter("author");
		String remove = request.getParameter("action");
		String value = request.getParameter("index");
		boolean quoteValid = true;
		boolean authorValid = true;
		
		if(quote !=null && quote.trim().length()>0 && author !=null && author.trim().length() > 0){
			quotationList.add(new Quotation(quote, author, 0));
			context.setAttribute("No of Quotes", quotationList.size());
		}
		if(quote == null || quote.equals(""))
			quoteValid = true;
		else
			quoteValid = false;
		if(author ==null || author.equals(""))
			authorValid = true;
		else
			authorValid = false;
		if(quoteValid){
			out.println("<h2 style=color:red> Please enter a quote</h2>");
		} else if(authorValid){
			out.println("<h2 style=color:red> Please enter an author name</h2>");
		}
		
		if(remove !=null && remove.trim().length() > 0){
			quotationList.remove(Integer.parseInt(value));
		}
		
		for(int i =0; i<quotationList.size();i++){
			out.println("<tr>");
			out.println("<td>" +quotationList.get(i).getQuote()+ "</td>");
			out.println("<td>" +quotationList.get(i).getAuthor()+ "</td>");
			out.println("<td><a href = \"QuotationAdminServlet?action=remove&index="+i+"\">Remove</td>");
			out.println("<tr>");
		}
		out.println("</table></br>");
		out.println("<table border =1><form name =\"addQuote\" action=\"QuotationAdminServlet\" method=\"post\">");
		out.println("<tr><td>Enter the Quotation : <input type=\"text\" name=\"quote\" placeholder=\"Enter the Quote\"></td></tr>");
		out.println("<tr><td>Enter the Author : <input type=\"text\" name=\"author\" placeholder=\"Enter the Author\"></td></tr>");
		out.println("<tr><td><input type=\"submit\" value=\"Save\"></td></tr>");
		out.println("</table></form>");
		out.println("<a href=\"QuotationServlet\">Quotation Servlet");
		out.println("&nbsp;<a href=\"StatisticServlet\">Statistic Servlet");
		out.println("</body></html>");
		
	}		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
