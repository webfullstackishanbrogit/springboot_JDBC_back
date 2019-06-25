package com.javainuse.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.javainuse.dao.EmployeeDao;
import com.javainuse.model.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{

	/*@Autowired
	DataSource dataSource;*/

	/*@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}*/
	
	@Override
	public void insertEmployee(Employee emp) {

		String dbURL = "jdbc:mysql://localhost:3306/spring";
		String username = "root";
		String password = "";

		try {

			Connection conn = DriverManager.getConnection(dbURL, username, password);

			if (conn != null) {
				System.out.println("Connected");
               // PreparedStatement ps1 =conn.prepareStatement("CREATE TABLE employee (empId varchar(255),empName varchar(255))");
               // ps1.executeUpdate();
				PreparedStatement ps = conn.prepareStatement("INSERT INTO employee VALUES ( ?, ?)");
				ps.setString(1, emp.getEmpId());
				ps.setString(2, emp.getEmpName());
				//ps.setInt(3, emp.getAge());
				//int i = ps.executeUpdate();
				ps.executeUpdate();
			}
			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}


	}
	
	@Override
	public void insertEmployees( List<Employee> employees) {

        String dbURL = "jdbc:mysql://localhost:3306/spring";
        String username = "root";
        String password = "";

        try {

            Connection conn = DriverManager.getConnection(dbURL, username, password);

            if (conn != null) {
                System.out.println("Connected");

                PreparedStatement ps = conn.prepareStatement("INSERT INTO employee VALUES ( ?, ?)");
                for(Employee n:employees) {
                    ps.setString(1, n.getEmpId());
                    ps.setString(2, n.getEmpName());
                    ps.executeUpdate();
                }

            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }



	}
	@Override
	public List<Employee> getAllEmployees(){

        String dbURL = "jdbc:mysql://localhost:3306/spring";
        String username = "root";
        String password = "";

        try {

            Connection conn = DriverManager.getConnection(dbURL, username, password);

            if (conn != null) {
                System.out.println("Connected");

                PreparedStatement ps = conn.prepareStatement("SELECT * FROM employee");
                ResultSet rs=ps.executeQuery();
                List<Employee> li=new ArrayList<Employee>();
                while (rs.next())
                {
                    //int id = rs.getInt("id");
                    String empId = rs.getString("empId");
                    String empName = rs.getString("empName");
                    //Date dateCreated = rs.getDate("date_created");
                    //boolean isAdmin = rs.getBoolean("is_admin");
                    //int numPoints = rs.getInt("num_points");
                    Employee obj=new Employee();
                    obj.setEmpId(empId);
                    obj.setEmpName(empName);
                    li.add(obj);
                    // print the results
                    //System.out.format("%s, %s, %s, %s, %s, %s\n", id, firstName, lastName, dateCreated, isAdmin, numPoints);
                }
                return li;

            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;

	}

	@Override
	public Employee getEmployeeById(String empId) {

        String dbURL = "jdbc:mysql://localhost:3306/spring";
        String username = "root";
        String password = "";

        try {

            Connection conn = DriverManager.getConnection(dbURL, username, password);

            if (conn != null) {
                System.out.println("Connected");
                // PreparedStatement ps1 =conn.prepareStatement("CREATE TABLE employee (empId varchar(255),empName varchar(255))");
                // ps1.executeUpdate();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM employee WHERE empId = ?");
                ps.setString(1,empId);//set the pharammeters
                ResultSet rs=ps.executeQuery();

                while ( rs.next() ) {
                    Employee emp = new Employee();
                    emp.setEmpId(rs.getString("empId"));
                    emp.setEmpName(rs.getString("empName"));
                    return emp;
                    //System.out.println(emp);
                }
            }

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;



	}

    @Override
    public void deleteEmployee(String empId) {
        String dbURL = "jdbc:mysql://localhost:3306/spring";
        String username = "root";
        String password = "";

        try {

            Connection conn = DriverManager.getConnection(dbURL, username, password);

            if (conn != null) {


                PreparedStatement ps = conn.prepareStatement("DELETE FROM employee WHERE empId=?");
                //PreparedStatement ps = conn.prepareStatement("DELETE FROM employee WHERE empId="+empId);

                ps.setString(1,empId);
                ps.execute();

                    System.out.println("successfully deleted");

            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean updateEmployee(Employee emp) {
        String dbURL = "jdbc:mysql://localhost:3306/spring";
        String username = "root";
        String password = "";
        try {

            Connection conn = DriverManager.getConnection(dbURL, username, password);

            if (conn != null) {

                PreparedStatement ps = conn.prepareStatement("UPDATE employee SET empId=?,empName=? WHERE empId='emp'");
                ps.setString(1, emp.getEmpId());
                ps.setString(2, emp.getEmpName());

                int i=ps.executeUpdate();
                if(i==1){
                    return true;
                }
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}