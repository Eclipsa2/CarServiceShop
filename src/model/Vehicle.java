package model;

public abstract class Vehicle
{
    private String registrationNumber;
    private String color;
    private Boolean isRepaired;
    private int fabricationYear;
    private int horsePower;
    private Client owner;
    private Employee employee;

    private Issue[] issues = new Issue[101];

    private int total;

    public Vehicle() {
    }

    public Vehicle(String registrationNumber, String color, int fabricationYear, int horsePower, Client owner, Employee employee, Issue[] issues) {
        this.registrationNumber = registrationNumber;
        this.color = color;
        this.isRepaired = false;
        this.fabricationYear = fabricationYear;
        this.horsePower = horsePower;
        this.owner = owner;
        this.employee = employee;
        this.issues = issues;
        repairCosts();
    }
    public void repairCosts()
    {
        this.total = 0;

        for(int i = 0; i < issues.length; ++i)
        {
            this.total += issues[i].getPrice();
        }
    }

    @Override
    public String toString()
    {
        if (this.isRepaired == true)
            return ("The vehicle with the registration number: " + this.registrationNumber
            + " and the color " + this.color + " has been repaired and is owned by "
            + this.owner.getName() + " " + this.owner.getFirstName() + " with the phone number "
            + this.owner.getPhoneNumber());
        return ("The vehicle with the registration number: " + this.registrationNumber
                + " and the color " + this.color + " hasn't been repaired and is owned by "
                + this.owner.getName() + " " + this.owner.getFirstName() + " with the phone number "
                + this.owner.getPhoneNumber());
    }

    //region GETTERS AND SETTERS
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getRepaired() {
        return isRepaired;
    }

    public void setRepaired(Boolean repaired) {
        isRepaired = repaired;
    }

    public int getFabricationYear() {
        return fabricationYear;
    }

    public void setFabricationYear(int fabricationYear) {
        this.fabricationYear = fabricationYear;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Issue[] getIssues() {
        return issues;
    }

    public void setIssues(Issue[] issues) {
        this.issues = issues;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    //endregion
}
