package fr.epita.assistants.mycompany;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Company {

    public String name;
    public List<Employee> employees;

    public Map<Employee, Manager> employeeManagement;

    public Company(String name) {
        this.name = name;
        this.employees = new ArrayList<>();
        employeeManagement = new HashMap<>();
    }

    public int size() {
        return employees.size();
    }

    public void hire(Employee p) {
        employees.add(p);
    }

    public void fire(Employee p) {
        employees.remove(p);
    }

    public void printEmployees() {
        System.out.println("The company " + name + " employs:");
        for (Employee employee : employees) {
            System.out.println("- " + employee);
        }
    }

    public boolean addEmployeeManagement(Employee e, Manager m) {
        if (!employees.contains(e)) {
            System.out.println(e + " is not employed by " + name + ".");

            if (!employees.contains(m)) {
                System.out.println(m + " is not employed by " + name + ".");
            }
            return false;
        }

        if (!employees.contains(m)) {
            System.out.println(m + " is not employed by " + name + ".");
            return false;
        }

        Manager returned_value = employeeManagement.put(e, m);
        if (returned_value == null) {
            System.out.println(e + " now has a manager.");
        } else {
            System.out.println(e + " has a new manager.");
        }
        return true;
    }

    public void printManager(Employee e) {
        Manager m = employeeManagement.get(e);
        if (m == null) {
            System.out.println(e + " is managed by nobody.");
        } else {
            System.out.println(m + " manages " + e + ".");
        }
    }

    public void printEmployeesProject() {
        for (Employee e : employees) {
            if (e.getClass() == Engineer.class) {
                ((Engineer) e).listProjects();
            } else if (e.getClass() == Manager.class) {
                ((Manager) e).listProjects();
            }
        }
    }

}
