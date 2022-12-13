import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class MovieCollection {

    // Instance variable, list of movie objects.
    List<Movie> movies;

    public MovieCollection() {
        movies = new ArrayList<>();
    }

    // Add movie object to list
    public void add(Movie m){
        movies.add(m);
    }

    // Returns list of filtered movies, filtered by lambda function
    public List<Movie> filterMovies(Predicate<Movie> condition) {
        List<Movie> filteredMovies = new ArrayList<>();
        for (Movie m: movies) {
            if (condition.test(m)) {
                filteredMovies.add(m);
            }
        }
        return filteredMovies;
    }

    // Get lowest rating in movie collection
    public double getLowestRating() {
        return movies.stream().map(Movie::getRating).reduce((x, y) -> x.compareTo(y) <= 0  ? x : y).get();
    }

    // Get highest rating in movie collection
    public double getHighestRating() {
        return movies.stream().map(Movie::getRating).reduce(0.0, Math::max);
    }

    // Get average rating and return formatted version of it.
    public String getAverageRating() {
        double total = movies.stream().map(Movie::getRating).reduce(0.0, Double::sum);
        double average = total / getSize();
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        return nf.format(average);
    }

    // Get size of movies collection
    public int getSize() {
        return movies.size();
    }

}
