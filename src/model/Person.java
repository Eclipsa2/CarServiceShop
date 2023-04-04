package model;

public abstract class Person {
    protected String name;
    protected String firstName;
    protected int phoneNumber;

    public Person() {
        this.name = "";
        this.firstName = "";
        this.phoneNumber = 0;
    }

    public Person(String name, String firstName, int phoneNumber) {
        this.name = name;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
