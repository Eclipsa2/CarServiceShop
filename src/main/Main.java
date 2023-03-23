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
        System.out.println("7. See all cars currently in the shop");
        System.out.println("8. Change car status");
        System.out.println("9. See all repaired cars");
        System.out.println("10. Resoft a car (add more horsepower)");
        System.out.println("-------------------------------------------------");
        System.out.println("11. Add a new motorcycle in the service");
        System.out.println("12. See all motorcycles currently in the shop");
        System.out.println("13. Change motorcycle status");
        System.out.println("14. See all repaired motorcycles");
        System.out.println("15. Resoft a motorcycle (add more horsepower)");
        System.out.println("-------------------------------------------------");
        System.out.println("0. Exit");

        Scanner inputScanner = new Scanner(System.in);
        String stringOption = inputScanner.nextLine();
        int option = Integer.parseInt(stringOption);

        while(option > 15)
        {
            System.out.println("\n Please enter a valid option!");
            stringOption = inputScanner.nextLine();
            option = Integer.parseInt(stringOption);
        }

        return option;
    }

    public static int menu2()
    {
        //asdasda
        return 1;
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
                case 7:
                {
                    RepairShopService.printAllCarsInShop();
                    option = menu();
                    break;
                }
                case 8:
                {
                    RepairShopService.changeCarStatus();
                    option = menu();
                    break;
                }
                case 9:
                {
                    RepairShopService.printAllRepairedCars();
                    option = menu();
                    break;
                }
                case 10:
                {
                    RepairShopService.addHorsePowerToCar();
                    option = menu();
                    break;
                }
                case 11:
                {
                    RepairShopService.addNewMotorcycle();
                    option = menu();
                    break;
                }
                case 12:
                {
                    RepairShopService.printAllMotorcyclesInShop();
                    option = menu();
                    break;
                }
                case 13:
                {
                    RepairShopService.changeMotorcycleStatus();
                    option = menu();
                    break;
                }
                case 14:
                {
                    RepairShopService.printAllRepairedMotorcycles();
                    option = menu();
                    break;
                }
                case 15:
                {
                    RepairShopService.addHorsePowerToMotorcycle();
                    option = menu();
                    break;
                }
            }
        }

    }
}