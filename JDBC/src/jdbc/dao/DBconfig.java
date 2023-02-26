package jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconfig {

	public static Connection openConnection() throws SQLException {

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empinfo", "" + "root", "root123");
		return con;
	}

}
