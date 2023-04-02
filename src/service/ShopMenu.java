package service;

import main.Main;

import java.util.Scanner;

public class ShopMenu {

    public static int readOption(int numberOfOptions)
    {
        Scanner inputScanner = new Scanner(System.in);
        String stringOption = inputScanner.nextLine();
        int option = Integer.parseInt(stringOption);

        while(option > numberOfOptions)
        {
            System.out.println("\n Please enter a valid option!");
            stringOption = inputScanner.nextLine();
            option = Integer.parseInt(stringOption);
        }

        return option;
    }

    public static int carRepairsMenu()
    {
        System.out.println("-------------------------------------------------");
        System.out.println("1. Add a new car in the service");
        System.out.println("2. See all cars currently in the shop");
        System.out.println("3. Change car status");
        System.out.println("4. See all repaired cars");
        System.out.println("5. Resoft a car (add more horsepower)");
        System.out.println("-------------------------------------------------");
        System.out.println("0. Main menu");
        System.out.println("-------------------------------------------------");

        return readOption(5);
    }
    public static int clientMenu()
    {
        System.out.println("-------------------------------------------------");
        System.out.println("1. Register new Client");
        System.out.println("2. See all clients");
        System.out.println("3. Add store credit to client");
        System.out.println("4. Pay Repairs");
        System.out.println("-------------------------------------------------");
        System.out.println("0. Main menu");
        System.out.println("-------------------------------------------------");

        return readOption(4);
    }
    public static int hrMenu()
    {
        System.out.println("-------------------------------------------------");
        System.out.println("1. Register new Employee");
        System.out.println("2. See all Employees");
        System.out.println("3. Fire an Employee");
        System.out.println("-------------------------------------------------");
        System.out.println("0. Main menu");
        System.out.println("-------------------------------------------------");

        return readOption(3);
    }

    public static int motorcycleRepairsMenu()
    {
        System.out.println("-------------------------------------------------");
        System.out.println("1. Add a new motorcycle in the service");
        System.out.println("2. See all motorcycles currently in the shop");
        System.out.println("3. Change motorcycle status");
        System.out.println("4. See all repaired motorcycles");
        System.out.println("5. Resoft a motorcycle (add more horsepower)");
        System.out.println("-------------------------------------------------");
        System.out.println("0. Main menu");
        System.out.println("-------------------------------------------------");

        return readOption(5);
    }
    public static int mainMenu()
    {
        int option = Main.menu();
        RepairShopService.dataInitialization();
        while (option != 0)
        {
            switch (option)
            {
                case 1:
                {
                    int optionSubmenu = hrMenu();
                    while (optionSubmenu != 0)
                    {
                        switch (optionSubmenu)
                        {
                            case 1:
                            {
                                RepairShopService.hireEmployee();
                                optionSubmenu = hrMenu();
                                break;
                            }
                            case 2:
                            {
                                RepairShopService.printAllEmployees();
                                optionSubmenu = hrMenu();
                                break;
                            }
                            case 3:
                            {
                                RepairShopService.fireEmployee();
                                optionSubmenu = hrMenu();
                                break;
                            }
                        }
                    }
                    option = Main.menu();
                    break;
                }
                case 2:
                {
                    int optionSubmenu = clientMenu();
                    while(optionSubmenu != 0)
                    {
                        switch (optionSubmenu)
                        {
                            case 1:
                            {
                                RepairShopService.registerNewClient();
                                optionSubmenu = clientMenu();
                                break;
                            }
                            case 2:
                            {
                                RepairShopService.printAllClients();
                                optionSubmenu = clientMenu();
                                break;
                            }
                            case 3:
                            {
                                RepairShopService.addStoreCredit();
                                optionSubmenu = clientMenu();
                                break;
                            }
                            case 4:
                            {
                                RepairShopService.repairVehicle();
                                optionSubmenu = clientMenu();
                                break;
                            }
                        }
                    }
                    option = Main.menu();
                    break;
                }
                case 3:
                {
                    int optionSubmenu = carRepairsMenu();
                    while(optionSubmenu != 0) {
                        switch (optionSubmenu)
                        {
                            case 1:
                            {
                                RepairShopService.addNewCar();
                                optionSubmenu = carRepairsMenu();
                                break;
                            }
                            case 2:
                            {
                                RepairShopService.printAllCarsInShop();
                                optionSubmenu = carRepairsMenu();
                                break;
                            }
                            case 3:
                            {
                                RepairShopService.changeCarStatus();
                                optionSubmenu = carRepairsMenu();
                                break;
                            }
                            case 4:
                            {
                                RepairShopService.printAllRepairedCars();
                                optionSubmenu = carRepairsMenu();
                                break;
                            }
                            case 5:
                            {
                                RepairShopService.addHorsePowerToCar();
                                optionSubmenu = carRepairsMenu();
                                break;
                            }
                        }
                    }
                    option = Main.menu();
                    break;
                }
                case 4:
                {
                    int optionSubmenu = motorcycleRepairsMenu();
                    while(optionSubmenu != 0) {
                        switch (optionSubmenu)
                        {
                            case 1:
                            {
                                RepairShopService.addNewMotorcycle();
                                optionSubmenu = motorcycleRepairsMenu();
                                break;
                            }
                            case 2:
                            {
                                RepairShopService.printAllMotorcyclesInShop();
                                optionSubmenu = motorcycleRepairsMenu();
                                break;
                            }
                            case 3:
                            {
                                RepairShopService.changeMotorcycleStatus();
                                optionSubmenu = motorcycleRepairsMenu();
                                break;
                            }
                            case 4:
                            {
                                RepairShopService.printAllRepairedMotorcycles();
                                optionSubmenu = motorcycleRepairsMenu();
                                break;
                            }
                            case 5:
                            {
                                RepairShopService.addHorsePowerToMotorcycle();
                                optionSubmenu = motorcycleRepairsMenu();
                                break;
                            }
                        }
                    }
                    option = Main.menu();
                    break;
                }
            }
        }

        return 0;
    }
}
