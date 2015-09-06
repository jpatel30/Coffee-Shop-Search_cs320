package cs320.lab5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CoffeeShop {
	
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/CoffeeDatabase";
	private static final String user = "root";
	private static final String password = "root";
	

	public static void main(String[] args) {
		
		Connection connection = null;
		Statement statement = null;
		BufferedReader bufferedReader = null;
		
		try{
			
			String file = "F:/Spring 2014/CS 320/workspace/CS320/WebContent/All_Starbucks_Locations_in_the_World.txt";
			
			bufferedReader = new BufferedReader(new FileReader(file));
			
			int storeId=0;
			String name="";
			String storeNumber="";
			String phone ="";
			String street1="";
			String street2="";
			String street3="";
			String city="";
			String country="";
			String postalCode="";
			double latitude=0;
			double longitude=0;
			String timeZone = "";
			
			String line;
						
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(URL, user, password);
			statement = connection.createStatement();
			String sql ="";
			int count=0;
			while((line = bufferedReader.readLine()) != null){
				try{
					
				String[] tokens = line.split("\t");
				storeId = Integer.parseInt(tokens[0].replace('"', ' ').trim());
				name = tokens[1].replace('"', ' ').trim().replace("'", "");
				storeNumber = tokens[2].trim();
				if(!tokens[3].isEmpty())
					phone = tokens[3].trim().replace(" ", "-");
				street1 = tokens[4].replace('"', ' ').trim().replace("'", "");
				street2 = tokens[5].replace('"', ' ').trim().replace("'", "");
				street3 = tokens[6].replace('"', ' ').trim().replace("'", "");
				city = tokens[7].replace('"', ' ').trim();
				country = tokens[8].replace('"', ' ').trim();
				postalCode = tokens[9].replace('"', ' ').trim();
				latitude = Double.parseDouble(tokens[10].trim());
				longitude = Double.parseDouble(tokens[11].trim());
				timeZone = tokens[12].trim();
				sql = "INSERT into CoffeeShops VALUES ("+storeId+",'"+name+"','"+storeNumber+"','"+phone+"','"+street1+"','"+street2+"','"+street3+"','"+city+"','"+country+"','"+postalCode+"',"+latitude+","+longitude+",'"+timeZone+"')";
				statement.executeUpdate(sql);
				
				}catch(Exception e){
					System.out.println(sql);
					//System.out.println(count+++" "+line);
					//e.printStackTrace();
				}
			}
					
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if(connection != null)
				try {
					connection.close();
					bufferedReader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}

	}

}
