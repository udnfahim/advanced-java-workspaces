package srp;

public class Employee {
    private String name;
    private double basicSalary;
    private double bonusSalary;

    public Employee() {
    }

    public Employee(String name, double basicSalary, double bonusSalary) {
        this.name = name;
        this.basicSalary = basicSalary;
        this.bonusSalary = bonusSalary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public double getBonusSalary() {
        return bonusSalary;
    }

    public void setBonusSalary(double bonusSalary) {
        this.bonusSalary = bonusSalary;
    }
}
