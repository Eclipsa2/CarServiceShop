package model;

public abstract class Vehicle
{
    protected String registrationNumber;
    protected String color;
    protected Boolean isRepaired;
    protected int fabricationYear;
    protected int horsePower;
    protected Client owner;
    protected Employee employee;

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

    public void payRepairs()
    {
        if (owner.getStoreCredit() < total)
        {
            System.out.println("The client does not have enough store credit to pay for the repair! ");
        }
        else
        {
            owner.addStoreCredit(-total);
            System.out.println("The repairs for this vehicle have been paid!");
            total = 0;
        }
    }

    public void resoftVehicle(int addedHorsePower)
    {
        this.horsePower += addedHorsePower;
    }

    @Override
    public boolean equals(Object o)
    {
        return (this.getRegistrationNumber().equals(((Vehicle)o).getRegistrationNumber()));
    }
    @Override
    public String toString()
    {
        String deReturnat = "";
        if (this.isRepaired == true)
            deReturnat = "\nThe vehicle with the registration number: " + this.registrationNumber
            + " and the color " + this.color + " has been repaired." + "\nOwned by: "
            + this.owner.getName() + " " + this.owner.getFirstName() + "\nOwner Phone number: "
            + this.owner.getPhoneNumber() + "\nHorse Power: " + this.horsePower
            + "\nRepaired by: " + this.employee.getName()
            + " " + employee.getFirstName() + "\nRepair cost: " + this.total
            + "\nIssues: ";

        else
        {
            deReturnat = "\nThe vehicle with the registration number: " + this.registrationNumber
                    + " and the color " + this.color + " hasn't been repaired yet." + "\nOwned by: "
                    + this.owner.getName() + " " + this.owner.getFirstName() + "\nOwner Phone number: "
                    + this.owner.getPhoneNumber() + "\nHorse Power: " + this.horsePower +
                    "\nRepaired by: " + this.employee.getName()
                    + " " + employee.getFirstName() + "\nRepair cost: " + this.total
                    + "\nIssues: ";
        }

        for(Issue issue : issues)
        {
            deReturnat += "\n" + issue.getIssueName() + " - " + issue.getPrice();
        }

        return deReturnat;
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
