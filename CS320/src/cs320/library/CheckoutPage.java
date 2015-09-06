package cs320.library;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckoutPage
 */
@WebServlet("/library/CheckoutPage")
public class CheckoutPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection connection = null;
		Statement statement = null;
		try {
			
			Class.forName(DatabaseUtility.JDBC_DRIVER);
			
			connection = DriverManager.getConnection(DatabaseUtility.URL,DatabaseUtility.user, DatabaseUtility.password);
			
			statement = connection.createStatement();
			String sql = null;
			ServletContext context = this.getServletContext();
			ArrayList<Book> books = (ArrayList<Book>)context.getAttribute("Books");
			
			String action = request.getParameter("action");
			String id = (String) request.getParameter("id");
			int bookid = 0;
			String title = null;
			int copies = 0;
			if(id != null && action == null){
				
				/*sql ="SELECT * FROM books where book_id="+Integer.parseInt(id);
				rs = statement.executeQuery(sql);
				
				while(rs.next()){
					bookid = rs.getInt("book_id");
					title = rs.getString("title");
				}*/
				
				for(Book book: books){
					if(Integer.parseInt(id) == book.getId()){
						bookid = book.getId();
						title = book.getTitle();
					}	
				}
				
				request.setAttribute("BookId", bookid);
				request.setAttribute("Title", title);
				request.getRequestDispatcher("../library/Checkout.jsp").forward(request, response);
			
			} else {
				String studentName = request.getParameter("studentName");
				String isStudentValid = "true";
				if(studentName != null && studentName.trim().length() > 0){
					sql = "INSERT into borrower VALUES ("+Integer.parseInt(id)+",'"+studentName+"',sysdate(),null)";
					statement.executeUpdate(sql);
					
					for(Book book: books){
						if(Integer.parseInt(id) == book.getId()){
							copies = book.getCopies();
							copies--;
						}	
					}
					sql = "UPDATE books SET copies="+copies+" WHERE book_id="+Integer.parseInt(id);
					statement.executeUpdate(sql);
					response.sendRedirect("MainPage");
				} else {
					for(Book book: books){
						if(Integer.parseInt(id) == book.getId()){
							request.setAttribute("BookId", book.getId());
							request.setAttribute("Title", book.getTitle());
						}
					}
					isStudentValid = "false";
					request.setAttribute("isStudentValid", isStudentValid);
					request.getRequestDispatcher("../library/Checkout.jsp").forward(request, response);
				}
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
