/**
 * This file have NOT been used for Database connections
 * 
 */

package application.util;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class connectDB {
	//DB connect variables
	static Connection connection = null;
	static PreparedStatement ps = null;
	static String databaseName = "gebs";
	static String url = "jdbc:mysql://localhost:3306/" + databaseName;
	
	static String username = "root";
	static String password = "";
	
	public static void main(String[] args) throws SQLException  {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//opening a connection to the database
		try {
			connection = DriverManager.getConnection(url, username, password);

			//Query execution
			ps = connection.prepareStatement("INSERT INTO supplier (fname, lname, company, address, category, phoneNum) VALUES ( 'madhawa', 'jayagoda', 'mj', 'colombo', 'construct', '0773423769');");
			
			int status = ps.executeUpdate();   //executeUpdate method returns an non-zero integer only if the execution successful, else it returns zero.  
			
			if (status != 0 ) {
				System.out.println("Database connection successful !");
				System.out.println("Records are Inserted");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Finally block used to cleaning up and close resources
		finally {
			if(ps != null) 
				ps.close();
		
			if(connection != null)
				connection.close();
		}
	}

}
