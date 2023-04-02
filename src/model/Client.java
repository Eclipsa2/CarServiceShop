package model;

public class Client extends Person
{
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

    @Override
    public String toString()
    {
        return (
                "Name: " + this.getName() +
                "\nFirst Name: " + this.getFirstName() +
                "\nLast Name: " + this.getName() +
                "\nPhone Number: " + this.getPhoneNumber() +
                "\nCurrent Balance: " + storeCredit + " euro" +
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
