package jdbc.dao;

import java.lang.StackWalker.Option;
import java.net.SocketOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

import jdbc.pojo.Employee;

public class DBEmployee {

	private Connection con;
	private Statement stmt1;
	private Statement stmt2;
	private PreparedStatement pstmt3;
	private PreparedStatement pstmt4;
	private PreparedStatement pstmt5;

	public DBEmployee() throws SQLException {
		con = DBconfig.openConnection();
		stmt1 = con.createStatement();
		stmt2 = con.createStatement();
		pstmt3 = con.prepareStatement("insert into employee values(?,?,?)");
		pstmt4 = con.prepareStatement("update employee set ename=? , salary=? where eid=?");
		pstmt5 = con.prepareStatement("delete from employee where eid=?");
	}

	public List<Employee> getAllEmps() throws SQLException {

		String query = "select * from employee";
		ResultSet result = stmt1.executeQuery(query);
		List<Employee> allEmp = new ArrayList<Employee>();
		while (result.next()) {
			Employee emp = new Employee(result.getInt("eid"), result.getString("ename"), result.getDouble("salary"));
			allEmp.add(emp);
		}

		return allEmp;
	}

	public Employee getEmployeeById(int id) throws SQLException {
		Employee employee = new Employee();
		String query = "select * from employee where eid=" + id;
		ResultSet result = stmt2.executeQuery(query);
		if (result.next()) {
			employee = new Employee(result.getInt(1), result.getString(2), result.getDouble(3));
		}
		return employee;
	}

	public int addEmployee(Employee emp) throws SQLException {

		pstmt3.setInt(1, emp.getEid());
		pstmt3.setString(2, emp.getEname());
		pstmt3.setDouble(3, emp.getSalary());
		int rows = pstmt3.executeUpdate();
		return rows;
	}
	public int updateEmployee(Employee emp) throws SQLException
	{
		pstmt4.setInt(3, emp.getEid());
		pstmt4.setString(1, emp.getEname());
		pstmt4.setDouble(2, emp.getSalary());
		
		int rows=pstmt4.executeUpdate();
		return rows;
	}
	
	public boolean deleteEmployee(int id) throws SQLException
	{
		pstmt5.setInt(1,id);
		
		
		int rows=pstmt5.executeUpdate();
		if(rows>0)
		return true;
		
		return false;
	}

	public void closeConnction() throws SQLException {
		if (con != null) {
			con.close();
		}

		if (stmt1 != null) {
			stmt1.close();
		}

		if (stmt2 != null) {
			stmt2.close();
		}
		if (pstmt3 != null) {
			pstmt3.close();
		}
		if (pstmt4 != null) {
			pstmt4.close();
		}
		if (pstmt5 != null) {
			pstmt5.close();
		}

	}

}
