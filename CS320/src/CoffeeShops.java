

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CoffeeShops {public static void main (String [] args)
{
	//Connection c = null;
try{
	
	String driver = "com.mysql.jdbc.Driver"; 
	String username = "root"; 
	String password = "root";
	String url ="jdbc:mysql://localhost:3306/CoffeeDatabase";
	Class.forName(driver).newInstance();
	System.out.println("Creating Connection");
	// Connect to the database
	Connection connection = DriverManager.getConnection(url, username, password);
	System.out.println("connection created");
	FileInputStream file=new FileInputStream("F:/Spring 2014/CS 320/workspace/CS320/WebContent/All_Starbucks_Locations_in_the_World.txt");
	DataInputStream in=new DataInputStream(file);
	BufferedReader br = new BufferedReader(new InputStreamReader(in));
	String ln;
	while ((ln=br.readLine())!=null)
	{
		//System.out.println("Hi");
			
		String [] line=ln.split("/t");
		//System.out.println("H");
		int StoreId=0;
		String Name="";
		String StoreNumber="";
		String Phone="";
		String Street1="";
		String Street2="";
		String Street3="";
		String City="";
		String Country="";
		String PostalCode="";
		Double Latitude=0.0;
		Double Longitude=0.0;
		String Timezone="";
		
		try
		{
		
		if(line.length<=13)
		{
			//System.out.println("read");
			
			StoreId=Integer.parseInt(line[0].replace('"', ' ').trim());
			Name=line[1].replace('"', ' ').trim();
			StoreNumber=line[2].replace('"', ' ').trim();
			Phone=line[3].replace('"', ' ').trim();
			Street1=line[4].replace('"', ' ').trim();
			Street2=line[5].replace('"', ' ').trim();
			Street3=line[6].replace('"', ' ').trim();
			City=line[7].replace('"', ' ').trim();
			Country=line[8].replace('"', ' ').trim();
			PostalCode=line[9].replace('"', ' ').trim();
			Latitude=Double.parseDouble(line[10].replace('"', ' ').trim());
			Longitude=Double.parseDouble(line[11].replace('"', ' ').trim());
			Timezone=line[12].replace('"', ' ').trim();
			String query="insert into CoffeeShops Values ('+StoreId+','"+Name+"','"+StoreNumber+"','"+Phone+"','"+Street1+"','"+Street2+"','"+Street3+",'"+City+"','"+Country+"','"+PostalCode+"','+Latitude+','+Longitude+','"+Timezone+"')";
			Statement stmt = connection.createStatement();
			//System.out.println( "IF PART TOTAL TOKEN LENGTH" + line.length + " Token 0 = "+line[0] + " Token 1 =  " + line[1] + " Token 2 = " + line[2] + " Token 3 = " + line[3] + " Token 4 = " + line[4]+ " Token 5 = " + line[5]+ " Token 6 = " + line[6]+ " Token 7 = " + line[7]+ " Token 8 = " + line[8]+ " Token 9 = " + line[9] + " Token 10 = " + line[10]+ " Token 11 = " + line[11]+ " Token 12 = " + line[12]);
			
			stmt.executeUpdate(query);
	        
			
		}
		
		}
		
		catch(Exception e)
		{
			System.out.println(e.getMessage());	
		}
		connection.close();	
	}
	
}
catch(Exception e)
{
	System.out.println(e.getMessage());
}

}
}
