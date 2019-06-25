/**
  * This file is used to get a Database connection to Other controller classes by simply returning a connection.
 * 
 */

package application.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBconnection {
	//DB connect variables
		static Connection connection = null;
		static PreparedStatement ps = null;
		static String databaseName = "gebs";
		static String url = "jdbc:mysql://localhost:3306/" + databaseName;
		
		static String username = "root";
		static String password = "sandstorm";

	public static Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		//calling new com.mysql.jdbc.Driver();
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		
		//getting a connection to the DB and returning...
		connection = DriverManager.getConnection(url, username, password);
		return connection;
	}

}
