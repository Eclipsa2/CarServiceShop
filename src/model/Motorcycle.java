package model;

public class Motorcycle extends Vehicle{
    private String manufacturer;
    private String model;
    private int totalMass;
    private String transmissionType;

    public Motorcycle()
    {
        super();
    }

    public Motorcycle(String registrationNumber, String color, int fabricationYear, int horsePower, Client owner, Employee employee, Issue[] issues, String manufacturer, String model, int totalMass, String transmissionType) {
        super(registrationNumber, color, fabricationYear, horsePower, owner, employee, issues);
        this.manufacturer = manufacturer;
        this.model = model;
        this.totalMass = totalMass;
        this.transmissionType = transmissionType;
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

    public int getTotalMass() {
        return totalMass;
    }

    public void setTotalMass(int totalMass) {
        this.totalMass = totalMass;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }
}
