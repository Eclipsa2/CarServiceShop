package repository;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarsRepository
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
    public static List<Car> getAllCarsFromDB(List<Car> repairedCars) throws SQLException
    {
        Client auxClient = new Client();
        Employee auxEmployee = new Employee();
        Car auxCar;
        List<Car> carsInShop = new ArrayList<Car>();

        ResultSet rs = getAllDataFromTable("cars");
        String query;
        PreparedStatement statement;
        while(rs.next())
        {
            //region Getting the owner details for the current car:
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

            //region Getting the employee that is working for the current car:
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

            //region Getting issues array for the current car:
            query = "SELECT i.issueName, i.price FROM issues i INNER JOIN carIssues ci ON i.ID = ci.issueID INNER JOIN cars c ON c.ID = ci.carID WHERE c.ID = ?";
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

            auxCar = new Car(rs.getString("registrationNumber"),
                    rs.getString("color"),
                    rs.getInt("fabricationYear"),
                    rs.getInt("horsePower"),
                    auxClient,
                    auxEmployee,
                    issuesArray,
                    rs.getString("manufacturer"),
                    rs.getString("model"));

            if(rs.getBoolean("isRepaired"))
                repairedCars.add(auxCar);

            else carsInShop.add(auxCar);
        }
        return carsInShop;
    }

    public static void payRepairs(String registrationNumber) throws SQLException
    {
        String query = "UPDATE cars SET paidRepairs = ? WHERE registrationNumber = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, "1");
        statement.setString(2, registrationNumber);
        statement.execute();
    }

    public static void addNewCar(Car carAux, int ClientID, int EmployeeID, Issue[] issues) throws SQLException
    {
        //region add Car to DB:
        String query = "INSERT INTO cars (registrationNumber, color, isRepaired, fabricationYear, horsePower" +
                ", ownerID, EmployeeID, manufacturer, model, paidRepairs) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, carAux.getRegistrationNumber());
        statement.setString(2, carAux.getColor());
        statement.setString(3, String.valueOf(0));
        statement.setString(4, String.valueOf(carAux.getFabricationYear()));
        statement.setString(5, String.valueOf(carAux.getHorsePower()));
        statement.setString(6, String.valueOf(ClientID));
        statement.setString(7, String.valueOf(EmployeeID));
        statement.setString(8, carAux.getManufacturer());
        statement.setString(9, carAux.getModel());
        statement.setString(10, String.valueOf(0));
        statement.execute();
        //endregion

        //region add the associative table rows in carIssues for the car:
        query = "SELECT ID FROM cars WHERE registrationNumber = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, carAux.getRegistrationNumber());
        ResultSet carIDResultSet = statement.executeQuery();
        int carID = 0;

        if(carIDResultSet.next())
            carID = carIDResultSet.getInt("ID");

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

            query = "INSERT INTO carIssues (IssueID, CarID) VALUES (?,?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, String.valueOf(issueID));
            statement.setString(2, String.valueOf(carID));
            statement.execute();
        }
    }

    public static void changeCarStatus(String registrationNumber) throws SQLException
    {
        String query = "UPDATE cars SET isRepaired = ? WHERE registrationNumber = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(1));
        statement.setString(2, registrationNumber);
        statement.execute();
    }

    public static void addHorsePowerToCar(String registrationNumber, int horsePower) throws SQLException
    {
        String query = "UPDATE cars SET horsePower = ? WHERE registrationNumber = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(horsePower));
        statement.setString(2, registrationNumber);
        statement.execute();
    }
}
