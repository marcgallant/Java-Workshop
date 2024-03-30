package fr.epita.assistants.test2;

import fr.epita.assistants.server.MyServer;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static fr.epita.assistants.server.MyServer.launchServer;
import static fr.epita.assistants.server.MyServer.stopServer;
import static fr.epita.assistants.test2.Test2.*;
import static org.junit.jupiter.api.Assertions.*;
import static java.net.HttpURLConnection.HTTP_OK;

public class Test2Test {

    @Test
    public void simpleDivisionTest() {
        int a = 2;
        int b = 1;
        int actual = division(a,b);
        int expected = 2;

        assertEquals(expected, actual);
    }

    @Test
    public void zeroDivisionTest() {
        int a = 2;
        int b = 0;
        assertThrows(ArithmeticException.class, () -> {
            division(a, b);
        });
    }

    @Test
    @Timeout(1)
    public void serverTest() {
        assertDoesNotThrow(Test2::serverGetResponseCode);
    }

    @Test
    @Timeout(1)
    public void serverLauchedTest() throws IOException {
        launchServer();
        assertDoesNotThrow(Test2::serverGetResponseCode);
        stopServer();
    }

    @Test
    public void serverResponseTest() throws IOException {
        launchServer();
        assertDoesNotThrow(() -> {
            long actual = serverGetResponseCode();
            long expected = HTTP_OK;
            assertEquals(expected,actual);
        });
        stopServer();
    }

    @Test
    @Timeout(1)
    public void serverALotTest() throws IOException {
        launchServer();
        assertThrows(IOException.class, () -> {
            for (int i = 0; i < 1000; i++) {
                    serverGetResponseCode();
            }
        });
        stopServer();
    }

    @Test
    @Timeout(1)
    public void simpleTribonnacciTest() {
        int n = 5;

        long actual = tribonacci(n);
        long expected = 7;

        assertEquals(expected,actual);
    }

    @Test
    @Timeout(1)
    public void onetwothreeTribonnacciTest() {
        int n = 0;
        long actual = tribonacci(n);
        long expected = 0;

        assertEquals(expected,actual);

        n = 1;
        actual = tribonacci(n);
        expected = 1;

        assertEquals(expected,actual);

        n = 2;
        actual = tribonacci(n);
        expected = 1;

        assertEquals(expected,actual);
    }

    @Test
    @Timeout(1)
    public void negativeTribonnacciTest() {
        int n = -10;

        assertThrows(IllegalArgumentException.class, () -> {
            tribonacci(n);
        });
    }

    @Test
    @Timeout(1)
    public void bigTribonnacciTest() {
        int n = Integer.MAX_VALUE;

        long actual = tribonacci(n);
    }
}
