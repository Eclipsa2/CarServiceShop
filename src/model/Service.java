package model;

import java.util.*;

public class Service {
    private List<Car> currentlyInShopCars = new ArrayList<Car>();
    private List<Car> repairedCars = new ArrayList<Car>();
    private List<Motorcycle> currentlyInShopMotorcycles = new ArrayList<Motorcycle>();
    private List<Motorcycle> repairedMotorcycles = new ArrayList<Motorcycle>();
    private PriorityQueue<Employee> employees = new PriorityQueue<>();

    public Service(List<Car> currentlyInShopCars, List<Car> repairedCars, List<Motorcycle> currentlyInShopMotorcycles, List<Motorcycle> repairedMotorcycles, PriorityQueue<Employee> employees) {
        this.currentlyInShopCars = currentlyInShopCars;
        this.repairedCars = repairedCars;
        this.currentlyInShopMotorcycles = currentlyInShopMotorcycles;
        this.repairedMotorcycles = repairedMotorcycles;
        this.employees = employees;
    }

    //region GETTERS AND SETTERS
    public List<Car> getCurrentlyInShopCars() {
        return currentlyInShopCars;
    }

    public void setCurrentlyInShopCars(List<Car> currentlyInShopCars) {
        this.currentlyInShopCars = currentlyInShopCars;
    }

    public List<Car> getRepairedCars() {
        return repairedCars;
    }

    public void setRepairedCars(List<Car> repairedCars) {
        this.repairedCars = repairedCars;
    }

    public List<Motorcycle> getCurrentlyInShopMotorcycles() {
        return currentlyInShopMotorcycles;
    }

    public void setCurrentlyInShopMotorcycles(List<Motorcycle> currentlyInShopMotorcycles) {
        this.currentlyInShopMotorcycles = currentlyInShopMotorcycles;
    }

    public List<Motorcycle> getRepairedMotorcycles() {
        return repairedMotorcycles;
    }

    public void setRepairedMotorcycles(List<Motorcycle> repairedMotorcycles) {
        this.repairedMotorcycles = repairedMotorcycles;
    }

    public PriorityQueue<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(PriorityQueue<Employee> employees) {
        this.employees = employees;
    }
    //endregion
}
