package model;

public class Client extends Person
{
    private boolean paidRepairs = false;

    public Client() {
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
}
