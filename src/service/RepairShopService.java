package service;

import model.*;

import java.util.*;
import java.util.regex.*;

public class RepairShopService
{
    private static List<Client> clients = new ArrayList<Client>();
    private static PriorityQueue<Employee> employees = new PriorityQueue<Employee>();
    private static List<Car> carsInShop = new ArrayList<Car>();
    private static List<Car> repairedCars = new ArrayList<Car>();
    private static List<Motorcycle> motorcyclesInShop = new ArrayList<Motorcycle>();
    private static List<Motorcycle> repairedMotorcycles = new ArrayList<Motorcycle>();
    private static Scanner inputScanner = new Scanner(System.in);

    public static void dataInitialization()
    {
        clients.add(new Client("Ilie", "Andrei", 123456789));
        clients.add(new Client("Ilie", "Virgil", 123456789));
        clients.add(new Client("Anton", "Marius", 123456789));

        employees.add(new Employee("Nicolae", "Anemtoaicei", 123456789, 2500));
        employees.add(new Employee("Daldo", "Delevigne", 123456789, 3700));
        employees.add(new Employee("Pomelo", "Necsoiu", 123456789, 2900));
        employees.add(new Employee("a", "a", 123456789, 9000000));

        Issue[] issues1 = new Issue[2];
        issues1[0] = new Issue("Engine Problems", 2000);
        issues1[1] = new Issue("Sofware Problems", 500);

        Issue[] issues2 = new Issue[3];
        issues2[0] = new Issue("Body work", 1200);
        issues2[1] = new Issue("Injector problems", 700);
        issues2[2] = new Issue("Winter Tyres Change", 300);

        Car car1 = new Car("B27FLG", "grey", 2022,
                130, clients.get(0), employees.peek(), issues1,
                "Volkswagen", "Golf VIII");

        Car car2 = new Car("B127HWT", "white", 2011,
                80, clients.get(1), employees.peek(), issues2,
                "Ford", "Focus MK.2");

        Car car3 = new Car("B102BBU", "grey", 2020,
                90, clients.get(2), employees.peek(), issues1,
                "Hyundai", "I20");

        Car car4 = new Car("IF23QWR", "yellow", 2023,
                420, clients.get(0), employees.peek(), issues1,
                "Mercedes-Benz", "AMG A45S");

        Car car5 = new Car("CT183BWR", "blue", 2017,
                100, clients.get(1), employees.peek(), issues2,
                "Hyundai", "Tucson");

        Car car6 = new Car("B73HLO", "green", 2020,
                220, clients.get(2), employees.peek(), issues1,
                "BMW", "320i");

        Car car7 = new Car("CT20HEV", "blue", 2021,
                650, clients.get(1), employees.peek(), issues2,
                "Audi", "RS6");

        carsInShop.add(car1);
        carsInShop.add(car2);
        carsInShop.add(car3);
        carsInShop.add(car4);

        repairedCars.add(car5);
        repairedCars.add(car6);
        repairedCars.add(car7);

        Motorcycle motorcycle1 = new Motorcycle("B123ABC", "black", 2023,
                230, clients.get(0), employees.peek(), issues1,
                "Yamaha", "YZF R", 300, "6 Speed Double Clutch");

        Motorcycle motorcycle2 = new Motorcycle("B23QPQ", "orange", 2019,
                125, clients.get(1), employees.peek(), issues2,
                "BMW", "Zonda", 276, "6 Speed Simple Clutch");

        Motorcycle motorcycle3 = new Motorcycle("CT75AQP", "white", 2015,
                120, clients.get(2), employees.peek(), issues2,
                "Suzuki", "Max 123", 300, "6 Speed Double Clutch");

        Motorcycle motorcycle4 = new Motorcycle("CT951AOL", "black", 2023,
                170, clients.get(0), employees.peek(), issues1,
                "Yamaha", "H123Q", 220, "6 Speed Double Clutch");

        motorcyclesInShop.add(motorcycle1);
        motorcyclesInShop.add(motorcycle2);

        repairedMotorcycles.add(motorcycle3);
        repairedMotorcycles.add(motorcycle4);

    }
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
            clients.get(i).leftToBePaid(carsInShop, repairedCars, motorcyclesInShop, repairedMotorcycles);
            System.out.println("-------------------------------------------------");
            System.out.println(clients.get(i));
        }
    }

    public static void addStoreCredit() {
        Client clientAux = new Client();
        int ok = 0;
        while (ok != 1) {
            System.out.println("Please enter the name of the client: ");
            String clientName = inputScanner.nextLine();
            System.out.println("Please enter the first name of the client: ");
            String clientFirstName = inputScanner.nextLine();
            clientAux = new Client(clientName, clientFirstName, 123);
            for (int i = 0; i < clients.size(); ++i) {
                if (clients.get(i).equals(clientAux)) {
                    ok = 1;

                    System.out.println("\nHow much store credit do you want to add to " + clients.get(i).getName() + " "
                            + clients.get(i).getFirstName() + "'s balance?");
                    int toBeAddedStoreCredit = Integer.parseInt(inputScanner.nextLine());
                    clients.get(i).addStoreCredit(toBeAddedStoreCredit);

                    break;
                }
            }
            if (ok == 0) {
                System.out.println("Please enter a valid client!");
            }
        }
    }

    public static void repairVehicle()
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
                carsInShop.get(i).payRepairs();
                break;
            }
        }
        if(ok == 0) System.out.println("The car with that registration number was not found!");
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
        PriorityQueue<Employee> auxEmployees = new PriorityQueue<Employee>();
        Employee aux = new Employee();

        for(int i = 0; i <= employees.size(); ++i)
        {
            aux = employees.poll();

            System.out.println("-------------------------------------------------");
            System.out.println("\n" + aux);

            auxEmployees.add(aux);
        }

        for(int i = 0; i <= auxEmployees.size(); ++i)
            employees.add(auxEmployees.poll());
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
        for(int i = 0; i < carsInShop.size(); ++i)
        {
            if(carsInShop.get(i).equals(auxCar))
            {
                ok = 1;
                carsInShop.get(i).setRepaired(true);
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
        for(int i = 0; i < carsInShop.size(); ++i)
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
        for(int i = 0; i < motorcyclesInShop.size(); ++i)
        {
            if(motorcyclesInShop.get(i).equals(auxMotorcycle))
            {
                ok = 1;
                motorcyclesInShop.get(i).setRepaired(true);
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
        for(int i = 0; i < motorcyclesInShop.size(); ++i)
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
