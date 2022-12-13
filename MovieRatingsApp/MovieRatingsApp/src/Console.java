import java.util.Scanner;

public class Console {

    // Instance static variable of Scanner object.
    private static final Scanner sc = new Scanner(System.in);


    // Methods to validate all user input from getting integers with or without min and max values,
    // to getting doubles with or without max values, or getting strings, with or without the stipulation
    // of choosing between two specific strings

    // Method to get and verify int input.
    public static int getInt (String prompt) {
        boolean isValid = false;
        String input = "";
        while (!isValid) {
            System.out.print(prompt);
            input = sc.nextLine();
            if (input.equals("")) {
                System.out.println("Error! This entry is required. Try again.");
                System.out.println();
            }
            else {
                if(isInt(input)) { // || input.equalsIgnoreCase("exit")){  Enter exit keyword here
                    isValid = true;
                } else {
                    System.out.println("Error! Enter a valid integer.");
                }
            }
        }
        return Integer.parseInt(input);
    }


    public static int getInt (String prompt, int min, int max) {
        boolean isValid = false;
        String input = "";
        while (!isValid) {
            System.out.print(prompt);
            input = sc.nextLine();
            if (input.equals("")) {
                System.out.println("Error! This entry is required. Try again.");
                System.out.println();
            }
            else {
                if(isInt(input)) {// || input.equalsIgnoreCase("exit")){    Enter exit keyword here
                    int intInput = Integer.parseInt(input);
                    if (intInput < min || intInput > max) {
                        System.out.println("Error, enter a number between " + min + " and " + max + ".");
                    } else {
                        isValid = true;
                    }
                } else {
                    System.out.println("Error! Enter a valid integer.");
                }
            }
        }
        return Integer.parseInt(input);
    }

    public static boolean isInt (String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int num = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static double getDouble(String prompt) {
        boolean isValid = false;
        String input = "";
        while (!isValid) {
            System.out.print(prompt);
            input = sc.nextLine();
            if (input.equals("")) {
                System.out.println("Error! This entry is required. Try again.");
                System.out.println();
            }
            else {
                if(isDouble(input)) { // || input.equalsIgnoreCase("exit")){  Enter exit keyword here
                    isValid = true;
                } else {
                    System.out.println("Error! Enter a valid double.");
                }
            }
        }
        return Double.parseDouble(input);
    }

    public static double getDouble(String prompt, double min, double max) {
        boolean isValid = false;
        String input = "";
        while (!isValid) {
            System.out.print(prompt);
            input = sc.nextLine();
            if (input.equals("")) {
                System.out.println("Error! This entry is required. Try again.");
                System.out.println();
            }
            else {
                if(isDouble(input)) {// || input.equalsIgnoreCase("exit")){    Enter exit keyword here
                    double doubInput = Double.parseDouble(input);
                    if (doubInput < min || doubInput > max) {
                        System.out.println("Error, enter a number between " + min + " and " + max + ".");
                    } else {
                        isValid = true;
                    }
                } else {
                    System.out.println("Error! Enter a valid double.");
                }
            }
        }
        return Double.parseDouble(input);
    }

    public static boolean isDouble(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double num = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static String getString(String prompt) {
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

    public static String getString(String prompt, String s1, String s2) {
        boolean isValid = false;
        String choice = "";
        while (!isValid) {
            System.out.print(prompt);
            choice = sc.nextLine();
            if (choice.equals("")) {
                System.out.println("Error! This entry is required. Try again.");
            }
            else {
                isValid = true;
            }
            if ((isValid) && ((!choice.equalsIgnoreCase(s1)) && (!choice.equalsIgnoreCase(s2)))) {
                System.out.println("Error! Entry must be '" + s1 + "' or '" + s2 + "'. Try again.");
                isValid = false;
            }
        }
        return choice;
    }

    public static String getString(String prompt, String s1, String s2, String s3) {
        boolean isValid = false;
        String choice = "";
        while (!isValid) {
            System.out.print(prompt);
            choice = sc.nextLine();
            if (choice.equals("")) {
                System.out.println("Error! This entry is required. Try again.");
            }
            else {
                isValid = true;
            }
            if ((isValid) && ((!choice.equalsIgnoreCase(s1)) && (!choice.equalsIgnoreCase(s2)) && (!choice.equalsIgnoreCase(s3)))) {
                System.out.println("Error! Entry must be '" + s1 + "' or '" + s2 + "' or '" + s3 + "'. Try again.");
                isValid = false;
            }
        }
        return choice;
    }

    // Methods to output Strings or blank lines.
    public static void print(String s) {
        System.out.print(s);
    }

    public static void println() {
        System.out.println();
    }

    public static void println(String s) {
        System.out.println(s);
    }
}

