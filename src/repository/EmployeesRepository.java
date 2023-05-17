package repository;

import model.DatabaseConnection;
import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.PriorityQueue;

public class EmployeesRepository
{
    private static DatabaseConnection dbConnection;
    static
    {
        try
        {
            dbConnection = DatabaseConnection.getInstance();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    private static Connection connection = dbConnection.getConnection();

    public static PriorityQueue<Employee> getAllEmployeesFromDB() throws SQLException
    {
        PriorityQueue<Employee> employees = new PriorityQueue<Employee>();

        String query = "SELECT * FROM employees";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet rs = statement.executeQuery();

        Employee auxEmployee = null;
        while(rs.next())
        {
            auxEmployee = new Employee(rs.getString("name"),
                    rs.getString("firstName"),
                    rs.getInt("phoneNumber"),
                    rs.getInt("salary"));

            employees.add(auxEmployee);
        }

        return employees;
    }

    public static void hireEmployee(Employee auxEmployee) throws SQLException
    {
        String query = "INSERT INTO employees (name, firstName, phoneNumber, salary) VALUES (?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, auxEmployee.getName());
        statement.setString(2, auxEmployee.getFirstName());
        statement.setString(3, String.valueOf(auxEmployee.getPhoneNumber()));
        statement.setString(4, String.valueOf(auxEmployee.getSalary()));
        statement.execute();
    }

    public static void fireEmployee(Employee auxEmployee) throws SQLException
    {
        String query = "DELETE FROM employees WHERE firstName = ? AND name = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, auxEmployee.getFirstName());
        statement.setString(2, auxEmployee.getName());
        statement.execute();
    }
}
