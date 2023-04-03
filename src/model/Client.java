package model;

import java.util.List;

public class Client extends Person
{
    private int leftToBePaid = 0;
    private int storeCredit = 0;
    private boolean paidRepairs = false;

    public Client() {
    }

    public void addStoreCredit(int toBeAdded)
    {
        this.storeCredit += toBeAdded;
        if(toBeAdded > 0)
        {
            System.out.println(toBeAdded + " euro have been added to the account");
        }
        else
        {
            System.out.println((toBeAdded * (-1)) + " euro have been paid");
        }

    }
    @Override
    public boolean equals(Object o)
    {
        return (this.getFirstName().equals(((Client)o).getFirstName())
                &&
                this.getName().equals(((Client)o).getName()));
    }
    public void leftToBePaid(List<Car> carsInShop, List<Car> repairedCars,
                             List <Motorcycle> motorcyclesInShop, List <Motorcycle> repairedMotorcycles)
    {
        for(Car car : carsInShop)
        {
            if(car.getOwner() == this)
            {
                leftToBePaid += car.getTotal();
            }
        }

        for(Motorcycle motorcycle : motorcyclesInShop)
        {
            if(motorcycle.getOwner() == this)
            {
                leftToBePaid += motorcycle.getTotal();
            }
        }

        for(Car car : repairedCars)
        {
            if(car.getOwner() == this)
            {
                leftToBePaid += car.getTotal();
            }
        }

        for(Motorcycle motorcycle : repairedMotorcycles)
        {
            if(motorcycle.getOwner() == this)
            {
                leftToBePaid += motorcycle.getTotal();
            }
        }

        if(leftToBePaid == 0) paidRepairs = true;
        else paidRepairs = false;
    }
    @Override
    public String toString()
    {
        return (
                "\nFirst Name: " + this.getFirstName() +
                "\nLast Name: " + this.getName() +
                "\nPhone Number: " + this.getPhoneNumber() +
                "\nCurrent Balance: " + storeCredit + " euro" +
                "\nLeft to be paid to the service: " + leftToBePaid + " euro " +
                "\nPaid Services: " + this.paidRepairs
                );
    }

    public Client(String name, String firstName, int phoneNumber) {
        super(name, firstName, phoneNumber);
    }

    public boolean isPaidRepairs() {
        return paidRepairs;
    }

    public void setPaidRepairs(boolean paidRepairs) {
        this.paidRepairs = paidRepairs;
    }

    public int getStoreCredit()
    {
        return storeCredit;
    }
}
