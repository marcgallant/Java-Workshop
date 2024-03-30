package fr.epita.assistants.cinematheque;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Counter implements PropertyChangeListener {

    public int count = 0;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue().getClass() != Stock.Operation.class){
            throw new IllegalArgumentException();
        }

        switch ((Stock.Operation) evt.getNewValue()) { //FIXME
            case Add -> count++;
            case Delete -> count--;
        }
    }

    public int getCount() {
        return count;
    }
}
