package fr.epita.assistants.observer;

import java.util.*;

public class Swimmer implements Observable<Swimmer> {

    public Set<Observer<Swimmer>> observerSet;

    private final String name;

    private SwimmerStatus status;

    public Swimmer(String name) {
        this.observerSet = new HashSet<>();
        this.name = name;
        this.status = SwimmerStatus.OK;

        System.out.println(name + " goes into the sea.");
    }

    public String getName() {
        return name;
    }

    public SwimmerStatus getStatus() {
        return status;
    }

    public void setStatus(SwimmerStatus status) {
        this.status = status;
        if (status == SwimmerStatus.DROWNING) {
            System.out.println(name + ": I'm drowning, help!!");
        }
        else if (status == SwimmerStatus.WAVING) {
            System.out.println(name + ": Waves towards the shore.");
        }
        fire(this);
    }

    @Override
    public Set<Observer<Swimmer>> getObservers() {
        return observerSet;
    }

    @Override
    public void register(Observer<Swimmer>... observers) {
        observerSet.addAll(Arrays.asList(observers));
    }

    @Override
    public void unregister(Observer<Swimmer> observer) {
        observerSet.remove(observer);
    }

    @Override
    public void fire(Swimmer event) {
        observerSet.forEach(i -> i.onEvent(event));
    }
}
