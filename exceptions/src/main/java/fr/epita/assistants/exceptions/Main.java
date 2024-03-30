package fr.epita.assistants.exceptions;

public class Main {
    public static void main(String[] args) throws InvalidAgeException, InvalidNameException, InvalidMajorException {
        Student paul = new Student("Jean-Michel", 19, "iMAgE");
        System.out.println(paul);
    }
}