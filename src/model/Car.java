package model;

public class Car extends Vehicle
{
    private String manufacturer;
    private String model;

    public Car()
    {

    }

    public Car(String registrationNumber, String color, int fabricationYear, int horsePower, Client owner, Employee employee, Issue[] issues, String manufacturer, String model) {
        super(registrationNumber, color,fabricationYear, horsePower, owner, employee, issues);
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString()
    {
        return (super.toString() +
                "\nManufacturer: " + this.manufacturer +
                "\nModel: " + this.model);
    }
}
