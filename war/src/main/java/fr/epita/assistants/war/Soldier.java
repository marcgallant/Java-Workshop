package fr.epita.assistants.war;

public class Soldier extends Combatant {
    public Soldier() {
        this.health = 15;
        this.damage = 3;
        this.scream = "No pity for losers!";
    }

    protected int health;

    protected int damage;

    protected String scream;

    public void kill() {
        this.health = 0;
    }
    public void printState() {
        System.out.println("I have " + this.health + " health points.");
    }

    public void attack(Soldier s) {
        s.health -= this.damage;
    }

    public void attack(Vehicle v) {
        System.out.println("I can't fight this.");
    }

    public void scream() {
        System.out.println(this.scream);
    }
}
