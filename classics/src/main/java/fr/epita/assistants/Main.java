package fr.epita.assistants;

import fr.epita.assistants.classics.Classics;

public class Main {
    public static void main(String[] args) {
        int n = 5;
        int expected = 120;
        if (Classics.factorial(n) != expected)
            System.err.println("Error factorial("+ n +") " +
                    "-> Actual: " + Classics.factorial(n) + " Expected: " + expected);

        n = 5;
        expected = 7;
        if (Classics.tribonacci(n) != expected)
            System.err.println("Error tribonacci("+ n +") " +
                    "-> Actual: " + Classics.tribonacci(n) + " Expected: " + expected);

        n = 6;
        expected = 13;
        if (Classics.tribonacci(n) != expected)
            System.err.println("Error tribonacci("+ n +") " +
                    "-> Actual: " + Classics.tribonacci(n) + " Expected: " + expected);

        n = 0;
        expected = 0;
        if (Classics.tribonacci(n) != expected)
            System.err.println("Error tribonacci("+ n +") " +
                    "-> Actual: " + Classics.tribonacci(n) + " Expected: " + expected);
        n = 1;
        expected = 1;
        if (Classics.tribonacci(n) != expected)
            System.err.println("Error tribonacci("+ n +") " +
                    "-> Actual: " + Classics.tribonacci(n) + " Expected: " + expected);

        n = 2;
        expected = 1;
        if (Classics.tribonacci(n) != expected)
            System.err.println("Error tribonacci("+ n +") " +
                    "-> Actual: " + Classics.tribonacci(n) + " Expected: " + expected);

        String a = "abc";
        String b = "def";
        System.out.println(Classics.combine(a, b));

        if (!Classics.isPalindrome("ann   a")) {
            System.err.println("Error palindrome");
        }

        if (!Classics.isPalindrome("An  nN  a")) {
            System.err.println("Error palindrome");
        }
    }
}
