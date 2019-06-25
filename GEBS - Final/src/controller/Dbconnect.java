package controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dbconnect {
	
	
	public static Connection connect()
	{
		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/gebs?useSSL=false";
		String uname = "root";
		String pass= "sandstorm";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = (Connection) DriverManager.getConnection(url,uname,pass);
			
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return con; 
	}
	

}
