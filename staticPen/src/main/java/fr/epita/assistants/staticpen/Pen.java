package fr.epita.assistants.staticpen;

public class Pen {

    private Color color;

    static int total_red_pen = 0;
    static int total_blue_pen = 0;

    public Pen(final Color color) {
        this.color = color;
        if (color == Color.RED)
            total_red_pen++;
        else
            total_blue_pen++;
    }

    public static int getRedPenCounter() {
        return total_red_pen;
    }

    public static int getPenCounter() {
        return total_blue_pen + total_red_pen;
    }

    public static int getBluePenCounter() {
        return total_blue_pen;
    }

    public void changeColor(final Color color) {
        this.color = color;
        if (color == Color.RED) {
            total_red_pen++;
            total_blue_pen--;
        }
        else {
            total_blue_pen++;
            total_red_pen--;
        }
    }

    public static void resetCounts() {
        total_red_pen = 0;
        total_blue_pen = 0;
    }

    public void print() {
        System.out.println("I'm a " + this.color.name() + " pen");
    }

    public enum Color {
        RED,
        BLUE
    }
}
