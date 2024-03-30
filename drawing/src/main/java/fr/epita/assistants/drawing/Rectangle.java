package fr.epita.assistants.drawing;

public class Rectangle extends Sharp {

    private int width;

    public Rectangle(int width, int height) {
        super(height);
        this.width = width;
    }

    @Override
    public void draw() {
        for (int y = 0; y < length; y++) {
            for (int x = 0; x < width; x++) {
                if (y == 0 || y == length - 1 || x == 0 || x == width - 1) {
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
