package cs320.mvc;

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


@WebServlet("/mvc/CoffeeShopSearchController")
public class CoffeeShopSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/CoffeeDatabase";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String type = request.getParameter("type");
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String query = "";
		try {
			
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			statement = connection.createStatement();
						
			if(type != null){
				
				String forwardUrl ="";
				String city = request.getParameter("cityName");
				String zipcode = request.getParameter("zipCode");
				String distance = request.getParameter("distance");
				if(distance == null)
					distance="5";
				String latitude = request.getParameter("lat");
				String longitude = request.getParameter("lon");
				
				ArrayList<CoffeeShops> coffeeList = new ArrayList<CoffeeShops>();
				if(type.equals("city")){
					if(city != null && city.trim().length() > 0){
						
						query = "SELECT DISTINCT * FROM CoffeeShops WHERE City like '%"+city+"' OR SOUNDEX(City) LIKE SOUNDEX('"+city+"')";
						resultSet = statement.executeQuery(query);
						while(resultSet.next()){
							coffeeList.add(new CoffeeShops(resultSet.getInt("StoreId"), resultSet.getString("Name"), resultSet.getString("StoreNumber"), resultSet.getString("Phone"), resultSet.getString("Street1"), resultSet.getString("Street2"), resultSet.getString("Street3"), resultSet.getString("City"), resultSet.getString("Country"), resultSet.getString("PostalCode"), resultSet.getDouble("Latitude"), resultSet.getDouble("Longitude"), resultSet.getString("Timezone")));
						}
						request.setAttribute("CoffeeList", coffeeList);
					}	
					forwardUrl = "../mvc/SearchCity.jsp";
					
				} else if(type.equals("zipcode")){
					if(zipcode != null && zipcode.trim().length() > 0){
						query = "SELECT * FROM CoffeeShops WHERE PostalCode like '%"+zipcode+"%'";
						resultSet = statement.executeQuery(query);
						while(resultSet.next()){
							coffeeList.add(new CoffeeShops(resultSet.getInt("StoreId"), resultSet.getString("Name"), resultSet.getString("StoreNumber"), resultSet.getString("Phone"), resultSet.getString("Street1"), resultSet.getString("Street2"), resultSet.getString("Street3"), resultSet.getString("City"), resultSet.getString("Country"), resultSet.getString("PostalCode"), resultSet.getDouble("Latitude"), resultSet.getDouble("Longitude"), resultSet.getString("Timezone")));
						}
						
						request.setAttribute("CoffeeList", coffeeList);
					}
					forwardUrl = "../mvc/SearchZipcode.jsp";
				
				} else if(type.equals("gps")){
					if(distance != null){
						query = "SELECT *," +
								" ( 3959 * acos( cos( radians('"+latitude+"') ) * cos( radians( Latitude ) ) * cos( radians( Longitude ) - radians('"+longitude+"') ) + sin( radians('"+latitude
								+"') ) * sin( radians( Latitude ) ) ) )" +
								" AS distance FROM CoffeeShops HAVING distance < '"+distance+"' ORDER BY distance";
						resultSet = statement.executeQuery(query);
						while(resultSet.next()){
							coffeeList.add(new CoffeeShops(resultSet.getInt("StoreId"), resultSet.getString("Name"), resultSet.getString("StoreNumber"), resultSet.getString("Phone"), resultSet.getString("Street1"), resultSet.getString("Street2"), resultSet.getString("Street3"), resultSet.getString("City"), resultSet.getString("Country"), resultSet.getString("PostalCode"), resultSet.getDouble("Latitude"), resultSet.getDouble("Longitude"), resultSet.getString("Timezone")));
						}
						
						request.setAttribute("CoffeeList", coffeeList);
						request.setAttribute("CoffeeList", coffeeList);
					}
					request.setAttribute("distance", distance);
					request.setAttribute("latitude", latitude);
					request.setAttribute("longitude", longitude);
					
					forwardUrl = "../mvc/SearchGps.jsp";
				}
				
				request.getRequestDispatcher(forwardUrl).forward(request, response);
				
			} else {
				response.sendRedirect("../mvc/index.jsp");
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
