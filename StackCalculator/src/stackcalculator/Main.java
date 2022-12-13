/*
*Author: Collin Leonard
*Date: 1/28/21
* Program that creates a "Stack" and performs mathematical operations on it. The Stack class is separate. The program
* continues for as long as the user desires and ends when the user inputs "quit".
*/

package stackcalculator;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        // Welcome user and explain commands
        System.out.println("Welcome to the Stack Calculator.");
        System.out.println("\nCommands: push n, add, sub, mult, div, clear, or quit.\n");
        // Create new StackCalculaor object
        StackCalculator stack = new StackCalculator();
        // Set choice equal to input and start while loop.
        String choice = getString("stack> ");
        while (!choice.equalsIgnoreCase("quit")){
            // Create String array with answer to parse double when people use push command.
            String[] answer = choice.split(" ");
            boolean isValid = true;
            // If else structure to do command according to input.
            if(answer[0].equalsIgnoreCase("push")) {
                if (isDouble(answer[1])) {
                    double num = Double.parseDouble(answer[1]);
                    stack.push(num);
                } else {
                    System.out.println("Error, invalid push attempt. Try again.");
                    isValid = false;
                }
            } else {
                if(answer[0].equalsIgnoreCase("add")) {
                    stack.add();
                } else if (answer[0].equalsIgnoreCase("sub")){
                    stack.subtract();
                } else if (answer[0].equalsIgnoreCase("mult")){
                    stack.multiply();
                } else if (answer[0].equalsIgnoreCase("div")){
                    stack.divide();
                } else if (answer[0].equalsIgnoreCase("clear")) {
                    stack.clear();
                } else {
                    System.out.println("Invalid entry, try again.");
                    isValid = false;
                }
            }
            // Print empty stack if empty and valid input.
            if (stack.isStackEmpty() && isValid) {
                System.out.println("empty");
            }
            // call renderStack to print stack if valid input.
            if (isValid) {
                renderStack(stack);
            }
            // get user choice
            System.out.println();
            choice = getString("stack> ");
        }
        System.out.println("\nThanks for using the Stack Calculator.");
    }

    // Function to get String input from user.
    public static String getString(String prompt) {
        Scanner sc = new Scanner(System.in);
        boolean isValid = false;
        String input = "";
        while (!isValid) {
            System.out.print(prompt);
            input = sc.nextLine();
            if (input.equals("")) {
                System.out.println("Error! This entry is required. Try again.");
            } else {
                isValid = true;
            }
        }
        return input;
    }

    // Function to print out stack to console.
    public static void renderStack(StackCalculator stack) {
        if (stack.isStackEmpty()) {
            return;
        } else {
            double[] stackArray = stack.getValues();
            for (int i = 0; i < stackArray.length; i++) {
                System.out.println(stackArray[i]);
            }
        }
    }

    // Function to check if String is valid double. Used for push function.
    public static boolean isDouble(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
}
