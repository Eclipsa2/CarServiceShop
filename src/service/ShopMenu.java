package service;

import main.Main;

public class ShopMenu {
    public static int mainMenu()
    {
        int option = Main.menu();
        while (option != 0)
        {
            switch (option)
            {
                case 1:
                {
                    RepairShopService.registerNewClient();
                    option = Main.menu();
                    break;
                }
                case 2:
                {
                    RepairShopService.printAllClients();
                    option = Main.menu();
                    break;
                }
                case 3:
                {
                    RepairShopService.hireEmployee();
                    option = Main.menu();
                    break;
                }
                case 4:
                {
                    RepairShopService.printAllEmployees();
                    option = Main.menu();
                    break;
                }
                case 5:
                {
                    RepairShopService.fireEmployee();
                    option = Main.menu();
                    break;
                }
                case 6:
                {
                    RepairShopService.addNewCar();
                    option = Main.menu();
                    break;
                }
                case 7:
                {
                    RepairShopService.printAllCarsInShop();
                    option = Main.menu();
                    break;
                }
                case 8:
                {
                    RepairShopService.changeCarStatus();
                    option = Main.menu();
                    break;
                }
                case 9:
                {
                    RepairShopService.printAllRepairedCars();
                    option = Main.menu();
                    break;
                }
                case 10:
                {
                    RepairShopService.addHorsePowerToCar();
                    option = Main.menu();
                    break;
                }
                case 11:
                {
                    RepairShopService.addNewMotorcycle();
                    option = Main.menu();
                    break;
                }
                case 12:
                {
                    RepairShopService.printAllMotorcyclesInShop();
                    option = Main.menu();
                    break;
                }
                case 13:
                {
                    RepairShopService.changeMotorcycleStatus();
                    option = Main.menu();
                    break;
                }
                case 14:
                {
                    RepairShopService.printAllRepairedMotorcycles();
                    option = Main.menu();
                    break;
                }
                case 15:
                {
                    RepairShopService.addHorsePowerToMotorcycle();
                    option = Main.menu();
                    break;
                }
            }
        }

        return 0;
    }
}
