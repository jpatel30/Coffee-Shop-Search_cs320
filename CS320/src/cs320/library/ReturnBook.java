package cs320.library;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReturnBook
 */
@WebServlet("/library/ReturnBook")
public class ReturnBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   @SuppressWarnings("unchecked")
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	   Connection connection = null;
	   Statement statement = null;
	   	try {
			
			Class.forName(DatabaseUtility.JDBC_DRIVER);
			connection = DriverManager.getConnection(DatabaseUtility.URL,DatabaseUtility.user, DatabaseUtility.password);
			statement = connection.createStatement();
			ResultSet rs = null;
			String sql = null;
			
			ArrayList<Book> borrowerList = new ArrayList<Book>();
			String borrowerId = request.getParameter("id");
			
			if(borrowerId == null){
			
				sql = "SELECT * FROM books a join borrower b on b.book_id = a.book_id";
				rs = statement.executeQuery(sql);
				while(rs.next()){
					borrowerList.add(new Book(rs.getInt("book_id"), rs.getString("title"), rs.getInt("copies"), rs.getString("borrowed_on"), rs.getString("student_name"),rs.getInt("borrower_id")));
				}
				
				request.getServletContext().setAttribute("Borrower_List", borrowerList);
				response.sendRedirect("../library/ReturnBook.jsp");
			} else {
				borrowerList = (ArrayList<Book>)this.getServletContext().getAttribute("Borrower_List");
				sql = "DELETE FROM borrower where borrower_id="+Integer.parseInt(borrowerId);
				statement.executeUpdate(sql);
				int copies = 0;
				int bookId = 0 ;
				for(Book book: borrowerList){
					if(Integer.parseInt(borrowerId) == book.getBorrowerId()){
						bookId = book.getId();
						copies = book.getCopies();
						copies++;
					}	
				}
				sql = "UPDATE books SET copies="+copies+" WHERE book_id="+bookId;
				statement.executeUpdate(sql);
				response.sendRedirect("MainPage");
			}
			
		} catch (Exception e){
			e.printStackTrace();
		} finally{
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
