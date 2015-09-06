package cs320.lab2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/lab2/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public void init() throws ServletException {
		ServletContext context = this.getServletContext();
		if(context.getAttribute("userList") == null){
			ArrayList<User> user = new ArrayList<User>();
			user.add(new User("Joe","joe@boxer.com", "aa1!"));
			user.add(new User("John","john@doe.com", "bb2@"));
			context.setAttribute("userList", user);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean hasErrors = true;
		boolean userNameValid = false;
		boolean passwordValid = false;
		String userName = null;
		String password = null;
		String fName = null;
		String userPassword = null;
		
		String pattern = "^[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,}$";
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		ServletContext context = this.getServletContext();
			
		
		ArrayList<User> userList = (ArrayList<User>) context.getAttribute("userList");
		
		userName = request.getParameter("userName");
			if(userName != null){
				if(userName != "" && userName.matches(pattern)){
					for(User user : userList){
						if(userName.equals(user.getEmail())){
							userNameValid = true;
							fName = user.getfName();
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
		out.println("<td><a href = \"../Homework1/Registration\"> Click here to register </a></td>");
		out.println("</tr></table></form>");
		out.println("</body></html>");
		
		if(!hasErrors){
			request.setAttribute("firstName", fName);
			
			response.sendRedirect("Welcome?firstName="+fName);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
