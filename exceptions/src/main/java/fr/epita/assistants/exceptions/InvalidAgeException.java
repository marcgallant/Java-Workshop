package fr.epita.assistants.exceptions;

public class InvalidAgeException extends Exception {
    public InvalidAgeException(int message) {
        super("InvalidAgeException: " + message);
    }
}
