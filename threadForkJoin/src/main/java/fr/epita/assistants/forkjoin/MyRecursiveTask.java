package fr.epita.assistants.forkjoin;

import java.lang.*;
import java.util.concurrent.RecursiveTask;


public class MyRecursiveTask extends RecursiveTask<Double> {
    final private double[][] matrix;
    final private int xLowerBound;
    final private int xUpperBound;
    final private int yLowerBound;
    final private int yUpperBound;

    public MyRecursiveTask(double[][] matrix, int xLowerBound, int xUpperBound, int yLowerBound, int yUpperBound) {
        this.matrix = matrix;
        this.xLowerBound = xLowerBound;
        this.xUpperBound = xUpperBound;
        this.yLowerBound = yLowerBound;
        this.yUpperBound = yUpperBound;
    }

    @Override
    protected Double compute() {
        if (matrix == null || xLowerBound >= xUpperBound || yLowerBound >= yUpperBound) {
            return 0.0;
        }

        if (xUpperBound - xLowerBound <= 5 && yUpperBound - yLowerBound <= 5) {
            Double total = 0.0;
            for (int i = yLowerBound; i < yUpperBound; i++) {
                for (int j = xLowerBound; j < xUpperBound; j++) {
                    total += matrix[i][j];
                }
            }
            return total / ((xUpperBound - xLowerBound) * (yUpperBound - yLowerBound));
        } else {
            int mid_x = (xUpperBound + xLowerBound) / 2;
            int mid_y = (yUpperBound + yLowerBound) / 2;

            MyRecursiveTask mat1 = new MyRecursiveTask(matrix, xLowerBound, mid_x, yLowerBound, mid_y);
            MyRecursiveTask mat2 = new MyRecursiveTask(matrix, mid_x, xUpperBound, yLowerBound, mid_y);
            MyRecursiveTask mat3 = new MyRecursiveTask(matrix, xLowerBound, mid_x, mid_y, yUpperBound);
            MyRecursiveTask mat4 = new MyRecursiveTask(matrix, mid_x, xUpperBound, mid_y, yUpperBound);

            mat1.fork();
            mat2.fork();
            mat3.fork();

            double value1 = mat1.join();
            double value2 = mat2.join();
            double value3 = mat3.join();
            double value4 = mat4.compute();

            return (value1 + value2 + value3 + value4) / 4;
        }
    }
}
