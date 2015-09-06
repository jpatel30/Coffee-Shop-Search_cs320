package cs320.Homework2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SearchCoffeeShops
 */
@WebServlet("/Homework2/SearchCoffeeShops")
public class SearchCoffeeShops extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();		
		CoffeeUsers user = (CoffeeUsers) session.getAttribute("currentCoffeeUser");
		
		if (user == null){
			response.sendRedirect("Login");
		} else {
			
			ArrayList<CoffeeShop> coffeeShops = (ArrayList<CoffeeShop>) this.getServletContext().getAttribute("CoffeeShops");			
			response.setContentType("text/html");
			
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html><html lang=\"en\">");
			out.println("<head><title>Coffee Shop Locator</title></head>");
			out.println("<body>");
			
			out.println("<p style=\"text-align: right;\"><a href=\"Login?action=logout\">Logout</a></p>");
			out.println("<h2> Welcome, " + user.getFirstName() + "!</h2>");
			out.println("<p> Displaying coffee shops in your zipcode: " + user.getZipCode() + "</p>");
						
			out.println("<form align =\"center\" name=\"searchBy\" action=\"SearchCoffeeShops\" method=\"post\">");
			out.println("Search by city : <input type = \"text\" name=\"searchbycity\" placeholder=\"Search by city\">");
			out.println("Search by zipcode : <input type = \"text\" name=\"searchbyzipcode\" placeholder=\"Search by zipcode\">");
			out.println("Search by distance : <input type = \"text\" name=\"searchbydistance\" placeholder=\"Enter the distance\">");
			out.println("<input type=\"submit\" value=\"Search\">");
			out.println("</br></br>");
			
			String city = request.getParameter("searchbycity");
			String zipcode = request.getParameter("searchbyzipcode");
			String tempDistance = request.getParameter("searchbydistance");
			double distance  = 0;
			double latitude = 0;
			double longitude = 0;
			if(tempDistance != null && tempDistance.trim().length() > 0){
				distance = Double.parseDouble(tempDistance);
				for(CoffeeShop coffee : coffeeShops){
					if(user.getZipCode().equals(coffee.getZip())){
						latitude  = coffee.getLat();
						longitude = coffee.getLon();
						break;
					}
				}
			}
			out.println("<table border=1 style=\"background: none repeat scroll 0% 0% yellow;\">");
			out.println("<tr><th>Sr.No</th><th>Store Name</th><th>Store Address</th><th>Store Number</th><th>View Map</th></tr>");
			int i=1;
			boolean valueExist = false;
			for (CoffeeShop coffeeShop : coffeeShops){
				if (coffeeShop.getZip().equals(user.getZipCode()) && ((city == "" && zipcode == "") || (city == null && zipcode == null && tempDistance ==null))){
					out.println("<tr>");
					out.println("<td align=\"center\">" +i+++ "</td>");
					out.println("<td><h4>" + coffeeShop.getName() + "</h4></td>");
					out.println("<td><h4>" + coffeeShop.getAddress() + "</h4></td>");
					out.println("<td><h4>" + coffeeShop.getPhone() + "</h4></td>");
					out.println("<td><a href=\"http://maps.googleapis.com/maps/api/streetview?location="+coffeeShop.getLat()+","+coffeeShop.getLon()+"&zoom=11&size=2500x2500\">View Map</td>");
					//http://maps.googleapis.com/maps/api/streetview?size=400x400&location=40.720032,-73.988354&fov=90&heading=235&pitch=10&sensor=false
					out.println("</tr>");
					valueExist = true;
				} else if( city != null  && city.trim().length() > 0 && (coffeeShop.getCity().trim().equalsIgnoreCase(city.trim()) || coffeeShop.getCity().trim().toLowerCase().startsWith(city.trim().toLowerCase()) || coffeeShop.getCity().trim().toLowerCase().contains(city.trim().toLowerCase()))){
					out.println("<tr>");
					out.println("<td align=\"center\">" +i+++ "</td>");
					out.println("<td><h4>" + coffeeShop.getName() + "</h4></td>");
					out.println("<td><h4>" + coffeeShop.getAddress() + "</h4></td>");
					out.println("<td><h4>" + coffeeShop.getPhone() + "</h4></td>");
					out.println("<td><a href=\"http://maps.googleapis.com/maps/api/streetview?location="+coffeeShop.getLat()+","+coffeeShop.getLon()+"&zoom=11&size=2500x2500\">View Map</td>");
					out.println("</tr>");
					valueExist = true;
				} else if( zipcode != null && zipcode.trim().length() > 0 && coffeeShop.getZip().contains(zipcode.trim())){
					out.println("<tr>");
					out.println("<td align=\"center\">" +i+++ "</td>");
					out.println("<td><h4>" + coffeeShop.getName() + "</h4></td>");
					out.println("<td><h4>" + coffeeShop.getAddress() + "</h4></td>");
					out.println("<td><h4>" + coffeeShop.getPhone() + "</h4></td>");
					out.println("<td><a href=\"http://maps.googleapis.com/maps/api/streetview?location="+coffeeShop.getLat()+","+coffeeShop.getLon()+"&zoom=11&size=2500x2500\">View Map</td>");
					out.println("</tr>");
					valueExist = true;
				} else if(tempDistance != null && tempDistance.trim().length() > 0 && distance >= calculateDistance(latitude, longitude, coffeeShop.getLat(), coffeeShop.getLon())){
					out.println("<tr>");
					out.println("<td align=\"center\">" +i+++ "</td>");
					out.println("<td><h4>" + coffeeShop.getName() + "</h4></td>");
					out.println("<td><h4>" + coffeeShop.getAddress() + "</h4></td>");
					out.println("<td><h4>" + coffeeShop.getPhone() + "</h4></td>");
					out.println("<td><a href=\"http://maps.googleapis.com/maps/api/streetview?location="+coffeeShop.getLat()+","+coffeeShop.getLon()+"&zoom=11&size=2500x2500\">View Map</td>");
					out.println("</tr>");
					valueExist = true;
				}
			}
			if(!valueExist)
				out.println("<h4 style =\"color:red\">No Records Found</h4>");
			out.println("</table>");
			out.println("</body>");
			out.println("</html>");
		}
	}
	
	public double calculateDistance(double latitude, double longitude, double newLatitude, double newLongitude){
		double distance = Math.acos((Math.sin(Math.toRadians(latitude))* Math.sin(Math.toRadians(newLatitude)))+(Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(newLatitude)) * Math.cos(Math.toRadians(newLongitude)-(Math.toRadians(longitude)))));
		return (Math.toDegrees(distance) * 69.1105);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
