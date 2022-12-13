/*
*Author: Collin Leonard
*Date: 4/2/21
*This application creates a interactive console application that accepts info on movies and displays different data about them. 
 */

import java.net.CookieHandler;
import java.util.List;

public class MovieRatingsApp {

    // Create MovieCollection static variable to be used in program
    static MovieCollection movies = new MovieCollection();

    public static void main(String[] args) {
        // Welcome user
        System.out.println("Welcome to the Movie Ratings application!");
        showMenu();

        // Get Menu option for choosing which command to do
        int menuOption = Console.getInt("Choose a menu option: ", 1, 6);
        System.out.println();
        while (menuOption != 6) {
            switch (menuOption) {
                case 1:
                    enterMovies();
                    break;
                case 2:
                    viewTopRated();
                    break;
                case 3:
                    viewMostRecent();
                    break;
                case 4:
                    viewAllMovies();
                    break;
                case 5:
                    viewRatings();
                    break;
            }
            // Show menu again and user chooses option again.
            showMenu();
            menuOption = Console.getInt("Choose a menu option: ", 1, 6);
            System.out.println();
        }
        // Goodbye user!
        System.out.println("\nGoodbye!");
    }

    // static method to show the menu
    public static void showMenu() {
        String menu = "\n------------------------\n" +
                "What do you want to do?\n" +
                "------------------------\n" +
                "1 - Enter a movie\n" +
                "2 - View top rated movies\n" +
                "3 - View most recent movies\n" +
                "4 - View all movies\n" +
                "5 - View ratings\n" +
                "6 - Quit application\n";
        System.out.println(menu);
    }

    // Enter specified number of movies into movies MovieCollection variable
    private static void enterMovies() {
        int moviesToBeEntered = Console.getInt("How many movies do you want to enter? ");
        System.out.println();
        for (int i = 0; i < moviesToBeEntered; i++) {
            System.out.println("Movie #" + (i + 1) + "\n---------");
            String title = Console.getString("Enter Title: ");
            int year = Console.getInt("Enter year: ");
            double rating = Console.getDouble("Enter a rating between 1 and 5 (decimals OK): ", 1.0, 5.0);
            Movie m = new Movie(title, year, rating);
            movies.add(m);
            System.out.println();
        }
    }

    // View top rated movies with lambda function parameter passed ot filterMovies method of MovieCollection object
    private static void viewTopRated() {
        List<Movie> topRatedMovies = movies.filterMovies(m -> m.getRating() >= 4.0);
        System.out.println("Movies rated 4.0 or higher\n--------------------------");
        for (Movie m: topRatedMovies) {
            System.out.println(m.getTitle() + " (" + m.getYear() + ") Rating: " + m.getRating());
        }
    }

    // View most recent movies (less than 5 years old)
    private static void viewMostRecent() {
        List<Movie> mostRecentMovies = movies.filterMovies(m -> ((2021 - m.getYear()) < 5));
        System.out.println("Movies less than 5 years old\n----------------------------");
        for (Movie m: mostRecentMovies) {
            System.out.println(m.getTitle() + " (" + m.getYear() + ") Rating: " + m.getRating());
        }
    }

    // View all movies
    private static void viewAllMovies() {
        List<Movie> allMovies = movies.filterMovies(m -> true);
        System.out.println("All Movies\n--------------------------");
        for (Movie m: allMovies) {
            System.out.println(m.getTitle() + " (" + m.getYear() + ") Rating: " + m.getRating());
        }
    }

    // View ratings info
    private static void viewRatings() {
        System.out.println("Movie rating data");
        System.out.println("-----------------");
        System.out.println("Number of movies: " + movies.getSize());
        System.out.println("Lowest rating: " + movies.getLowestRating());
        System.out.println("Highest rating: " + movies.getHighestRating());
        System.out.println("Average rating: " + movies.getAverageRating());
    }

}
