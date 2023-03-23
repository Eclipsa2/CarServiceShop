package main;

import model.*;
import service.ShopMenu;

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
        System.out.println("0. Log out");

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
        String name, aux;

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

                        for(int i = 0; i < users.size(); ++i)
                        {
                            if(users.get(i).equals(userAux))
                            {
                                option = ShopMenu.mainMenu();
                                ok = 1;
                                break;
                            }
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

                    users.add(userAux);
                    option = menuLogin();
                    break;
                }
            }
        }
    }
}