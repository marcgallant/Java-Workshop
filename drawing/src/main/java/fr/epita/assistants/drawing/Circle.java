package fr.epita.assistants.drawing;

public class Circle extends Entity {
    private int radius;

    public Circle(int radius) {
        super();
        this.radius = radius;
    }

    @Override
    public void draw() {
        for (int y = -radius; y <= radius; y++) {
            for (int x = -radius; x <= radius; x++) {
                int dist = Math.abs(radius * radius - (x * x + y * y));

                if (dist < radius) {
                    System.out.print('#');
                } else {
                    System.out.print(' ');
                }

                if (x < radius) {
                    System.out.print(' ');
                }
            }
            if (y < radius) {
                System.out.println();
            }
        }
        System.out.println();
    }
}
