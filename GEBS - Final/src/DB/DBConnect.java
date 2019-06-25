package DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	public static Connection connect() {
		String url = "jdbc:mysql://localhost:3306/gebs?useSSL=FALSE";
		String uname = "root";
		String pwd = "sandstorm";
		
		Connection con = null;
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(url,uname,pwd);
		
	}catch(Exception e) {
		System.out.println(e);
	}
	return con;
	
	}
}  
