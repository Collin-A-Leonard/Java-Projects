/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gregory Spain
 */

package Classes;

public class Pitcher {
    // instance variables
    private String name;
    private boolean win;
    private boolean lose;
    private int totalWins;
    private int totalLosses;
    private double inningsPitched;
    private int hits;
    private int runs;
    private int earnedRuns;
    private int walks;
    private int strikeouts;
    private int ab;
    private int battersFaced;
    private int pitchesThrown;

    // 14 Fields in total

    // Constructor
    public Pitcher(String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the inningsPitched
     */
    public double getInningsPitched() {
        return inningsPitched;
    }

    /**
     * @param inningsPitched the inningsPitched to set
     * Must be a valid inning length. We may want to move the validation to
     * the input but for now it is here.
     */
    public void setInningsPitched(double inningsPitched) {
        int validator = (int) Math.round(inningsPitched);
        if (inningsPitched - validator <= 0.2) {
            this.inningsPitched = inningsPitched;
        } else {
            System.out.println("Invalid number of innings. enter partital "
                    + "innings as .1 or .2");
        }

    }

    /**
     * @return the win
     */
    public boolean getWin() {
        return win;
    }

    /**
     * @param win the win to set
     */
    public void setWin(boolean win) {
        this.win = win;

    }

    /**
     * @return the lose
     */
    public boolean getLose() {
        return lose;
    }

    /**
     * @param lose the lose to set
     */
    public void setLose(boolean lose) {
        this.lose = lose;
    }

    /**
     * @return the totalWins
     */
    public int getTotalWins() {
        return totalWins;
    }

    /**
     * @param totalWins the totalWins to set
     */
    public void setTotalWins(int totalWins) {
        this.totalWins = totalWins;
    }

    /**
     * @return the totalLosses
     */
    public int getTotalLosses() {
        return totalLosses;
    }

    /**
     * @param totalLosses the totalLosses to set
     */
    public void setTotalLosses(int totalLosses) {
        this.totalLosses = totalLosses;
    }
    /**
     * @return the hits
     */
    public int getHits() {
        return hits;
    }

    /**
     * @param hits the hits to set
     */
    public void setHits(int hits) {
        this.hits = hits;
    }

    /**
     * @return the runs
     */
    public int getRuns() {
        return runs;
    }

    /**
     * @param runs the runs to set
     */
    public void setRuns(int runs) {
        this.runs = runs;
    }

    /**
     * @return the earnedRuns
     */
    public int getEarnedRuns() {
        return earnedRuns;
    }

    /**
     * @param earnedRuns the earnedRuns to set
     */
    public void setEarnedRuns(int earnedRuns) {
        this.earnedRuns = earnedRuns;
    }

    /**
     * @return the walks
     */
    public int getWalks() {
        return walks;
    }

    /**
     * @param walks the walks to set
     */
    public void setWalks(int walks) {
        this.walks = walks;
    }

    /**
     * @return the strikeOuts
     */
    public int getStrikeouts() {
        return strikeouts;
    }

    /**
     * @param strikeouts the strikeOuts to set
     */
    public void setStrikeouts(int strikeouts) {
        this.strikeouts = strikeouts;
    }

    /**
     * @return the ab
     */
    public int getAb() {
        return ab;
    }

    /**
     * @param ab the ab to set
     */
    public void setAb(int ab) {
        this.ab = ab;
    }

    /**
     * @return the bf
     */
    public int getBattersFaced() {
        return battersFaced;
    }

    /**
     * @param battersFaced the bf to set
     */
    public void setBattersFaced(int battersFaced) {
        this.battersFaced = battersFaced;
    }

    /**
     * @return the pitchesThrown
     */
    public int getPitchesThrown() {
        return pitchesThrown;
    }

    /**
     * @param pitchesThrown the pitchesThrown to set
     */
    public void setPitchesThrown(int pitchesThrown) {
        this.pitchesThrown = pitchesThrown;
    }


    public void printStats() {
        System.out.println("Game Statistics for: " + this.getName());
        System.out.println("---------------------------------------");
        if (this.getWin()) {
            System.out.println(this.getName() + " won the Game!");
        } else if (this.getLose()) {
            System.out.println(this.getName() + " lost the Game!");
        } else {
            System.out.println(this.getName() + " did not get the decision");
        }
        System.out.println("Innings Pitched: " + this.getInningsPitched());
        System.out.println("Hits given up: " + this.getHits());
        System.out.println("Runs against: " + this.getRuns());
        System.out.println("Earned runs: " + this.getEarnedRuns());
        System.out.println("Walks: " + this.getWalks());
        System.out.println("Strikeouts: " + this.getStrikeouts());
        System.out.println("AB: " + this.getAb());
        System.out.println("Batters faced: " + this.getBattersFaced());
        System.out.println("Pitches Thrown: " + this.getPitchesThrown());
        System.out.println();
    }

    public void printAccumulatedStats() {
        System.out.println("Statistics for: " + this.getName());
        System.out.println("---------------------------------------");

        System.out.println("Wins: " + this.getTotalWins());
        System.out.println("Losses: " + this.getTotalLosses());
        System.out.println("Innings Pitched: " + this.getInningsPitched());
        System.out.println("Hits given up: " + this.getHits());
        System.out.println("Runs against: " + this.getRuns());
        System.out.println("Earned runs: " + this.getEarnedRuns());
        System.out.println("Walks: " + this.getWalks());
        System.out.println("Strikeouts: " + this.getStrikeouts());
        System.out.println("AB: " + this.getAb());
        System.out.println("Batters faced: " + this.getBattersFaced());
        System.out.println("Pitches Thrown: " + this.getPitchesThrown());
        System.out.println();
    }



}