package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pojo.Voters;

public class DBConnector {

	private Connection con;
	private PreparedStatement pst1;

	public DBConnector() throws SQLException {
		con = DBConfig.getConnection();
		pst1 = con.prepareStatement("select * from voter where vname =? and vpassword=?");

	}

	public Voters Authenticate(String usname, String psw) throws SQLException {
		pst1.setString(1, usname);
		pst1.setString(2, psw);

		ResultSet result = pst1.executeQuery();
		if (result.next()) {
			return new Voters(result.getString(1), result.getString(2), result.getString(3));
		}
		return null;

	}

}
