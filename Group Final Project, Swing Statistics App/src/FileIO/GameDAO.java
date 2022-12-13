package FileIO;

import Classes.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

public class GameDAO {

    ArrayList<Game> gamesList;
    Path gamePath;
    File gameFile;
    static String delim = ",";

    // Initializes output file and creates new instance of class.
    public GameDAO() {
        gamePath = Paths.get("games.txt");
        try {
            if (Files.notExists(gamePath)) {
                System.out.println("Data file not found.");
                System.out.println("Creating file: " +
                        gamePath.toAbsolutePath() + "\n");
                Files.createFile(gamePath);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        gameFile = gamePath.toFile();
        gamesList = this.getGamesList();
    }


    // Returns ArrayList of Game objects.
    public ArrayList<Game> getGamesList() {
        if (gamesList != null) {
            return gamesList;
        }

        gamesList = new ArrayList<>();

        if (Files.exists(gamePath)) { // prevent the FileNotFoundException
            try (BufferedReader in
                         = new BufferedReader(
                    new FileReader(gameFile))) {
                // read all Games stored in the file
                // into the array list
                int indexOfPitchers = 1;
                int numberOfPitcherFields = 12;
                String line = in.readLine();
                while (line != null) {
                    String[] columns = line.split(delim);
                    LocalDate gameDate = LocalDate.parse(columns[0]);
                    Game g = new Game(gameDate);
                    ArrayList<Pitcher> pitchers = new ArrayList<>();

                    for (int i = indexOfPitchers; i < columns.length; i += numberOfPitcherFields){
                        String pitcherName = columns[i];
                        Pitcher p = new Pitcher(pitcherName);
                        p.setWin(Boolean.parseBoolean(columns[i + 1]));
                        p.setLose(Boolean.parseBoolean(columns[i + 2]));
                        p.setInningsPitched(Double.parseDouble(columns[i + 3]));
                        p.setHits(Integer.parseInt(columns[i + 4]));
                        p.setRuns(Integer.parseInt(columns[i + 5]));
                        p.setEarnedRuns(Integer.parseInt(columns[i + 6]));
                        p.setWalks(Integer.parseInt(columns[i + 7]));
                        p.setStrikeouts(Integer.parseInt(columns[i + 8]));
                        p.setAb(Integer.parseInt(columns[i + 9]));
                        p.setBattersFaced(Integer.parseInt(columns[i + 10]));
                        p.setPitchesThrown(Integer.parseInt(columns[i + 11]));
                        pitchers.add(p);
                    }
                    g.setPitcherArrayList(pitchers);
                    gamesList.add(g);
                    line = in.readLine();
                }
            } catch (IOException e) {
                System.out.println(e);
                return null;
            }
        } else {
            System.out.println("Path is empty");
            return null;
        }
        return gamesList;
    }


    public boolean saveGames() {
        try (PrintWriter out = new PrintWriter(
                new BufferedWriter(
                        new FileWriter(gameFile)))) {

            // For each game create a line in the file for saving information on a game
            for (Game g : gamesList) {

                out.print(g.getGameDate().toString() + delim);

                // For each pitcher in each Game object, save their information on the same line
                ArrayList<Pitcher> pitchers = g.getPitcherArrayList();
                int lastPitcher = pitchers.size() - 1;
                for (int i = 0; i < pitchers.size(); i++) {
                    Pitcher p = pitchers.get(i);
                    out.print(p.getName() + delim);
                    out.print(p.getWin() + delim);
                    out.print(p.getLose() + delim);
                    out.print(p.getInningsPitched() + delim);
                    out.print(p.getHits() + delim);
                    out.print(p.getRuns() + delim);
                    out.print(p.getEarnedRuns() + delim);
                    out.print(p.getWalks() + delim);
                    out.print(p.getStrikeouts() + delim);
                    out.print(p.getAb() + delim);
                    out.print(p.getBattersFaced() + delim);
                    if (i == lastPitcher) {
                        // If it's the last pitcher use println to separate game objects in output file
                        out.println(p.getPitchesThrown());
                    } else {
                        // If not the last pitcher use standard print method of writer with delimiter to continue
                        out.print(p.getPitchesThrown() + delim);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }

        return true;
    }

    // Returns game object from list if the gameDate param equals a game object in the list's GameDate value
    // The returned Game object from this method can then be passed to the delete method to remove it from the list
    public Game get(LocalDate gameDate) {
        for(Game g : gamesList)
            if(g.getGameDate().compareTo(gameDate) == 0){
                return g;
            }
        return null;
    }

    // Add a game object to the list
    public void add(Game g) {
            gamesList.add(g);
    }

    // Removes game object from gamesList
    public boolean delete(Game g) {
        gamesList.remove(g);
        return this.saveGames();
    }

}