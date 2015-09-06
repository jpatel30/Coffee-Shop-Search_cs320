package cs320.library;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainPage
 */
@WebServlet("/library/MainPage")
public class MainPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection connection = null;
		Statement statement = null;
		try{
			
			Class.forName(DatabaseUtility.JDBC_DRIVER);
			connection = DriverManager
						.getConnection(DatabaseUtility.URL,DatabaseUtility.user, DatabaseUtility.password);
			
			ServletContext context = this.getServletContext();
			ArrayList<Book> books = new ArrayList<Book>();
			
			String sql = "SELECT * FROM books";
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()){
				books.add(new Book(rs.getInt("book_id"), rs.getString("title"), rs.getInt("copies")));
			}
			
			context.setAttribute("Books", books);
			response.sendRedirect("../library/MainPage.jsp");
			
		} catch (Exception e){
			e.printStackTrace();
		
		} finally{
			try {
				if(connection != null){
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
