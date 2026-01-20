package srp;

public class ReportGenerator {

    // for getting total salary I am creating an object , final object
    private final SalaryCalculator salaryCalculator = new SalaryCalculator();

    public void generatingReports(Employee employee){
        IO.println("Name: " + employee.getName() + "\n" + "Total Salary: " + salaryCalculator.calculatingSalaries(employee));
    }
}
