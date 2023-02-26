package jdbc.tester;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import jdbc.dao.DBEmployee;
import jdbc.pojo.Employee;

public class Tester {

	public static void main(String[] args) {

		boolean exit = false;
		try (Scanner sc = new Scanner(System.in)) {
			DBEmployee dbCon = new DBEmployee();
			System.out.println("1. Show All 2. Get Employee By Id 3. add employee 4.Update Employee Details 5. Delete Employee  10. exit");
			while (!exit) {
				System.out.println("Enter choice :: ");
				switch (sc.nextInt()) {
				case 1:
					dbCon.getAllEmps().forEach(System.out::println);
					break;

				case 2:
					System.out.println("Enter the Employee Id to get details");
					Employee emp = dbCon.getEmployeeById(sc.nextInt());
					System.out.println(emp);
					break;

				case 3:
					System.out.println("Enter eid ename and salary :: ");
					emp = new Employee(sc.nextInt(), sc.nextLine(), sc.nextDouble());
					if (dbCon.addEmployee(emp) == 0) {
						System.out.println("not inserted");
					} else {
						System.out.println("inserted");
					}

					break;
					
				case 4: System.out.println("Enter Employee Id to update and the name and salary");
						emp=new Employee(sc.nextInt(),sc.nextLine(),sc.nextDouble());
						if (dbCon.updateEmployee(emp) == 0) {
							System.out.println("not Updated");
						} else {
							System.out.println("Updated");
						}
						break;
				case 5: System.out.println("Enter the Employee Id to delete the Employee");
						if(dbCon.deleteEmployee(sc.nextInt()))
						{
							System.out.println("Deleted");
						}
						else
						{
							System.out.println("Failed  to delete");
						}
						break;
						
						

				case 10:
					dbCon.closeConnction();
					exit = true;

				default:
					break;
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
