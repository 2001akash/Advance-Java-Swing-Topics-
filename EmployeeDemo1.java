// wap in which display the salary of all the employees in sorted order of their id.

import java.util.ArrayList;
import java.util.stream.Stream;

class Contact {
    String contact_name;
    long contact_number;
    public static ArrayList<Contact> phonebook = new ArrayList<>();

    Contact(String name, long con) {
        contact_name = name;
        contact_number = con;
        phonebook.add(this);
    }
}

class EmployeeDemo1 {
    static public void main(String[] rk) {
        ArrayList<Employee> al = new ArrayList<>();
        al.add(new Employee("Ravi", 10, 8437360391L, 1));
        al.add(new Employee("ABC", 10, 8523760391L, 3));
        al.add(new Employee("ZXV", 10, 85255660391L, 4));
        al.add(new Employee("DFG", 10, 844525285L, 2));
        al.add(new Employee("FGH", 10, 7852582525L, 5));

        Stream<Employee> empStream = al.stream().sorted((s1, s2) -> s1.id > s2.id ? 1 : -1);
        empStream.forEach(x -> System.out.println(
                "Employee id: " + x.id + " Name: " + x.name + ", Salary: " + x.salary + " , Mobile: " + x.mobile));
    }
}

class Employee {
    String name;
    long mobile;
    int salary;
    int id;

    Employee(String n, int s, long m, int id) {
        name = n;
        salary = s;
        mobile = m;
        this.id = id;
    }
}