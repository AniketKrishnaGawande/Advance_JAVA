package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {
	
	
	public static Connection getConnection() throws SQLException {
		
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/voters","root","Aniket19%");
		
	}
	

}
