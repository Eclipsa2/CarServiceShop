package repository;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MotorcyclesRepository
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

    public static ResultSet getAllDataFromTable(String tableName) throws SQLException
    {
        String query = "SELECT * FROM " + tableName;
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        return rs;
    }

    public static List<Motorcycle> getAllMotorcyclesFromDB(List<Motorcycle> repairedMotorcycles) throws SQLException
    {
        Client auxClient = new Client();
        Employee auxEmployee = new Employee();
        Motorcycle auxMotorcycle;
        List<Motorcycle> motorcyclesInShop = new ArrayList<Motorcycle>();

        ResultSet rs = getAllDataFromTable("motorcycles");
        String query;
        PreparedStatement statement;
        while(rs.next())
        {
            //region Getting the owner details for the current motorcycle:
            query = "SELECT * FROM clients WHERE ID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, rs.getString("ownerID"));
            ResultSet ownerDetails = statement.executeQuery();

            if(ownerDetails.next())
            {
                auxClient = new Client(ownerDetails.getString("name"),
                        ownerDetails.getString("firstName"),
                        ownerDetails.getInt("phoneNumber"));
                auxClient.setLeftToBePaid(ownerDetails.getInt("leftToBePaid"));
                auxClient.setStoreCredit(ownerDetails.getInt("storeCredit"));
                auxClient.setPaidRepairs(ownerDetails.getBoolean("paidRepairs"));
            }
            //endregion

            //region Getting the employee that is working for the current motorcycle:
            query = "SELECT * FROM employees WHERE ID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, rs.getString("EmployeeID"));
            ResultSet employeeDetails = statement.executeQuery();

            if(employeeDetails.next())
            {
                auxEmployee = new Employee(employeeDetails.getString("name"),
                        employeeDetails.getString("firstName"),
                        employeeDetails.getInt("phoneNumber"),
                        employeeDetails.getInt("salary"));
            }
            //endregion

            //region Getting issues array for the current motorcycle:
            query = "SELECT i.issueName, i.price FROM issues i INNER JOIN motorcycleIssues mi ON i.ID = mi.issueID INNER JOIN motorcycles m ON m.ID = mi.MotorcycleID WHERE m.ID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, rs.getString("ID"));
            ResultSet issues = statement.executeQuery();
            ArrayList<Issue> auxIssues = new ArrayList<>();
            while(issues.next())
                auxIssues.add(new Issue(issues.getString("issueName"),
                        issues.getInt("price")));

            Issue[] issuesArray = new Issue[auxIssues.size()];

            for(int i = 0; i < issuesArray.length; ++i)
                issuesArray[i] = auxIssues.get(i);
            //endregion

            auxMotorcycle = new Motorcycle(rs.getString("registrationNumber"),
                    rs.getString("color"),
                    rs.getInt("fabricationYear"),
                    rs.getInt("horsePower"),
                    auxClient,
                    auxEmployee,
                    issuesArray,
                    rs.getString("manufacturer"),
                    rs.getString("model"),
                    rs.getInt("totalMass"),
                    rs.getString("transmissionType"));

            if(rs.getBoolean("isRepaired"))
                repairedMotorcycles.add(auxMotorcycle);

            else motorcyclesInShop.add(auxMotorcycle);
        }

        return motorcyclesInShop;
    }

    public static void payRepairs(String registrationNumber) throws SQLException
    {
        String query = "UPDATE motorcycles SET paidRepairs = ? WHERE registrationNumber = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, "1");
        statement.setString(2, registrationNumber);
        statement.execute();
    }

    public static void addNewMotorcycle(Motorcycle motorcycleAux, int ClientID, int EmployeeID, Issue[] issues) throws SQLException
    {
        String query = "INSERT INTO motorcycles(registrationNumber, color, fabricationYear, horsePower" +
                ", ownerID, EmployeeID, manufacturer, model, totalMass, transmissionType, paidRepairs, isRepaired) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, motorcycleAux.getRegistrationNumber());
        statement.setString(2, motorcycleAux.getColor());
        statement.setString(3, String.valueOf(motorcycleAux.getFabricationYear()));
        statement.setString(4, String.valueOf(motorcycleAux.getHorsePower()));
        statement.setString(5, String.valueOf(ClientID));
        statement.setString(6, String.valueOf(EmployeeID));
        statement.setString(7, motorcycleAux.getManufacturer());
        statement.setString(8, motorcycleAux.getModel());
        statement.setString(9, String.valueOf(motorcycleAux.getTotalMass()));
        statement.setString(10, motorcycleAux.getTransmissionType());
        statement.setString(11, String.valueOf(0));
        statement.setString(12, String.valueOf(0));
        statement.execute();
        //endregion

        //region add the associative table rows in motorcycleIssues for the motorcycle:
        query = "SELECT ID FROM motorcycles WHERE registrationNumber = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, motorcycleAux.getRegistrationNumber());
        ResultSet motorcycleIDResultSet = statement.executeQuery();
        int motorcycleID = 0;

        if(motorcycleIDResultSet.next())
            motorcycleID = motorcycleIDResultSet.getInt("ID");

        int issueID = 0;

        for(int i = 0; i < issues.length; ++i)
        {
            query = "SELECT ID FROM issues WHERE issueName = ? and price = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, issues[i].getIssueName());
            statement.setString(2, String.valueOf(issues[i].getPrice()));
            ResultSet issueIDResultSet = statement.executeQuery();

            if(issueIDResultSet.next())
                issueID = issueIDResultSet.getInt("ID");

            query = "INSERT INTO motorcycleIssues (IssueID, MotorcycleID) VALUES (?,?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, String.valueOf(issueID));
            statement.setString(2, String.valueOf(motorcycleID));
            statement.execute();
        }
        //endregion
    }

    public static void changeMotorcycleStatus(String registrationNumber) throws SQLException
    {
        String query = "UPDATE motorcycles SET isRepaired = ? WHERE registrationNumber = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(1));
        statement.setString(2, registrationNumber);
        statement.execute();
    }

    public static void addHorsePowerToMotorcycle(String registrationNumber, int horsePower) throws SQLException
    {
        String query = "UPDATE motorcycles SET horsePower = ? WHERE registrationNumber = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(horsePower));
        statement.setString(2, registrationNumber);
        statement.execute();
    }
}
