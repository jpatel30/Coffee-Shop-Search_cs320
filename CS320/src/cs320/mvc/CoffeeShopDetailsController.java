package cs320.mvc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/mvc/CoffeeShopDetailsController")
public class CoffeeShopDetailsController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/CoffeeDatabase";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	
 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
 		String id = request.getParameter("id");
 		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String query = "";
		try {
			if(id != null){
				
				Class.forName(JDBC_DRIVER);
				connection = DriverManager.getConnection(URL, USER, PASSWORD);
				statement = connection.createStatement();
				query = "SELECT * FROM CoffeeShops where StoreId='"+id+"'";
				resultSet = statement.executeQuery(query);
				CoffeeShopBean coffeeBean = new CoffeeShopBean();
				while(resultSet.next()){
					coffeeBean.setAddress(resultSet.getString("Street1")+resultSet.getString("Street2")+resultSet.getString("Street3"));
					coffeeBean.setPhone(resultSet.getString("Phone"));
					coffeeBean.setStoreName(resultSet.getString("Name"));
					coffeeBean.setLatitude(resultSet.getDouble("Latitude"));
					coffeeBean.setLongitude(resultSet.getDouble("Longitude"));
				}
				request.setAttribute("coffeeBean", coffeeBean);
				request.getRequestDispatcher("../mvc/CoffeeShopDetails.jsp").forward(request, response);
				
			} else {
				response.sendRedirect("CoffeeShopSearchController");
			}
			
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if(connection != null)
				try {
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
