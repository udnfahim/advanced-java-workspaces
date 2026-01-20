package srp;

public class SalaryCalculator {
    public double calculatingSalaries(Employee employee){
        return employee.getBasicSalary()+employee.getBonusSalary();
    }
}
