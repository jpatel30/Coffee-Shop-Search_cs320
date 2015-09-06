package cs320.onlinetest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class OnlineTest
 */
@WebServlet("/onlinetest/OnlineTest")
public class OnlineTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/OnlineTest";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	   
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
			
		
		String isNewTest = request.getParameter("isNewTest");
		
		if(isNewTest != null && isNewTest.equals("true")){
			session.invalidate();
			session = request.getSession();
		}
		
		Connection connection = null;
		Statement statement = null;
		
		String query = "";
		
		if(session != null ){
			try{
			
				Class.forName(JDBC_DRIVER);
			
				connection = DriverManager.getConnection(URL, USER, PASSWORD);
				query = "select * from questions order by rand() limit 1";
				statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(query);
				ArrayList<Test> list = new ArrayList<Test>();
				
				while(rs.next()){
					list.add(new Test(rs.getInt("id"), rs.getString("questions"),rs.getString("a"), rs.getString("b"), rs.getString("c"), rs.getString("d"), rs.getString("answer")));
				}
			
			session.setAttribute("Questions", list);
			request.getRequestDispatcher("../onlinetest/ViewTest.jsp").forward(request, response);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
