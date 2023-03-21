package service;

import model.Client;
import model.Employee;
import model.Issue;
import model.Car;

import java.util.*;

public class RepairShopService
{
    private static List<Client> clients = new ArrayList<Client>();
    private static PriorityQueue<Employee> employees = new PriorityQueue<Employee>();
    private static Scanner inputScanner = new Scanner(System.in);

    public static void registerNewClient()
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

        System.out.println("The Client has been registered: ");
        System.out.println(auxClient);
    }

    public static void printAllClients()
    {
        System.out.println("\nThese are all the clients of this Shop: ");

        for(int i = 0; i < clients.size(); ++i)
        {
            System.out.println("-------------------------------------------------");
            System.out.println(clients.get(i));
        }
    }

    public static void hireEmployee()
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

        System.out.println("The Employee has been hired: ");
        System.out.println(auxEmployee);
    }

    public static void printAllEmployees()
    {
        System.out.println("\nThese are all the employees of this Shop: ");

        Iterator<Employee> iterate = employees.iterator();
        while (iterate.hasNext())
        {
            System.out.println("\n" + iterate.next());
        }
    }

    public static void fireEmployee()
    {
        System.out.println("\nPlease enter the name of the employee which you want to fire:");
        String employeeName = inputScanner.nextLine();
        System.out.println("\nPlease enter the first name of the employee which you want to fire:");
        String employeeFirstName = inputScanner.nextLine();
        Employee auxEmployee = new Employee(employeeName, employeeFirstName, 123, 1);

        if(employees.remove(auxEmployee))
        {
            System.out.println("\nThe employee has been fired!");
        }

        else System.out.println("\nThe employee was not found!");

    }

    public static void addNewCar()
    {
        String registrationNumber, color, fabricationYear,
                horsePower, clientName, clientFirstName,
                employeeName, employeeFirstName, issueName,
                issuePrice, manufacturer, model;
        Client clientAux = new Client();
        Employee employeeAux = new Employee();

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
            for(Employee e : employees)
            {
                if(e.getName() == employeeName && e.getFirstName() == employeeFirstName)
                {
                    employeeAux = e;
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
        }

        

        Car car = new Car("B27FLG", "grey", 2022,
                130, clientAux, employeeAux, issues,
                "Volkswagen", "Golf VIII");

        System.out.println(car);
    }
}
