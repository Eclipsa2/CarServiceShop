package main;

import model.*;
import service.ShopMenu;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Connection dbConnection()
    {
        String url = "jdbc:mysql://localhost:3306/serviceVeriku";
        String username = "root";
        String password = "";
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);

            return connection;
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load JDBC driver: " + e.getMessage());
            return null;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static int menu ()
    {
        System.out.println("\n-------------------------------------------------");
        System.out.println("Welcome to our Vehicles Service!");
        System.out.println("-------------------------------------------------");
        System.out.println("1. HR management");
        System.out.println("2. Client management");
        System.out.println("3. Car repairs");
        System.out.println("4. Motorcycle repairs");
        System.out.println("5. Statistics");
//        System.out.println("1. Register new Client");
//        System.out.println("2. See all clients");
//        System.out.println("3. Add store credit to client");
//        System.out.println("4. Pay Repairs");
//        System.out.println("5. Register new Employee");
//        System.out.println("6. See all Employees");
//        System.out.println("7. Fire an Employee");
//        System.out.println("-------------------------------------------------");
//        System.out.println("8. Add a new car in the service");
//        System.out.println("9. See all cars currently in the shop");
//        System.out.println("10. Change car status");
//        System.out.println("11. See all repaired cars");
//        System.out.println("12. Resoft a car (add more horsepower)");
//        System.out.println("-------------------------------------------------");
//        System.out.println("13. Add a new motorcycle in the service");
//        System.out.println("14. See all motorcycles currently in the shop");
//        System.out.println("15. Change motorcycle status");
//        System.out.println("16. See all repaired motorcycles");
//        System.out.println("17. Resoft a motorcycle (add more horsepower)");
        System.out.println("-------------------------------------------------");
        System.out.println("0. Exit app");

        Scanner inputScanner = new Scanner(System.in);
        String stringOption = inputScanner.nextLine();
        int option = Integer.parseInt(stringOption);

        while(option > 5)
        {
            System.out.println("\n Please enter a valid option!");
            stringOption = inputScanner.nextLine();
            option = Integer.parseInt(stringOption);
        }

        return option;
    }

    public static int menuLogin()
    {
        System.out.println("\n-------------------------------------------------");
        System.out.println("Welcome!");
        System.out.println("-------------------------------------------------");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("-------------------------------------------------");
        System.out.println("0. Exit");

        Scanner inputScanner = new Scanner(System.in);
        String stringOption = inputScanner.nextLine();
        int option = Integer.parseInt(stringOption);

        while(option > 3)
        {
            System.out.println("\n Please enter a valid option!");
            stringOption = inputScanner.nextLine();
            option = Integer.parseInt(stringOption);
        }

        return option;
    }
    public static void main(String[] args) throws SQLException, IOException
    {
        //region initialization of some cars
        Client client1 = new Client("Andrei", "Ilie",
                1111111111);
        Employee employee1 = new Employee("Andrei", "Ilie",
                1111111112, 3000);

        Issue[] issues1 = new Issue[2];
        issues1[0] = new Issue("Engine Problems", 2000);
        issues1[1] = new Issue("Sofware Problems", 500);

        Car car1 = new Car("B27FLG", "grey", 2022,
                130, client1, employee1, issues1,
                "Volkswagen", "Golf VIII");
        //endregion

        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        Connection connection = dbConnection.getConnection();

        Scanner inputScanner = new Scanner(System.in);
        String name, aux, query;

        List <User> users = new ArrayList<User>();

        int option = menuLogin();
        while(option != 0)
        {
            switch (option)
            {
                case 1:
                {
                    int ok = 0;
                    User userAux = new User("","");

                    while(ok != 1)
                    {
                        System.out.println("Please enter the username: ");
                        name = inputScanner.nextLine();
                        System.out.println("Please enter the password: ");
                        aux = inputScanner.nextLine();
                        userAux = new User(name, aux);

                        query = "SELECT COUNT(*) FROM users WHERE username = ? AND password = ?";
                        PreparedStatement statement = connection.prepareStatement(query);
                        statement.setString(1, userAux.getUsername());
                        statement.setString(2, userAux.getPassword());
                        ResultSet numberOfUsers = statement.executeQuery();

                        int isUser = 0;

                        if(numberOfUsers.next())
                            isUser = numberOfUsers.getInt(1);

                        if(isUser > 0)
                        {
                            option = ShopMenu.mainMenu();
                            ok = 1;
                        }

                        if(ok == 0)
                        {
                            System.out.println("The login credentials are wrong!");
                            System.out.println("Try again!");
                        }
                    }
                    break;
                }
                case 2:
                {
                    System.out.println("Please enter the username: ");
                    name = inputScanner.nextLine();
                    System.out.println("Please enter the password: ");
                    aux = inputScanner.nextLine();
                    User userAux = new User(name, aux);

                    query = "INSERT INTO users (username, password) VALUES (?,?)";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, userAux.getUsername());
                    statement.setString(2, userAux.getPassword());
                    statement.execute();

                    users.add(userAux);
                    option = menuLogin();
                    break;
                }
            }
        }
    }
}