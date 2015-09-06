package cs320.library;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/library/AddBook")
public class AddBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean isTitleValid = false;
		boolean isCopiesValid = false;
		
		Connection connection = null;
		Statement statement = null;
		
		try{
			Class.forName(DatabaseUtility.JDBC_DRIVER);
		
			connection = DriverManager
				.getConnection(DatabaseUtility.URL,DatabaseUtility.user, DatabaseUtility.password);
	
			String title = request.getParameter("title");
			String copies = request.getParameter("copies");
			
			if(title !=null && title.trim().length() > 0){
				isTitleValid = true;
			}
			if(copies != null && copies.trim().length() > 0){
				isCopiesValid = true;
			}
			
			if(isTitleValid && isCopiesValid){
				String sql = "INSERT into books VALUES (null,'"+title+"',"+Integer.parseInt(copies)+")";
				statement = connection.createStatement();
				statement.executeUpdate(sql);
				response.sendRedirect("MainPage");
					
			} else {
				response.sendRedirect("../library/AddBook.jsp?title="+isTitleValid+"&copies="+isCopiesValid+"");
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				if(connection != null)
					connection.close();
				} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
