package cs320.Homework2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CoffeeLogin
 */
@WebServlet("/Homework2/Login")
public class CoffeeLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	public void init() throws ServletException {
		
		ServletContext context = this.getServletContext();
		
		if(context.getAttribute("coffeeUsers") == null){
			ArrayList<CoffeeUsers> coffeeUsers = new ArrayList<CoffeeUsers>();
			coffeeUsers.add(new CoffeeUsers("john@doe.com", "aa1!", "John", "Doe", "90071"));
			coffeeUsers.add(new CoffeeUsers("joe@boxer.com", "bb2@", "Joe", "Boxer", "92101"));
			context.setAttribute("coffeeUsers", coffeeUsers);
		}
		if ( context.getAttribute("CoffeeShops") == null ){
			ArrayList<CoffeeShop> coffeeShops = new ArrayList<CoffeeShop>();
			String file = this.getServletContext().getRealPath("/") + "Starbucks-tab-delimited2.txt";
			try{
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line;
				while ((line = br.readLine()) != null) {
				   String[] tokens = line.split("\t");
				   coffeeShops.add(new CoffeeShop(tokens[1].replace('"', ' ').trim(), tokens[3].trim(), tokens[4].trim(), "CA",
						   tokens[5].trim(), tokens[2].trim(), Double.parseDouble(tokens[6]), Double.parseDouble(tokens[7]) ) );
				} 
				br.close();
				
				context.setAttribute("CoffeeShops", coffeeShops);
			}catch(IOException e){
				e.printStackTrace();
			}			
		}
		
	} 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if ( action != null && action.equals("logout") ){
			HttpSession session = request.getSession();
			session.invalidate(); // Invalidate the session
		}
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		boolean hasErrors = true;
		boolean userNameValid = false;
		boolean passwordValid = false;
		String userName = null;
		String password = null;
		String userPassword = null;
		
		String pattern = "^[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,}$";
		
		ArrayList<CoffeeUsers> coffeeUsers = (ArrayList<CoffeeUsers>) this.getServletContext().getAttribute("coffeeUsers");
		
		userName = request.getParameter("userName");
			if(userName != null){
				if(userName != "" && userName.matches(pattern)){
					for(CoffeeUsers user : coffeeUsers){
						if(userName.equals(user.getUserName())){
							userNameValid = true;
							userPassword = user.getPassword();
						}
					}
				}
			}
		
		password = request.getParameter("password");
			if(password != null){
				if(password != ""){
					if(password.equals(userPassword)){
						passwordValid = true;
					}					
				}
			}
		
		out.println("<html><head><title>Login</title></head>");
		out.println("<body>");
		out.println("<form action=\"Login\" method=\"post\">");
		out.println("<table><tr>");
		out.println("<td>User Name : </td><td><input type = \"text\" name=\"userName\" tabindex=\"1\" autofocus");
		if(userNameValid){
			out.println(" value =\"" +userName+ "\"></td>");
			hasErrors = false;
		} else if (userName !=null){
			out.println(" style=\"border-color: red\"></td>");
			out.println("<td><h5 style= \"color: red\">User name is invalid</h5></td>");
		}
		out.println("</tr><tr>");
		out.println("<td>Password : </td><td><input type = \"password\" name=\"password\" tabindex=\"2\" ");
		if(!passwordValid && password != null){
			out.println(" style=\"border-color: red\"></td>");
			out.println("<td><h5 style= \"color: red\">Password is invalid</h5></td>");
			hasErrors = true;
		}
		out.println("</tr><tr>");
		out.println("<td><input type = \"submit\" value = \"Login\" tabindex=\"3\"></td>");
		out.println("</tr><tr>");
		out.println("</tr></table></form>");
		out.println("</body></html>");
		
		if(!hasErrors){
			
			for (CoffeeUsers user : coffeeUsers){
				if ( user.isUser(userName.trim(), password.trim() ) ){
					HttpSession session = request.getSession();					
					session.setAttribute("currentCoffeeUser", user);
					response.sendRedirect("SearchCoffeeShops");
					break;
				}
			}
		}
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
