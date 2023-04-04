package model;

public class Employee extends Person implements Comparable<Employee>
{
    private int salary;

    public Employee() {
        super();
        this.salary = 0;
    }

    @Override
    public int compareTo(Employee employee)
    {
        return (this.salary - employee.getSalary());
//        if(this.getSalary() > employee.getSalary())
//            return 1;
//        else if(this.getSalary() < employee.getSalary())
//            return -1;
//        return 0;
    }

    @Override
    public boolean equals(Object o)
    {
        return (this.getFirstName().equals(((Employee)o).getFirstName())
                &&
                this.getName().equals(((Employee)o).getName()));
    }

    @Override
    public String toString()
    {
        return (
                "First Name: " + this.getFirstName() +
                "\nLast Name: " + this.getName() +
                "\nPhone Number: " + this.getPhoneNumber() +
                "\nSalary: " + this.salary
        );
    }
    public Employee(String name, String firstName, int phoneNumber, int salary) {
        super(name, firstName, phoneNumber);
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

}
