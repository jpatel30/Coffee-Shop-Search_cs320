package cs320.lab3;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;


@WebServlet("/lab3/Login")
public class CookieLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("userName");
		String password = request.getParameter("password");
		boolean remember = "true".equals(request.getParameter("remember"));
		String name = null;
				
		out.println("<html><head><title>Login</title></head>");
		out.println("<body>");
		out.println("<form action=\"Login\" method=\"post\">");
		out.println("<table><tr>");
		out.println("<td>User Name : </td><td><input type = \"text\" name=\"userName\" tabindex=\"1\" autofocus");
		if(username == null){
			Cookie[] cookies = request.getCookies();
			
			if(cookies != null){
				for(Cookie cookie : cookies){
					if("Remember-me".equals(cookie.getName())){
						name = cookie.getValue();
						byte[] decode = Base64.decodeBase64(name);
						out.println("value =\"" +new String(decode)+"\"></td>");
						break;
					} else{
						out.println("value =\"\"></td>");
					}
				}
			}
		}
		out.println("</tr><tr>");
		out.println("<td>Password : </td><td><input type = \"password\" name=\"password\" tabindex=\"2\"></td>");
		out.println("</tr><tr>");
		out.println("<td>Remember Me : </td><td><input type = \"checkbox\" name=\"remember\" value=\"true\" tabindex=\"3\"");
		if(name != "" && name != null)
			out.println("checked></td>");
		out.println("</tr><tr>");
		out.println("<td><input type = \"submit\" value = \"Login\" tabindex=\"4\"></td>");
		out.println("</tr><tr>");
		out.println("</tr></table></form>");
		out.println("</body></html>");
		
		if((username != null && username != "") && (password != null && password != "") && remember){
			
			String encode = Base64.encodeBase64String(username.getBytes());
			Cookie c = new Cookie("Remember-me", encode);
			c.setMaxAge(60);
			response.addCookie(c);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
