package repository;

import model.Client;
import model.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository
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

    public static List<Client> getAllClientsFromDB() throws SQLException
    {
        List<Client> clients = new ArrayList<Client>();

        String query = "SELECT * FROM clients";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet rs = statement.executeQuery();

        Client auxClient = null;
        while(rs.next())
        {
            auxClient = new Client(rs.getString("name"),
                    rs.getString("firstName"),
                    rs.getInt("phoneNumber"));
            auxClient.setLeftToBePaid(rs.getInt("leftToBePaid"));
            auxClient.setStoreCredit(rs.getInt("storeCredit"));
            auxClient.setPaidRepairs(rs.getBoolean("paidRepairs"));

            clients.add(auxClient);
        }

        return clients;
    }

    public static void registerNewClient(Client auxClient) throws SQLException
    {
        String query = "INSERT INTO clients (name, firstName, phoneNumber) VALUES (?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, auxClient.getName());
        statement.setString(2, auxClient.getFirstName());
        statement.setString(3, String.valueOf(auxClient.getPhoneNumber()));
        statement.execute();
    }

    public static void addStoreCredit(Client auxClient) throws SQLException
    {
        int newCredit = auxClient.getStoreCredit();

        String query = "UPDATE clients SET storeCredit = ? WHERE name = ? AND firstName = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(newCredit));
        statement.setString(2, auxClient.getName());
        statement.setString(3, auxClient.getFirstName());
        statement.execute();
    }
}
