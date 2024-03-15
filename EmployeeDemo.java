import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeeDemo {
    String name;
    int salary;
    int emp_id;
    String country;

    public EmployeeDemo(String n, int s, int emp, String c) {
        salary = s;
        name = n;
        emp_id = emp;
        country = c;
    }

    public static void main(String[] args) {
        ArrayList<EmployeeDemo> list = new ArrayList<>();
        list.add(new EmployeeDemo("Akash", 1000000, 1, "India"));
        list.add(new EmployeeDemo("Rahul", 200000, 2, "Australia"));
        list.add(new EmployeeDemo("Ravi", 200000, 3, "India"));
        list.add(new EmployeeDemo("Rakesh", 200000, 4, "Pakistan"));
        list.add(new EmployeeDemo("Amit", 200000, 5, "South africa"));

        // Filter employees from India
        Stream<EmployeeDemo> indiaEmployees = list.stream()
                .filter(employee -> employee.country.equals("India"));

        // Sort by emp_id
        List<EmployeeDemo> sortedIndiaEmployees = indiaEmployees
                .sorted((e1, e2) -> Integer.compare(e1.emp_id, e2.emp_id))
                .collect(Collectors.toList());

        // Display the sorted employees
        sortedIndiaEmployees.forEach(employee ->
                System.out.println("Name: " + employee.name + "\n Emp_id: " + employee.emp_id + "\n Country: " + employee.country+ "\n Salary: " +employee.salary));
    }
}
