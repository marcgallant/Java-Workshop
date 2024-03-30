package fr.epita.assistants.war;

public class Vehicle extends Combatant {
    public Vehicle(String name, int defense) {
        this.name = name;
        this.defense = defense;
    }

    protected String name;

    protected int defense;

    public void printState() {
        System.out.println("I have " + this.defense + " defense points.");
    }
    public void attack(Soldier s) {
        s.health = 0;
    }
    public void attack(Vehicle v) {
        v.defense /= 2;
    }
    public void scream() {
        System.out.println("I'm " + this.name + "!");
    }
}
