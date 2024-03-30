package fr.epita.assistants.drawing;

public class Triangle extends Sharp {

    public Triangle(int length) {
        super(length);
    }

    @Override
    public void draw() {
        for (int y = 0; y < length; y++) {
            for (int x = 0; x <= y; x++) {
                if (y == length - 1 || x == 0 || x == y) {
                    System.out.print("# ");
                }
                else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }
}
