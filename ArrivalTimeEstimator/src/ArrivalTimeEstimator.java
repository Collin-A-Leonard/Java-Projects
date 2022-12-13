/*
*Author: Collin Leonard
*Date: 2/5/21
* The purpose of this program is to calculate the travel time of a trip and display that information to the console.
 */

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Scanner;

public class ArrivalTimeEstimator {

   static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Welcome user, set choice = "y", create NumberFormat and DateTimeFormatter so they won't
        // have to be created every loop. Start while loop while choice.equalsIgnoreCase("y")
        System.out.println("Arrival Time Estimator");
        String choice = "y";
        NumberFormat numFormat = NumberFormat.getInstance();
        numFormat.setRoundingMode(RoundingMode.HALF_UP);
        numFormat.setMaximumFractionDigits(0);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        while (choice.equalsIgnoreCase("y")) {
            System.out.println();
            // Collect Strings with departure date and time, assumed formatted correctly.
            System.out.print("Departure Date (YYYY-MM-DD): ");
            String departureDate = sc.nextLine();
            System.out.print("Departure Time (HH:MM): ");
            String departureTime = sc.nextLine();
            // Create LocalDateTime object by parsing string with user input.
            LocalDateTime departure = LocalDateTime.parse(departureDate + "T" + departureTime);
            // Collect integer information on num of miles and mph with getInt method.
            int miles = getInt("Number of miles: ");
            int mph = getInt("Miles per hour: ");
            // Perform calculations on the integer input to calculate travel time.
            double amntTime = (double) miles/mph;
            int hours = (int) amntTime;
            double unformattedMinutes = ((amntTime - hours) * 60); // 60 = num minutes in hour
            // Format minutes for display
            String minutes = numFormat.format(unformattedMinutes);
            // Cast minutes to type long to be used in DateTimeFormatter
            long departureMinutes = Long.parseLong(minutes);
            departure = departure.plusHours(hours).plusMinutes(departureMinutes);
            // Format date and time for output using DateTimeFormatter objects created above.
            String arrivalDate = dateFormatter.format(departure);
            String arrivalTime = timeFormatter.format(departure);
            // Format output and print to console.
            String output = "Hours: " + hours + "\nMinutes: " + minutes +
                    "\nEstimated date of arrival: " + arrivalDate + "\nEstimated time of arrival: " +
                    arrivalTime;
            System.out.println();
            System.out.println(output);
            System.out.println();
            // Ask user if they want to continue.
            choice = getString("Contnue? (y/n): ", "y", "n");
        }
        System.out.println("\nBye!");
    }

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
                if(isInt(input) || input.equalsIgnoreCase("exit")){
                    isValid = true;
                } else {
                    System.out.println("Error! Enter either \"exit\" or a valid double.");
                }
            }
        }
        return Integer.parseInt(input);
    }

    // Boolean method to check if entered string is a integer value. Returns true if it is.
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

    // Method to get and validate 1 of 2 string input choices.
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
}
