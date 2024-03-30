package fr.epita.assistants.classics;

public class Classics {
    /**
     * Computes the factorial of n.
     *
     * @param n the nth value to compute, negative values should return -1
     * @return the long value of n!
     */
    public static long factorial(int n) {
        if (n < 0)
            return -1;

        if (n == 0)
            return 1;

        return n * factorial(n - 1);
    }

    /**
     * Computes the nth value of the tribonacci suite.
     * f(0) = 0, f(1) = 1, f(2) = 1, f(n+3) = f(n) + f(n+1) + f(n+2)
     *
     * @param n the nth sequence to compute
     */
    public static long tribonacci(int n) {
        if (n < 0)
            return -1;
        long a = 0;
        long b = 1;
        long c = 1;
        long tmp;
        while (n != 0) {
            n--;
            tmp = a + b + c;
            a = b;
            b = c;
            c = tmp;
        }
        return a;
    }

    /**
     * Checks if a word is a palindrome.
     *
     * @param word the string to check
     * @return true if the word is a palindrome, false otherwise.
     */
    public static boolean isPalindrome(String word) {
        if (word == null)
            return false;

        word = word.replace(" " ,"").toLowerCase();

        int i = 0;
        int j = word.length() - 1; //String len
        while (i < j) {
            if ((word.charAt(i)) != word.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    /**
     * Sorts an array using an insertion sort.
     *
     * @param array the array to sort in place
     */
    public static void insertionSort(int[] array) {
        int i = 1;
        while (i < array.length) {
            int j = i;
            while (j > 0 && array[ j - 1] > array[j])
            {
                int tmp = array[j];
                array[j] = array[j - 1];
                array[j - 1] = tmp;
                j--;
            }
            i++;
        }
    }

    /**
     * Combines two strings by alternating their characters. Must use a StringBuilder.
     * If the strings do not have the same length, appends the remaining characters at the end of the result.
     * For instance, combine("abc", "def") returns "adbecf"
     */
    public static String combine(String a, String b) {
        var StringBuilder = new StringBuilder();

        int i = 0;
        int len = Math.min(a.length(), b.length());

        for (; i < len; i++) {
            StringBuilder.append(a.charAt(i));
            StringBuilder.append(b.charAt(i));
        }

        StringBuilder.append(a.substring(i));
        StringBuilder.append(b.substring(i));

        return StringBuilder.toString();
    }
}
