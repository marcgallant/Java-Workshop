package fr.epita.assistants.cinematheque;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class


public class Logger implements PropertyChangeListener {

    public PrintStream stream;

    public Logger(PrintStream stream) {
        this.stream = stream;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue().getClass() != Stock.Operation.class){
            throw new IllegalArgumentException();
        }

        String res = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy|HH:mm:ss"));

        switch ((Stock.Operation) evt.getNewValue()) {
            case Add -> stream.println("[" + res + "] An element has been added.");
            case Delete -> stream.println("[" + res + "] An element has been removed.");
            case Sort -> stream.println("[" + res + "] A sorting has occurred.");
        }
    }
}
