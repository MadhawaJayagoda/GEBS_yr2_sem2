package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.Dbconnect;

public class LoginModel {
	Connection con = null;

	public boolean IsLogin(String user, String pass) throws SQLException {
		con = Dbconnect.connect();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String q = "select * from user where user = ? and password = ?";
		
		try {
			pst = con.prepareStatement(q);
			pst.setString(1, user);
			pst.setString(2, pass);
			
			rs = pst.executeQuery();
			if(rs.next()) {
				return true;
			}
			else
				return false;
			
		}catch(Exception e) {
			return false;
			
		}finally {
			pst.close();
			rs.close();
		}
		
	}

}
