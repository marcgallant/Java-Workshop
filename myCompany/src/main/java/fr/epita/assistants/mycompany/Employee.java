package fr.epita.assistants.mycompany;

public abstract class Employee {
    private String lastName;
    private String firstName;

    public Employee(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String toString() {
        return firstName + " " + lastName;
    }

    public void sayMyName() {
        System.out.println("My name is " + toString() + ".");
    }

    public abstract void sayMyJob();
}
