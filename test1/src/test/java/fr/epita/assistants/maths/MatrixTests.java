package fr.epita.assistants.maths;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixTests {

    @Test
    void correctMatrixTest() {
        int[][] value = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Matrix matrix = new Matrix(value);
        assertEquals(matrix.getMatrix(), value);
    }

    @Test
    void wrongMatrixTest() {
        int[][] value = {
                {1, 2},
                {4, 5, 6},
                {7}
        };
        Matrix matrix = new Matrix(value);
        assertNull(matrix);
    }

    @Test
    void changingMatrixTest() {
        int[][] value = {
                {7, 8, 9}
        };
        Matrix matrix = new Matrix(value);

        value[0][1] = 19;

        assertNotEquals(matrix.getMatrix(), value);
    }

    @Test
    void nullMatrixTest() {
        Matrix matrix = new Matrix(null);
        assertNull(matrix.getMatrix());
    }

    @Test
    void otherMatrixTest() {
        int[][] value = {
                {1, 2},
                {4, 5, 0},
                null
        };
        Matrix matrix = new Matrix(value);
        assertNull(matrix.getMatrix());
    }

    @Test
    void againAnotherMatrixTest() {
        int[][] value = {
                {1, 2},
                {4, 5, 0},
                {}
        };
        Matrix matrix = new Matrix(value);
        assertNull(matrix.getMatrix());
    }

    @Test
    void anotherMatrixTest() {
        int[][] value = {};
        Matrix matrix = new Matrix(value);
        assertNull(matrix.getMatrix());
    }

    @Test
    void correctEqualsTest() {
        int[][] value = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Matrix matrix = new Matrix(value);
        int[][] value2 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Matrix matrix2 = new Matrix(value2);
        assertTrue(matrix.equals(matrix2));
    }

    @Test
    void nullEqualsTest() {
        int[][] value = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Matrix matrix = new Matrix(value);
        Matrix matrix2 = new Matrix(null);
        assertFalse(matrix.equals(matrix2));
    }
    @Test
    void objEqualsTest() {
        int[][] value = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Matrix matrix = new Matrix(value);
        assertFalse(matrix.equals(value));
    }

    @Test
    void correctEqualsTest2() {
        int[][] value = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Matrix matrix = new Matrix(value);
        Matrix matrix2 = new Matrix(value);
        assertTrue(matrix.equals(matrix2));
    }

    @Test
    void wrongEqualsTest() {
        int[][] value = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Matrix matrix = new Matrix(value);
        int[][] value2 = {
                {1, 2, 3},
                {4, 5, 6},
        };
        Matrix matrix2 = new Matrix(value2);
        assertFalse(matrix.equals(matrix2));
    }

    @Test
    void correctMultiplyTest() {
        int[][] value = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Matrix matrix = new Matrix(value);
        Matrix res = matrix.multiply(matrix);

        int tmp[][] = {
                {30, 36, 42},
                {66, 81, 96},
                {102, 126, 150}
        };

        assertEquals(res.getMatrix(), tmp);
    }
    @Test
    void wrongMultiplyTest() {
        int[][] value = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Matrix matrix = new Matrix(value);
        int tmp[][] = {
                {30, 36, 42},
                {66, 81, 96},
        };
        Matrix matrix2 = new Matrix(tmp);
        Matrix res = matrix.multiply(matrix2);

        assertNull(res);
    }

    @Test
    void nullMultiplyTest() {
        int[][] value = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Matrix matrix = new Matrix(value);
        Matrix res = matrix.multiply(null);
        assertNull(res);
    }
}
