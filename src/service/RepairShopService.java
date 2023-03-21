package service;

import model.*;

import java.util.*;

public class RepairShopService
{
    private static List<Client> clients = new ArrayList<Client>();
    private static PriorityQueue<Employee> employees = new PriorityQueue<Employee>();
    private static List<Car> carsInShop = new ArrayList<Car>();
    private static List<Car> repairedCars = new ArrayList<Car>();
    private static List<Motorcycle> motorcyclesInShop = new ArrayList<Motorcycle>();
    private static List<Motorcycle> repairedMotorcycles = new ArrayList<Motorcycle>();
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
            System.out.println("-------------------------------------------------");
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
            employeeAux = new Employee(employeeName, employeeFirstName, 1, 1);
            for(Employee e : employees)
            {
                if(e.equals(employeeAux))
                {
                    ok = 1;
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

        System.out.println("Please enter the manufacturer of the car: ");
        manufacturer = inputScanner.nextLine();
        System.out.println("Please enter the model of the car: ");
        model = inputScanner.nextLine();

        Car carAux = new Car(registrationNumber, color, Integer.parseInt(fabricationYear),
                Integer.parseInt(horsePower), clientAux, employeeAux, issues,
                manufacturer, model);
        carsInShop.add(carAux);
        System.out.println(carAux);
    }

    public static void printAllCarsInShop()
    {
        System.out.println("\nThese are all the cars currently in this Shop: ");

        for(int i = 0; i < carsInShop.size(); ++i)
        {
            System.out.println("-------------------------------------------------");
            System.out.println(carsInShop.get(i));
        }
    }

    public static void changeCarStatus()
    {
        String registrationNumber;
        System.out.println("\nPlease enter the registration number of the car: ");
        registrationNumber = inputScanner.nextLine();
        Car auxCar = new Car();
        auxCar.setRegistrationNumber(registrationNumber);

        int ok = 0;
        for(int i = 0; i <= carsInShop.size(); ++i)
        {
            if(carsInShop.get(i).equals(auxCar))
            {
                ok = 1;

                auxCar = carsInShop.get(i);
                carsInShop.remove(i);
                repairedCars.add(auxCar);
                break;
            }
        }
        if(ok == 0) System.out.println("The car with that registration number was not found!");
        else System.out.println("The car has been repaired!");
    }

    public static void printAllRepairedCars()
    {
        System.out.println("\nThese are all the repaired cars by Shop: ");
        for(int i = 0; i < repairedCars.size(); ++i)
        {
            System.out.println("-------------------------------------------------");
            System.out.println(repairedCars.get(i));
        }
    }

    public static void addHorsePowerToCar()
    {
        String registrationNumber;
        System.out.println("\nPlease enter the registration number of the car: ");
        registrationNumber = inputScanner.nextLine();
        Car auxCar = new Car();
        auxCar.setRegistrationNumber(registrationNumber);

        int ok = 0;
        for(int i = 0; i <= carsInShop.size(); ++i)
        {
            if(carsInShop.get(i).equals(auxCar))
            {
                ok = 1;
                System.out.println("How many horse power did you add to the car: ");
                int addedHorsePower = Integer.parseInt(inputScanner.nextLine());
                carsInShop.get(i).resoftVehicle(addedHorsePower);
                System.out.println(carsInShop.get(i));
                break;
            }
        }
        if(ok == 0) System.out.println("The car with that registration number was not found!");
        else System.out.println("\nThe car has been resofted!");
    }

    public static void addNewMotorcycle()
    {
        String registrationNumber, color, fabricationYear,
                horsePower, clientName, clientFirstName,
                employeeName, employeeFirstName, issueName,
                issuePrice, manufacturer, model, totalMass, transmissionType;
        Client clientAux = new Client();
        Employee employeeAux = new Employee();

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
        System.out.println(motorcycleAux);
    }

    public static void printAllMotorcyclesInShop()
    {
        System.out.println("\nThese are all motorcycles in this Shop: ");

        for(int i = 0; i < motorcyclesInShop.size(); ++i)
        {
            System.out.println("-------------------------------------------------");
            System.out.println(motorcyclesInShop.get(i));
        }
    }

    public static void changeMotorcycleStatus()
    {
        String registrationNumber;
        System.out.println("\nPlease enter the registration number of the motorcycle: ");
        registrationNumber = inputScanner.nextLine();
        Motorcycle auxMotorcycle = new Motorcycle();
        auxMotorcycle.setRegistrationNumber(registrationNumber);

        int ok = 0;
        for(int i = 0; i <= motorcyclesInShop.size(); ++i)
        {
            if(motorcyclesInShop.get(i).equals(auxMotorcycle))
            {
                ok = 1;

                auxMotorcycle = motorcyclesInShop.get(i);
                motorcyclesInShop.remove(i);
                repairedMotorcycles.add(auxMotorcycle);
                break;
            }
        }
        if(ok == 0) System.out.println("The motorcycle with that registration number was not found!");
        else System.out.println("The motorcycle has been repaired!");
    }

    public static void printAllRepairedMotorcycles()
    {
        System.out.println("\nThese are all motorcycles repaired by the Shop: ");

        for(int i = 0; i < repairedMotorcycles.size(); ++i)
        {
            System.out.println("-------------------------------------------------");
            System.out.println(repairedMotorcycles.get(i));
        }
    }

    public static void addHorsePowerToMotorcycle()
    {
        String registrationNumber;
        System.out.println("\nPlease enter the registration number of the motorcycle: ");
        registrationNumber = inputScanner.nextLine();
        Motorcycle auxMotorcycle = new Motorcycle();
        auxMotorcycle.setRegistrationNumber(registrationNumber);

        int ok = 0;
        for(int i = 0; i <= motorcyclesInShop.size(); ++i)
        {
            if(motorcyclesInShop.get(i).equals(auxMotorcycle))
            {
                ok = 1;
                System.out.println("How many horse power did you add to the motorcycle: ");
                int addedHorsePower = Integer.parseInt(inputScanner.nextLine());
                motorcyclesInShop.get(i).resoftVehicle(addedHorsePower);
                System.out.println(motorcyclesInShop.get(i));
                break;
            }
        }
        if(ok == 0) System.out.println("The motorcycle with that registration number was not found!");
        else System.out.println("\nThe motorcycle has been resofted!");
    }
}
