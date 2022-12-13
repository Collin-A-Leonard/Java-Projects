/*
 *Author: Collin Leonard
*Date: 4/9/21
* This application starts a 10 second timer and you have to try to beat the timer in guessing the correct random number
* between 1 and 10.
 */

import java.util.Timer;

public class GuessTheNumberApp {

    public static void main(String[] args) {
        // Output welcome info
        String welcomeInfo = "Guess the number!\nI'm thinking of a number from 1 to 10.\nTry to guess it in 10 seconds.\n";
        System.out.println(welcomeInfo);
        // Create random number, initialize guess and count int variables to 0
        int num = (int) (Math.random() * 10 + 1);
        int guess = 0;
        int count =  0;
        // Create TimerThread and start it.
        TimerThread timer = new TimerThread();
        timer.start();
        // While loop goes while guess is not equal to random number, gives feedback on guess.
        while (guess != num) {
            guess = Console.getInt("Your guess: ", 1, 10);
            ++count;
            if (guess > num) {
                System.out.println("Too high");
            } else if (guess < num) {
                System.out.println("Too low.");
            }
        }
        // I know this is overkill but I want to make sure the program correctly makes sure if the user finished in time or not
        // before outputting anything else.
        boolean finishedInTime = timer.isAlive();
        // Output info on guess attempts.
        System.out.println("You guessed it in " + count + " tries.");
        System.out.println();
        // If the user finished in time output one message, if not output another
        if (finishedInTime) {
            System.out.println("You did it!\nThe application will end when the timer finishes.");
        } else {
            System.out.println("Not fast enough!\nBye!");
        }
    }
    
}