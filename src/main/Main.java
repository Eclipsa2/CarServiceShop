package main;

import com.sun.source.tree.Tree;
import model.*;
import service.RepairShopService;

import java.util.*;

public class Main {
    public static int menu ()
    {
        System.out.println("\n-------------------------------------------------");
        System.out.println("Welcome to our Vehicles Service!");
        System.out.println("-------------------------------------------------");
        System.out.println("1. Register new Client");
        System.out.println("2. See all clients");
        System.out.println("3. Register new Employee");
        System.out.println("4. See all Employees");
        System.out.println("5. Fire an Employee");
        System.out.println("-------------------------------------------------");
        System.out.println("6. Add a new car in the service");
        System.out.println("7. Change car status");
        System.out.println("8. See all repaired cars");
        System.out.println("9. Resoft a car (add more horsepower)");
        System.out.println("-------------------------------------------------");
        System.out.println("10. Add a new motorcycle in the service");
        System.out.println("11. Change motorcycle status");
        System.out.println("12. See all repaired motorcycles");
        System.out.println("13. Resoft a motorcycle (add more horsepower)");
        System.out.println("-------------------------------------------------");
        System.out.println("0. Exit");

        Scanner inputScanner = new Scanner(System.in);
        String stringOption = inputScanner.nextLine();
        int option = Integer.parseInt(stringOption);

        while(option > 13)
        {
            System.out.println("\n Please enter a valid option!");
            stringOption = inputScanner.nextLine();
            option = Integer.parseInt(stringOption);
        }

        return option;
    }

    public static void main(String[] args)
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

        Scanner inputScanner = new Scanner(System.in);
        String name, firstName, aux;
        int option = menu();

        while (option != 0)
        {
            switch (option)
            {
                case 1:
                {
                    RepairShopService.registerNewClient();
                    option = menu();
                    break;
                }
                case 2:
                {
                    RepairShopService.printAllClients();
                    option = menu();
                    break;
                }
                case 3:
                {
                    RepairShopService.hireEmployee();
                    option = menu();
                    break;
                }
                case 4:
                {
                    RepairShopService.printAllEmployees();
                    option = menu();
                    break;
                }
                case 5:
                {
                    RepairShopService.fireEmployee();
                    option = menu();
                    break;
                }
                case 6:
                {
                    RepairShopService.addNewCar();
                    option = menu();
                    break;
                }
            }
        }

    }
}