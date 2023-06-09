package service;

import com.opencsv.CSVWriter;
import model.*;
import repository.CarsRepository;
import repository.ClientRepository;
import repository.EmployeesRepository;
import repository.MotorcyclesRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class RepairShopService
{
    private static List<Client> clients = new ArrayList<Client>();
    private static PriorityQueue<Employee> employees = new PriorityQueue<Employee>();
    private static List<Car> carsInShop = new ArrayList<Car>();
    private static List<Car> repairedCars = new ArrayList<Car>();
    private static List<Motorcycle> motorcyclesInShop = new ArrayList<Motorcycle>();
    private static List<Motorcycle> repairedMotorcycles = new ArrayList<Motorcycle>();
    private static Scanner inputScanner = new Scanner(System.in);
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

    public static void writeActionInFile(String action, String fileName) throws IOException
    {
        CSVWriter csvWriter = new CSVWriter(new FileWriter(fileName, true));
        LocalDateTime currentDateTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        switch (action)
        {
            case "AddedNewEmployee":
            {
                String[] row = {"A new employee has been added", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

            case "SeeAllEmployees":
            {
                String[] row = {"All employees have been printed", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

            case "FiredEmployee":
            {
                String[] row = {"An employee has been fired", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

            case "RegisterNewClient":
            {
                String[] row = {"A new client has been registered", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

            case "SeeAllClients":
            {
                String[] row = {"All clients have been printed", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

            case "AddStoreCreditToClient":
            {
                String[] row = {"Store credit has been added to a client", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

            case "PayRepairs":
            {
                String[] row = {"Repairs for a vehicle have been paid", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

            case "AddANewCar":
            {
                String[] row = {"A new car has been added in the service", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

            case "SeeAllCars":
            {
                String[] row = {"All cars have been printed", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

            case "ChangeCarStatus":
            {
                String[] row = {"A car has been repaired", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

            case "SeeAllRepairedCars":
            {
                String[] row = {"All repaired cars have been printed", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

            case "ResoftACar":
            {
                String[] row = {"A car has been resofted", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

            case "AddANewMotorcycle":
            {
                String[] row = {"A new motorcycle has been added in the service", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

            case "SeeAllMotorcycles":
            {
                String[] row = {"All motorcycles have been printed", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

            case "ChangeMotorcycleStatus":
            {
                String[] row = {"A motorcycle has been repaired", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

            case "SeeAllRepairedMotorcycles":
            {
                String[] row = {"All repaired motorcycles have been printed", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }

            case "ResoftAMotorcycle":
            {
                String[] row = {"A motorcycle has been resofted", formattedDateTime};
                csvWriter.writeNext(row);
                csvWriter.close();
                break;
            }
        }
    }

    public static ResultSet getAllDataFromTable(String tableName) throws SQLException
    {
        String query = "SELECT * FROM " + tableName;
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        return rs;
    }

    public static <Issue> Issue[] arrayListToIssuesArray(ArrayList<Issue> arrayList)
    {
        Issue[] array = (Issue[]) new Object[arrayList.size()];

        for(int i = 0; i < arrayList.size(); ++i)
            array[i] = arrayList.get(i);

        return array;
    }
    public static void dataInitialization() throws SQLException
    {
        clients = ClientRepository.getAllClientsFromDB();

        employees = EmployeesRepository.getAllEmployeesFromDB();

        carsInShop = CarsRepository.getAllCarsFromDB(repairedCars);

        motorcyclesInShop = MotorcyclesRepository.getAllMotorcyclesFromDB(repairedMotorcycles);
    }
    public static void registerNewClient() throws SQLException, IOException
    {
        String firstName, name, aux;

        System.out.println("Please enter the name of the client: ");
        name = inputScanner.nextLine();
        System.out.println("Please enter the first name of the client: ");
        firstName = inputScanner.nextLine();
        System.out.println("Please enter the phone number of the client: ");
        aux = inputScanner.nextLine();
        Client auxClient = new Client(name, firstName, Integer.parseInt(aux));
        clients.add(auxClient);

        ClientRepository.registerNewClient(auxClient);

        System.out.println("The Client has been registered: ");
        System.out.println(auxClient);

        writeActionInFile("RegisterNewClient", "ClientAudit.csv");
    }

    public static void printAllClients() throws IOException
    {
        System.out.println("\nThese are all the clients of this Shop: ");

        for(int i = 0; i < clients.size(); ++i)
        {
            clients.get(i).leftToBePaid(carsInShop, repairedCars, motorcyclesInShop, repairedMotorcycles);
            System.out.println("-------------------------------------------------");
            System.out.println(clients.get(i));
        }

        writeActionInFile("SeeAllClients", "ClientAudit.csv");
    }

    public static void addStoreCredit() throws SQLException, IOException
    {
        Client clientAux = new Client();
        int ok = 0;
        while (ok != 1) {
            System.out.println("Please enter the name of the client: ");
            String clientName = inputScanner.nextLine();
            System.out.println("Please enter the first name of the client: ");
            String clientFirstName = inputScanner.nextLine();
            clientAux = new Client(clientName, clientFirstName, 123);
            for (int i = 0; i < clients.size(); ++i)
            {
                if (clients.get(i).equals(clientAux))
                {
                    ok = 1;

                    System.out.println("\nHow much store credit do you want to add to " + clients.get(i).getName() + " "
                            + clients.get(i).getFirstName() + "'s balance?");
                    int toBeAddedStoreCredit = Integer.parseInt(inputScanner.nextLine());
                    clients.get(i).addStoreCredit(toBeAddedStoreCredit);

                    ClientRepository.addStoreCredit(clients.get(i));

                    break;
                }
            }
            if (ok == 0) {
                System.out.println("Please enter a valid client!");
            }
        }

        writeActionInFile("AddStoreCreditToClient", "ClientAudit.csv");
    }

    public static void payRepairs() throws SQLException, IOException
    {
        String registrationNumber;
        System.out.println("\nPlease enter the registration number of the vehicle: ");
        registrationNumber = inputScanner.nextLine();
        Car auxCar = new Car();
        auxCar.setRegistrationNumber(registrationNumber);
        Motorcycle auxMotorcycle = new Motorcycle();
        auxMotorcycle.setRegistrationNumber(registrationNumber);

        int ok = 0;
        for(int i = 0; i < carsInShop.size(); ++i)
        {
            if(carsInShop.get(i).equals(auxCar))
            {
                ok = 1;
                int repaired = carsInShop.get(i).payRepairs();

                if(repaired == 1)
                {
                    //region update paidRepairs atribute:
                    CarsRepository.payRepairs(carsInShop.get(i).getRegistrationNumber());
                    //endregion
                }

                break;
            }
        }

        for(int i = 0; i < motorcyclesInShop.size(); ++i)
        {
            if(motorcyclesInShop.get(i).equals(auxCar))
            {
                ok = 1;
                int repaired = motorcyclesInShop.get(i).payRepairs();

                if(repaired == 1)
                {
                    //region update paidRepairs atribute:
                    MotorcyclesRepository.payRepairs(motorcyclesInShop.get(i).getRegistrationNumber());
                    //endregion
                }

                break;
            }
        }

        if(ok == 1) writeActionInFile("PayRepairs", "ClientAudit.csv");
        else if(ok == 0) System.out.println("The vehicle with that registration number was not found!");
    }
    public static void hireEmployee() throws SQLException, IOException
    {
        String firstName, name, aux;

        System.out.println("Please enter the name of the employee: ");
        name = inputScanner.nextLine();
        System.out.println("Please enter the first name of the employee: ");
        firstName = inputScanner.nextLine();
        System.out.println("Please enter the phone number of the employee: ");
        aux = inputScanner.nextLine();
        System.out.println("Please enter the salary of the employee: ");
        String salary = inputScanner.nextLine();
        Employee auxEmployee = new Employee(name, firstName, Integer.parseInt(aux), Integer.parseInt(salary));
        employees.add(auxEmployee);

        EmployeesRepository.hireEmployee(auxEmployee);

        System.out.println("The Employee has been hired: ");
        System.out.println(auxEmployee);

        writeActionInFile("AddedNewEmployee", "HrAudit.csv");
    }

    public static void printAllEmployees() throws SQLException, IOException
    {
        System.out.println("\nThese are all the employees of this Shop: ");
        PriorityQueue<Employee> auxEmployees = new PriorityQueue<Employee>();
        Employee aux = new Employee();

        for(int i = 0; i <= employees.size(); ++i)
        {
            aux = employees.poll();

            System.out.println("-------------------------------------------------");
            System.out.println("\n" + aux);

            auxEmployees.add(aux);
        }

        if(employees.size() != 0)
        {
            System.out.println("-------------------------------------------------");
            System.out.println(employees.peek());
        }

        for(int i = 0; i <= auxEmployees.size(); ++i)
            employees.add(auxEmployees.poll());

        writeActionInFile("SeeAllEmployees", "HrAudit.csv");
    }

    public static void fireEmployee() throws SQLException, IOException
    {
        System.out.println("\nPlease enter the name of the employee which you want to fire:");
        String employeeName = inputScanner.nextLine();
        System.out.println("\nPlease enter the first name of the employee which you want to fire:");
        String employeeFirstName = inputScanner.nextLine();
        Employee auxEmployee = new Employee(employeeName, employeeFirstName, 123, 1);

        if(employees.remove(auxEmployee))
        {
            EmployeesRepository.fireEmployee(auxEmployee);

            System.out.println("\nThe employee has been fired!");

            writeActionInFile("FiredEmployee", "HrAudit.csv");
        }

        else System.out.println("\nThe employee was not found!");

    }

    public static void addNewCar() throws SQLException, IOException
    {
        String registrationNumber, color, fabricationYear,
                horsePower, clientName, clientFirstName,
                employeeName, employeeFirstName, issueName,
                issuePrice, manufacturer, model;
        Client clientAux = new Client();
        Employee employeeAux = new Employee();
        int EmployeeID = 0, ClientID = 0;

        System.out.println("\nPlease enter the registration number of the car: ");
        registrationNumber = inputScanner.nextLine();
        System.out.println("Please enter the color of the car: ");
        color = inputScanner.nextLine();
        System.out.println("Please enter the fabrication year of the car: ");
        fabricationYear = inputScanner.nextLine();
        System.out.println("Please enter the horse power of the car: ");
        horsePower = inputScanner.nextLine();

        int ok = 0;
        while(ok != 1)
        {
            System.out.println("Please enter the client name: ");
            clientName = inputScanner.nextLine();
            System.out.println("Please enter the client first name: ");
            clientFirstName = inputScanner.nextLine();
            clientAux = new Client(clientName, clientFirstName, 123);
            for(int i = 0; i < clients.size(); ++i)
            {
                if(clients.get(i).equals(clientAux))
                {
                    ok = 1;
                    clientAux = clients.get(i);

                    //region get Client ID from DB:
                    String query = "SELECT ID FROM clients WHERE name = ? AND firstName = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, clientAux.getName());
                    statement.setString(2, clientAux.getFirstName());
                    ResultSet clientDetails = statement.executeQuery();

                    if(clientDetails.next())
                        ClientID = clientDetails.getInt("ID");

                    //endregion

                    break;
                }
            }
            if(ok == 0)
            {
                System.out.println("Please enter a valid client!");
            }
        }

        ok = 0;
        while(ok != 1)
        {
            System.out.println("Please enter the name of the employee which works on this car: ");
            employeeName = inputScanner.nextLine();
            System.out.println("Please enter the first name of the employee which works on this car: ");
            employeeFirstName = inputScanner.nextLine();
            employeeAux = new Employee(employeeName, employeeFirstName, 1, 1);
            for(Employee e : employees)
            {
                if(e.equals(employeeAux))
                {
                    ok = 1;
                    employeeAux = e;

                    //region get Employee ID from DB:
                    String query = "SELECT ID FROM employees WHERE name = ? AND firstName = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, employeeAux.getName());
                    statement.setString(2, employeeAux.getFirstName());
                    ResultSet employeeDetails = statement.executeQuery();

                    if(employeeDetails.next())
                        EmployeeID = employeeDetails.getInt("ID");

                    //endregion

                    break;
                }
            }
            if(ok == 0)
            {
                System.out.println("Please enter a valid employee!");
            }
        }

        System.out.println("Please enter the number of the issues of the car: ");
        int numberOfIssues = Integer.parseInt(inputScanner.nextLine());
        Issue[] issues = new Issue[numberOfIssues];
        for(int i = 0; i < numberOfIssues; ++i)
        {
            System.out.println("Please enter the name of the issue: ");
            issueName = inputScanner.nextLine();
            System.out.println("Please enter the price of the issue: ");
            issuePrice = inputScanner.nextLine();
            issues[i] = new Issue(issueName, Integer.parseInt(issuePrice));
            //region add Issue to DB:
            String query = "INSERT IGNORE INTO issues (issueName, price) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, issues[i].getIssueName());
            statement.setString(2, String.valueOf(issues[i].getPrice()));
            statement.execute();
            //endregion
        }

        System.out.println("Please enter the manufacturer of the car: ");
        manufacturer = inputScanner.nextLine();
        System.out.println("Please enter the model of the car: ");
        model = inputScanner.nextLine();

        Car carAux = new Car(registrationNumber, color, Integer.parseInt(fabricationYear),
                Integer.parseInt(horsePower), clientAux, employeeAux, issues,
                manufacturer, model);
        carsInShop.add(carAux);

        CarsRepository.addNewCar(carAux, ClientID, EmployeeID, issues);

        //endregion
        System.out.println(carAux);

        writeActionInFile("AddANewCar", "VehiclesAudit.csv");
    }

    public static void printAllCarsInShop() throws IOException
    {
        System.out.println("\nThese are all the cars currently in this Shop: ");

        for(int i = 0; i < carsInShop.size(); ++i)
        {
            System.out.println("-------------------------------------------------");
            System.out.println(carsInShop.get(i));
        }

        writeActionInFile("SeeAllCars", "VehiclesAudit.csv");
    }

    public static void changeCarStatus() throws SQLException, IOException
    {
        String registrationNumber;
        System.out.println("\nPlease enter the registration number of the car: ");
        registrationNumber = inputScanner.nextLine();
        Car auxCar = new Car();
        auxCar.setRegistrationNumber(registrationNumber);

        int ok = 0;
        for(int i = 0; i < carsInShop.size(); ++i)
        {
            if(carsInShop.get(i).equals(auxCar))
            {
                ok = 1;
                carsInShop.get(i).setRepaired(true);
                auxCar = carsInShop.get(i);
                carsInShop.remove(i);
                repairedCars.add(auxCar);

                //region change isRepaired from DB:
                CarsRepository.changeCarStatus(auxCar.getRegistrationNumber());
                //endregion

                break;
            }
        }
        if(ok == 0) System.out.println("The car with that registration number was not found!");
        else
        {
            System.out.println("The car has been repaired!");

            writeActionInFile("ChangeCarStatus", "VehiclesAudit.csv");
        }
    }

    public static void printAllRepairedCars() throws IOException
    {
        System.out.println("\nThese are all the repaired cars by Shop: ");
        for(int i = 0; i < repairedCars.size(); ++i)
        {
            System.out.println("-------------------------------------------------");
            System.out.println(repairedCars.get(i));
        }

        writeActionInFile("SeeAllRepairedCars", "VehiclesAudit.csv");
    }

    public static void addHorsePowerToCar() throws SQLException, IOException
    {
        String registrationNumber;
        System.out.println("\nPlease enter the registration number of the car: ");
        registrationNumber = inputScanner.nextLine();
        Car auxCar = new Car();
        auxCar.setRegistrationNumber(registrationNumber);

        int ok = 0;
        for(int i = 0; i < carsInShop.size(); ++i)
        {
            if(carsInShop.get(i).equals(auxCar))
            {
                ok = 1;
                System.out.println("How many horse power did you add to the car: ");
                int addedHorsePower = Integer.parseInt(inputScanner.nextLine());
                carsInShop.get(i).resoftVehicle(addedHorsePower);
                System.out.println(carsInShop.get(i));

                CarsRepository.addHorsePowerToCar(carsInShop.get(i).getRegistrationNumber(), carsInShop.get(i).getHorsePower());

                break;
            }
        }
        if(ok == 0) System.out.println("The car with that registration number was not found!");
        else
        {
            System.out.println("\nThe car has been resofted!");

            writeActionInFile("ResoftACar", "VehiclesAudit.csv");
        }
    }

    public static void addNewMotorcycle() throws SQLException, IOException
    {
        String registrationNumber, color, fabricationYear,
                horsePower, clientName, clientFirstName,
                employeeName, employeeFirstName, issueName,
                issuePrice, manufacturer, model, totalMass, transmissionType;
        Client clientAux = new Client();
        Employee employeeAux = new Employee();
        int ClientID = 0, EmployeeID = 0;

        System.out.println("\nPlease enter the registration number of the motorcycle: ");
        registrationNumber = inputScanner.nextLine();
        System.out.println("Please enter the color of the motorcycle: ");
        color = inputScanner.nextLine();
        System.out.println("Please enter the fabrication year of the motorcycle: ");
        fabricationYear = inputScanner.nextLine();
        System.out.println("Please enter the horse power of the motorcycle: ");
        horsePower = inputScanner.nextLine();

        int ok = 0;
        while(ok != 1)
        {
            System.out.println("Please enter the client name: ");
            clientName = inputScanner.nextLine();
            System.out.println("Please enter the client first name: ");
            clientFirstName = inputScanner.nextLine();
            clientAux = new Client(clientName, clientFirstName, 123);
            for(int i = 0; i < clients.size(); ++i)
            {
                if(clients.get(i).equals(clientAux))
                {
                    ok = 1;
                    clientAux = clients.get(i);

                    //region get Client ID from DB:
                    String query = "SELECT ID FROM clients WHERE name = ? AND firstName = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, clientAux.getName());
                    statement.setString(2, clientAux.getFirstName());
                    ResultSet clientDetails = statement.executeQuery();

                    if(clientDetails.next())
                        ClientID = clientDetails.getInt("ID");

                    //endregion

                    break;
                }
            }
            if(ok == 0)
            {
                System.out.println("Please enter a valid client!");
            }
        }

        ok = 0;
        while(ok != 1)
        {
            System.out.println("Please enter the name of the employee which works on this motorcycle: ");
            employeeName = inputScanner.nextLine();
            System.out.println("Please enter the first name of the employee which works on this motorcycle: ");
            employeeFirstName = inputScanner.nextLine();
            employeeAux = new Employee(employeeName, employeeFirstName, 1, 1);
            for(Employee e : employees)
            {
                if(e.equals(employeeAux))
                {
                    ok = 1;
                    employeeAux = e;

                    //region get Employee ID from DB:
                    String query = "SELECT ID FROM employees WHERE name = ? AND firstName = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, employeeAux.getName());
                    statement.setString(2, employeeAux.getFirstName());
                    ResultSet employeeDetails = statement.executeQuery();

                    if(employeeDetails.next())
                        EmployeeID = employeeDetails.getInt("ID");

                    //endregion

                    break;
                }
            }
            if(ok == 0)
            {
                System.out.println("Please enter a valid employee!");
            }
        }

        System.out.println("Please enter the number of the issues of the motorcycle: ");
        int numberOfIssues = Integer.parseInt(inputScanner.nextLine());
        Issue[] issues = new Issue[numberOfIssues];
        for(int i = 0; i < numberOfIssues; ++i)
        {
            System.out.println("Please enter the name of the issue: ");
            issueName = inputScanner.nextLine();
            System.out.println("Please enter the price of the issue: ");
            issuePrice = inputScanner.nextLine();
            issues[i] = new Issue(issueName, Integer.parseInt(issuePrice));
            //region add Issue to DB:
            String query = "INSERT IGNORE INTO issues (issueName, price) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, issues[i].getIssueName());
            statement.setString(2, String.valueOf(issues[i].getPrice()));
            statement.execute();
            //endregion
        }

        System.out.println("Please enter the manufacturer of the motorcycle: ");
        manufacturer = inputScanner.nextLine();
        System.out.println("Please enter the model of the motorcycle: ");
        model = inputScanner.nextLine();
        System.out.println("Please enter the total mass of the motorcycle: ");
        totalMass = inputScanner.nextLine();
        System.out.println("Please enter the transmission type of the motorcycle: ");
        transmissionType = inputScanner.nextLine();

        Motorcycle motorcycleAux = new Motorcycle(registrationNumber, color, Integer.parseInt(fabricationYear),
                Integer.parseInt(horsePower), clientAux, employeeAux, issues,
                manufacturer, model, Integer.parseInt(totalMass), transmissionType);
        motorcyclesInShop.add(motorcycleAux);

        //region add motorcycle to DB:
        MotorcyclesRepository.addNewMotorcycle(motorcycleAux, ClientID, EmployeeID, issues);
        //endregion
        System.out.println(motorcycleAux);

        writeActionInFile("AddANewMotorcycle", "VehiclesAudit.csv");
    }

    public static void printAllMotorcyclesInShop() throws IOException
    {
        System.out.println("\nThese are all motorcycles in this Shop: ");

        for(int i = 0; i < motorcyclesInShop.size(); ++i)
        {
            System.out.println("-------------------------------------------------");
            System.out.println(motorcyclesInShop.get(i));
        }

        writeActionInFile("SeeAllMotorcycles", "VehiclesAudit.csv");
    }

    public static void changeMotorcycleStatus() throws SQLException, IOException
    {
        String registrationNumber;
        System.out.println("\nPlease enter the registration number of the motorcycle: ");
        registrationNumber = inputScanner.nextLine();
        Motorcycle auxMotorcycle = new Motorcycle();
        auxMotorcycle.setRegistrationNumber(registrationNumber);

        int ok = 0;
        for(int i = 0; i < motorcyclesInShop.size(); ++i)
        {
            if(motorcyclesInShop.get(i).equals(auxMotorcycle))
            {
                ok = 1;
                motorcyclesInShop.get(i).setRepaired(true);
                auxMotorcycle = motorcyclesInShop.get(i);
                motorcyclesInShop.remove(i);
                repairedMotorcycles.add(auxMotorcycle);

                //region change isRepaired from DB:
                MotorcyclesRepository.changeMotorcycleStatus(motorcyclesInShop.get(i).getRegistrationNumber());
                //endregion

                break;
            }
        }
        if(ok == 0) System.out.println("The motorcycle with that registration number was not found!");
        else
        {
            System.out.println("The motorcycle has been repaired!");

            writeActionInFile("ChangeMotorcycleStatus", "VehiclesAudit.csv");
        }
    }

    public static void printAllRepairedMotorcycles() throws IOException
    {
        System.out.println("\nThese are all motorcycles repaired by the Shop: ");

        for(int i = 0; i < repairedMotorcycles.size(); ++i)
        {
            System.out.println("-------------------------------------------------");
            System.out.println(repairedMotorcycles.get(i));
        }

        writeActionInFile("SeeAllRepairedMotorcycles", "VehiclesAudit.csv");
    }

    public static void addHorsePowerToMotorcycle() throws SQLException, IOException
    {
        String registrationNumber;
        System.out.println("\nPlease enter the registration number of the motorcycle: ");
        registrationNumber = inputScanner.nextLine();
        Motorcycle auxMotorcycle = new Motorcycle();
        auxMotorcycle.setRegistrationNumber(registrationNumber);

        int ok = 0;
        for(int i = 0; i < motorcyclesInShop.size(); ++i)
        {
            if(motorcyclesInShop.get(i).equals(auxMotorcycle))
            {
                ok = 1;
                System.out.println("How many horse power did you add to the motorcycle: ");
                int addedHorsePower = Integer.parseInt(inputScanner.nextLine());
                motorcyclesInShop.get(i).resoftVehicle(addedHorsePower);
                System.out.println(motorcyclesInShop.get(i));

                MotorcyclesRepository.addHorsePowerToMotorcycle(motorcyclesInShop.get(i).getRegistrationNumber(),
                        motorcyclesInShop.get(i).getHorsePower());

                break;
            }
        }
        if(ok == 0) System.out.println("The motorcycle with that registration number was not found!");
        else
        {
            System.out.println("\nThe motorcycle has been resofted!");

            writeActionInFile("ResoftAMotorcycle", "VehiclesAudit.csv");
        }
    }

    public static void searchVehiclesFromCounty()
    {
        int counter = 0;

        String county;
        System.out.println("\nPlease enter the desired county:");
        county = inputScanner.nextLine();

        System.out.println("\nThese are all the vehicles from the county: ");

        String regex = county + "[0-9]+[A-Za-z]+";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        for(Car car : carsInShop)
        {
            Matcher matcher = pattern.matcher(car.getRegistrationNumber());
            if(matcher.find())
            {
                ++counter;
                System.out.println("-------------------------------------------------");
                System.out.println(car);
            }
        }

        for(Motorcycle motorcycle : motorcyclesInShop)
        {
            Matcher matcher = pattern.matcher(motorcycle.getRegistrationNumber());
            if(matcher.find())
            {
                ++counter;
                System.out.println("-------------------------------------------------");
                System.out.println(motorcycle);
            }
        }

        for(Car car : repairedCars)
        {
            Matcher matcher = pattern.matcher(car.getRegistrationNumber());
            if(matcher.find())
            {
                ++counter;
                System.out.println("-------------------------------------------------");
                System.out.println(car);
            }
        }

        for(Motorcycle motorcycle : repairedMotorcycles)
        {
            Matcher matcher = pattern.matcher(motorcycle.getRegistrationNumber());
            if(matcher.find())
            {
                ++counter;
                System.out.println("-------------------------------------------------");
                System.out.println(motorcycle);
            }
        }

        if (counter == 0) System.out.println("No vehicles from that county were found");

    }

    public static void getAverageRepairCost()
    {
        int total = 0;
        int counter = 0;

        for(Car car : carsInShop)
        {
            ++counter;
            total += car.getTotal();
        }

        for(Motorcycle motorcycle : motorcyclesInShop)
        {
           ++counter;
           total += motorcycle.getTotal();
        }

        for(Car car : repairedCars)
        {
            ++counter;
            total += car.getTotal();
        }

        for(Motorcycle motorcycle : repairedMotorcycles)
        {
            ++counter;
           total += motorcycle.getTotal();
        }

        System.out.println("Average repair cost: " + total/counter);
    }

    public static void percentageOfShop_RepairedVehicles()
    {
        int vehiclesInService = carsInShop.size() + motorcyclesInShop.size();
        int repairedVehicles = vehiclesInService + repairedCars.size() + repairedMotorcycles.size();
        int inShopPercentage = (vehiclesInService * 100) / repairedVehicles;

        System.out.println("Percentage of vehicles inside the shop: " + inShopPercentage + "%");
        System.out.println("Percentage of vehicles repaired by the shop: " + (100 - inShopPercentage) + "%");
    }

    public static void percentageOfCars_Motorcycles()
    {
        int numberOfCars = carsInShop.size() + repairedCars.size();
        int numberOfVehicles = numberOfCars + motorcyclesInShop.size() + repairedMotorcycles.size();
        int carsPercentage = (numberOfCars * 100) / numberOfVehicles;

        System.out.println("Percentage of cars out of all vehicles: " + carsPercentage + "%");
        System.out.println("Percentage of motorcycles out of all vehicles: " + (100 - carsPercentage) + "%");
    }
}
