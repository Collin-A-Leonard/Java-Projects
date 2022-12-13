package Classes;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Gregory Spain
 *
 * Purpose: Holds the fields and methods for a Game Object.
 * The game ID may wind up just being the LocalDate variable,
 * but for now I have it separate.
 */
public class Game {
    private LocalDate gameDate;
    private ArrayList<Pitcher> pitcherArrayList;

    // Constructor
    public Game(LocalDate gameDate) {
        this.gameDate = gameDate;
        pitcherArrayList = new ArrayList<>();
    }

    /**
     * @return the gameDate
     */
    public LocalDate getGameDate() {
        return gameDate;
    }

    /**
     * @param gameDate the gameDate to set
     */
    public void setGameDate(LocalDate gameDate) {
        this.gameDate = gameDate;
    }

    /**
     * @return the pitcherArrayList
     */
    public ArrayList<Pitcher> getPitcherArrayList() {
        return pitcherArrayList;
    }

    /**
     * @param pitcherArrayList the pitcherArrayList to set
     */
    public void setPitcherArrayList(ArrayList<Pitcher> pitcherArrayList) {
        this.pitcherArrayList = pitcherArrayList;
    }

    public void addPitcher(Pitcher p) {
        this.pitcherArrayList.add(p);
    }

}