/*
 *Author: Collin Leonard
 *Date: 2/23/21
 * This app creates two different methods for calculating the factorial of a given number. One recursive and one
 * iterative.
 */

public class RecursiveFactorialApp {

    static int iterativeFactorialCount = 0;
    static int recursiveFactorialCount = 0;

    public static void main(String[] args) {
        int number = 5;
        int iterativeResult = iterativeFactorial(number);
        int recursiveResult = recursiveFactorial(number);
        System.out.println("Number used: " + number);
        System.out.println("Iterative Factorial Result: " + iterativeResult + ", Number of Steps: " + iterativeFactorialCount);
        System.out.println("Recursive Factorial Result: " + recursiveResult + ", Number of Steps: " + recursiveFactorialCount);
    }

    public static int iterativeFactorial(int n) {
        int total = 1;
        for (int i = 1; i <= n; i++) {
            total *= i;
            ++iterativeFactorialCount;
        }
        return total;
    }

    public static int recursiveFactorial(int n) {
        if (n == 1) {
            ++recursiveFactorialCount;
            return 1;
        }
        else {
            ++recursiveFactorialCount;
            return n * recursiveFactorial(n - 1);
        }
    }
    
}
