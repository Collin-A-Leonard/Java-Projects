package PitcherStatistics;

/*
 *Authors: Collin Leonard, Josh Lyons, Gregory Spain, Brain Pucciani, Megan Ianni
 *Date: 4/26/21
 * Pitcher Stats Application
 */

import Classes.Pitcher;
import Classes.Game;
import FileIO.GameDAO;
import Console.Validator;

import java.awt.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import javax.swing.*;


public class PitcherStatisticsFrame extends JFrame {
    // instance variables for the frame
    private JLabel dateLabel, nameLabel, inningsLabel, hitsLabel, runsLabel,
            earnedRunsLabel, walksLabel, strikeoutLabel, ABLabel,
            battersFacedLabel, pitchesThrownLabel, radioLabel;
    private JPanel gamePanel, pitcherPanel, buttonPanel;
    private JButton viewGameStatsButton, viewSeasonStatsButton, addPitcherButton, exitButton;
    private JTextField dateTextField, nameTextField, inningsTextField, hitsTextField,
            runsTextField, earnedRunsTextField, walksTextField, strikeoutTextField, ABTextField,
            battersFacedTextField, pitchesThrownTextField;
    private JRadioButton winRadioButton, lossRadioButton, noDecisionRadioButton;
    private JScrollPane instructionsScrollPane, resultsScrollPane;
    private JTextArea instructionsTextArea, resultsTextArea;
    private static GameDAO games;
    // ArrayList<Game> gameList = games.getGamesList(); not needed

    // constructor. Get look and feel from the system then call initComponents
    // method to build frame
    public PitcherStatisticsFrame() {
        games = new GameDAO();
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException |
                IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }
        initComponents();
    }

    // This shows the format of the GUI
    private void initComponents() {

        // format frame
        setTitle("Pitcher Statistics");
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // set up textArea for instructions
        instructionsTextArea = new JTextArea(14, 40); // 14 rows, 40 columns
        instructionsTextArea.setEditable(false); // user can't edit
        instructionsTextArea.setLineWrap(true); // wrap lines
        instructionsTextArea.setWrapStyleWord(true); // wrap only between words
        instructionsTextArea.setText(
                "Welcome to the Pitcher Statistics App!\n\n" +
                        "1. To view the results for a game, enter " +
                        "the date in the Game Date field and " +
                        "click 'View Game Stats'.\n\n" +
                        "2. To view the season stats for a pitcher, enter their name "
                        + "and click 'View Season Stats'.\n\n" +
                        "3. To add a pitcher, enter the date of the game and the stats " +
                        "for the pitcher, then click 'Add Pitcher'.\n\n" +
                        "4. To close the program, click exit."
        );
        // create and format textArea for results. This will go in a scroll pane
        resultsTextArea = new JTextArea(14, 40);
        resultsTextArea.setEditable(false);
        resultsTextArea.setLineWrap(true); // wrap lines - helps if window is shrunk
        resultsTextArea.setWrapStyleWord(true); // wrap only between words

        // create labels for text fields.
        // I set this to be one operation instead of using the setText() method.
        dateLabel = new JLabel("Game date (YYYY-MM-DD):");
        nameLabel = new JLabel("Player's first and last name:");
        inningsLabel = new JLabel("Innings pitched:");
        hitsLabel = new JLabel("Hits given up:");
        runsLabel = new JLabel("Runs against:");
        earnedRunsLabel = new JLabel("Earned runs:");
        walksLabel = new JLabel("Walks: ");
        strikeoutLabel = new JLabel("Strikeouts:");
        ABLabel = new JLabel("AB: ");
        battersFacedLabel = new JLabel("Batters faced: ");
        pitchesThrownLabel = new JLabel("Pitches thrown: ");


        // radio buttons. We needed one for No Decision. I set that to be
        // so users are less likely to give wins or losses to multiple pitchers
        //in the same game. We may want to use validation as well for that.
        winRadioButton = new JRadioButton("Win");
        lossRadioButton = new JRadioButton("Loss");
        noDecisionRadioButton = new JRadioButton("No Decision");
        noDecisionRadioButton.setSelected(true);

        // set up button group to prevent multiple selections
        ButtonGroup winLoseGroup = new ButtonGroup();
        winLoseGroup.add(winRadioButton);
        winLoseGroup.add(lossRadioButton);
        winLoseGroup.add(noDecisionRadioButton);

        // dimension to control size of textFields
        Dimension dim = new Dimension(150, 20);

        // create text fields
        dateTextField = new JTextField();
        dateTextField.setPreferredSize(dim);
        dateTextField.setMinimumSize(dim);

        nameTextField = new JTextField();
        nameTextField.setPreferredSize(dim);
        nameTextField.setMinimumSize(dim);

        inningsTextField = new JTextField();
        inningsTextField.setPreferredSize(dim);
        inningsTextField.setMinimumSize(dim);

        hitsTextField = new JTextField();
        hitsTextField.setPreferredSize(dim);
        hitsTextField.setMinimumSize(dim);

        runsTextField = new JTextField();
        runsTextField.setPreferredSize(dim);
        runsTextField.setMinimumSize(dim);

        earnedRunsTextField = new JTextField();
        earnedRunsTextField.setPreferredSize(dim);
        earnedRunsTextField.setMinimumSize(dim);

        walksTextField = new JTextField();
        walksTextField.setPreferredSize(dim);
        walksTextField.setMinimumSize(dim);

        strikeoutTextField = new JTextField();
        strikeoutTextField.setPreferredSize(dim);
        strikeoutTextField.setMinimumSize(dim);

        ABTextField = new JTextField();
        ABTextField.setPreferredSize(dim);
        ABTextField.setMinimumSize(dim);

        battersFacedTextField = new JTextField();
        battersFacedTextField.setPreferredSize(dim);
        battersFacedTextField.setMinimumSize(dim);

        pitchesThrownTextField = new JTextField();
        pitchesThrownTextField.setPreferredSize(dim);
        pitchesThrownTextField.setMinimumSize(dim);



        // create buttons and set size and add event handlers
        viewGameStatsButton = new JButton("View Game Stats");
        viewGameStatsButton.setPreferredSize(dim);
        viewGameStatsButton.setMinimumSize(dim);
        viewGameStatsButton.addActionListener(e -> {
            viewGameStatsButtonClicked();
        });

        viewSeasonStatsButton = new JButton("View Season Stats");
        viewSeasonStatsButton.setPreferredSize(dim);
        viewSeasonStatsButton.setMinimumSize(dim);
        viewSeasonStatsButton.addActionListener(e -> {
            viewSeasonStatsButtonClicked();
        });


        addPitcherButton = new JButton("Add Pitcher to Game");
        addPitcherButton.setPreferredSize(dim);
        addPitcherButton.setMinimumSize(dim);
        addPitcherButton.addActionListener(e -> {
            addPitcherButtonClicked();
        });


        exitButton = new JButton("Exit");
        exitButton.setPreferredSize(dim);
        exitButton.setMinimumSize(dim);
        exitButton.addActionListener(e -> {
            exitButtonClicked();
        });

        // create Panes and Panels
        // create a scroll pane for the instructions, add border
        instructionsScrollPane = new JScrollPane(instructionsTextArea);
        instructionsScrollPane.setBorder(BorderFactory.createTitledBorder("Instructions"));

        // create a scroll pane for the results, add border
        resultsScrollPane = new JScrollPane(resultsTextArea);
        resultsScrollPane.setBorder(BorderFactory.createTitledBorder("Results"));

        // create and format a panel for the game date and add elements
        gamePanel = new JPanel();
        gamePanel.setLayout(new GridBagLayout());
        gamePanel.setBorder(BorderFactory.createTitledBorder("Game"));
        gamePanel.add(dateLabel, getConstraints(0, 0));
        gamePanel.add(dateTextField, getConstraints(1, 0));

        // create and format a panel for pitcher information and add elements
        pitcherPanel = new JPanel();
        pitcherPanel.setLayout(new GridBagLayout());
        pitcherPanel.setBorder(BorderFactory.createTitledBorder("Pitcher"));
        pitcherPanel.add(nameLabel, getConstraints(0, 0));
        pitcherPanel.add(nameTextField, getConstraints(1, 0));
        pitcherPanel.add(winRadioButton, getConstraints(0, 1));
        pitcherPanel.add(lossRadioButton, getConstraints(1, 1));
        pitcherPanel.add(noDecisionRadioButton, getConstraints(0, 2));
        pitcherPanel.add(inningsLabel, getConstraints(0, 3));
        pitcherPanel.add(inningsTextField, getConstraints(1, 3));
        pitcherPanel.add(hitsLabel, getConstraints(0, 4));
        pitcherPanel.add(hitsTextField, getConstraints(1, 4));
        pitcherPanel.add(runsLabel, getConstraints(0, 5));
        pitcherPanel.add(runsTextField, getConstraints(1, 5));
        pitcherPanel.add(earnedRunsLabel, getConstraints(0, 6));
        pitcherPanel.add(earnedRunsTextField, getConstraints(1, 6));
        pitcherPanel.add(walksLabel, getConstraints(0, 7));
        pitcherPanel.add(walksTextField, getConstraints(1, 7));
        pitcherPanel.add(strikeoutLabel, getConstraints(0, 8));
        pitcherPanel.add(strikeoutTextField, getConstraints(1, 8));
        pitcherPanel.add(ABLabel, getConstraints(0, 9));
        pitcherPanel.add(ABTextField, getConstraints(1, 9));
        pitcherPanel.add(battersFacedLabel, getConstraints(0, 10));
        pitcherPanel.add(battersFacedTextField, getConstraints(1, 10));
        pitcherPanel.add(pitchesThrownLabel, getConstraints(0, 11));
        pitcherPanel.add(pitchesThrownTextField, getConstraints(1, 11));

        // create and format a panel for Buttons and add elements
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.add(viewGameStatsButton, getConstraints(0, 0));
        buttonPanel.add(viewSeasonStatsButton, getConstraints(1, 0));
        buttonPanel.add(addPitcherButton, getConstraints(0, 1));
        buttonPanel.add(exitButton, getConstraints(1, 1));

        // add panels to the frame
        add(instructionsScrollPane, BorderLayout.WEST);
        add(gamePanel, BorderLayout.NORTH);
        add(pitcherPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(resultsScrollPane, BorderLayout.EAST);

        pack(); // sizes the frame to display
        setVisible(true); // finally display frame

    }

    // method for displaying game stats
    private void viewGameStatsButtonClicked() {
        Validator validator = new Validator();
        String errorMessage = "";
        errorMessage += validator.isString(dateTextField.getText(), "Game Date");
        errorMessage += validator.isLocalDate(dateTextField.getText());
        if (errorMessage.isEmpty()) {
            resultsTextArea.setText("Results for game date: " + // set starter text
                    dateTextField.getText() + "\n\n");
            String decisionString; // text to display whether win, lose, or ND
            LocalDate suppliedDate = LocalDate.parse(dateTextField.getText());
            // use nested enhanced for loops to create strings of results
            // and then append them to the resultsTextArea
            for (Game game : games.getGamesList()) {
                if (game.getGameDate().compareTo(suppliedDate) == 0) {
                    for (Pitcher p : game.getPitcherArrayList()) {
                        double ERA = ((p.getRuns() * 9) / p.getInningsPitched());
                        if (p.getWin()) {
                            decisionString = p.getName() + " won the Game!";
                        } else if (p.getLose()) {
                            decisionString = p.getName() + " lost the Game!";
                        } else {
                            decisionString = p.getName() + " did not get the decision";
                        }
                        String resultsString =
                                "Game Statistics for: " + p.getName() + "\n" +
                                        "-------------------------------------\n" +
                                        decisionString + "\n" +
                                        "Innings Pitched: " + p.getInningsPitched() + "\n" +
                                        "Hits given up: " + p.getHits() + "\n" +
                                        "Runs against: " + p.getRuns() + "\n" +
                                        "Earned runs: " + p.getEarnedRuns() + "\n" +
                                        "Walks: " + p.getWalks() + "\n" +
                                        "Strikeouts: " + p.getStrikeouts() + "\n" +
                                        "AB: " + p.getAb() + "\n" +
                                        "Batters faced: " + p.getBattersFaced() + "\n" +
                                        "Pitches Thrown: " + p.getPitchesThrown() + "\n" +
                                        "ERA: " + ERA + "\n\n";
                        resultsTextArea.append(resultsString);
                    }
                }
            }
        } else {
            resultsTextArea.setText(errorMessage);
        }
    }


    // Method to add a pitcher to the game you created.
    // we probably don't have time, but it wouldn't take a lot
    // of code to be able to add pitchers to a game that already exists
    private void addPitcherButtonClicked() {
        Validator validator = new Validator();
        String errorMessage = "";
        errorMessage += validator.isLocalDate(dateTextField.getText());
        errorMessage += validator.isString(nameTextField.getText(),
                "Player's first and last name");

        errorMessage += validator.isDouble(inningsTextField.getText(), "Innings Pitched");
        errorMessage += validator.isInt(hitsTextField.getText(), "Hits given up");
        errorMessage += validator.isInt(runsTextField.getText(), "Runs against");
        errorMessage += validator.isDouble(earnedRunsTextField.getText(), "Earned runs");
        errorMessage += validator.isInt(walksTextField.getText(), "Walks");
        errorMessage += validator.isInt(strikeoutTextField.getText(), "Strikeouts");
        errorMessage += validator.isInt(ABTextField.getText(), "AB");
        errorMessage += validator.isInt(battersFacedTextField.getText(), "Batters Faced");
        errorMessage += validator.isInt(pitchesThrownTextField.getText(), "Pitches thrown");

        if (errorMessage.isEmpty()) {
            boolean newGame = false;
            LocalDate gameDate = LocalDate.parse(dateTextField.getText());
            Game g = games.get(gameDate);
            if (g == null) {
                g = new Game(gameDate);
                newGame = true;
            }
            // Create a Pitcher
            Pitcher pp = new Pitcher(nameTextField.getText());

            // Determine which radio button is selected. Default is No Decision
            // so we don't need an else for that

            if (winRadioButton.isSelected()) {
                pp.setWin(true);
            } else if(lossRadioButton.isSelected()) {
                pp.setLose(true);
            }
            pp.setHits(Integer.parseInt(hitsTextField.getText()));
            pp.setRuns(Integer.parseInt(runsTextField.getText()));
            pp.setInningsPitched(Integer.parseInt(inningsTextField.getText()));
            pp.setEarnedRuns(Integer.parseInt(earnedRunsTextField.getText()));
            pp.setWalks(Integer.parseInt(walksTextField.getText()));
            pp.setStrikeouts(Integer.parseInt(strikeoutTextField.getText()));
            pp.setAb(Integer.parseInt(ABTextField.getText()));
            pp.setBattersFaced(Integer.parseInt(battersFacedTextField.getText()));
            pp.setPitchesThrown(Integer.parseInt(pitchesThrownTextField.getText()));

            g.addPitcher(pp); // add the pitcher to the game

            // reset fields to avoid duplication
            nameTextField.setText("");
            noDecisionRadioButton.setSelected(true);
            hitsTextField.setText("");
            runsTextField.setText("");
            inningsTextField.setText("");
            earnedRunsTextField.setText("");
            walksTextField.setText("");
            strikeoutTextField.setText("");
            ABTextField.setText("");
            battersFacedTextField.setText("");
            pitchesThrownTextField.setText("");
            if (newGame) {
                saveGame(g);
            }
            resultsTextArea.setText("Pitcher Added");
        } else {
            resultsTextArea.setText(errorMessage);
        }
    }

    // add the game to the DAO. The DAO auto-saves on add
    private void saveGame(Game g) {
        games.add(g);
    }

    // accepts Pitcher object and uses nested enhanced for loops to accumulate
    // data, then returns the Pitcher object.
    public static Pitcher accumulatePitcherStats(Pitcher vPitcher) {
        // for each game in the passed ArrayList
        for (Game game1 : games.getGamesList()) {
            // and for each Pitcher in the pitcherArrayList of that game
            for (Pitcher p1 : game1.getPitcherArrayList()) {
                // if the names match, accumulate.
                if (p1.getName().equalsIgnoreCase(vPitcher.getName())) {
                    if (p1.getWin()) {
                        vPitcher.setTotalWins(vPitcher.getTotalWins() + 1);
                    }
                    if (p1.getLose()) {
                        vPitcher.setTotalLosses(vPitcher.getTotalLosses() + 1);
                    }
                    vPitcher.setInningsPitched(vPitcher.getInningsPitched() +
                            p1.getInningsPitched());
                    vPitcher.setHits(vPitcher.getHits() + p1.getHits());
                    vPitcher.setRuns(vPitcher.getRuns() + p1.getRuns());
                    vPitcher.setEarnedRuns(
                            vPitcher.getEarnedRuns() + p1.getEarnedRuns());
                    vPitcher.setWalks(vPitcher.getWalks() + p1.getWalks());
                    vPitcher.setStrikeouts(vPitcher.getStrikeouts() +
                            p1.getStrikeouts());
                    vPitcher.setAb(vPitcher.getAb() + p1.getAb());
                    vPitcher.setBattersFaced(vPitcher.getBattersFaced() +
                            p1.getBattersFaced());
                    vPitcher.setPitchesThrown(vPitcher.getPitchesThrown() +
                            p1.getPitchesThrown());
                }
            }
        }
        return vPitcher;
    }

    // method to show the season stas for a pitcher. This calls the accumulate
    // method and then displays the results
    private void viewSeasonStatsButtonClicked() {
        Validator validator = new Validator();
        String errorMessage = "";
        errorMessage += validator.isString(nameTextField.getText(),
                "Player's first and last name");
        if (errorMessage.isEmpty()) {
            String pitcherName = nameTextField.getText();

            Pitcher p = new Pitcher(pitcherName);
            accumulatePitcherStats(p);
            if (p.getInningsPitched() == 0) {
                String resultsString = pitcherName + " was not found";
                resultsTextArea.setText(resultsString);
            } else {
                double ERA = Math.round((p.getRuns() * 9) / p.getInningsPitched());
                resultsTextArea.setText("Season Stats for: " + pitcherName + "\n" +
                        "-------------------------------------\n\n");
                String resultsString =
                        "Total Wins: " + p.getTotalWins() + "\n" +
                                "Total Losses: " + p.getTotalLosses() + "\n" +
                                "ERA: " + ERA + "\n" +
                                "Innings Pitched: " + p.getInningsPitched() + "\n" +
                                "Hits given up: " + p.getHits() + "\n" +
                                "Runs against: " + p.getRuns() + "\n" +
                                "Earned runs: " + p.getEarnedRuns() + "\n" +
                                "Walks: " + p.getWalks() + "\n" +
                                "Strikeouts: " + p.getStrikeouts() + "\n" +
                                "AB: " + p.getAb() + "\n" +
                                "Batters faced: " + p.getBattersFaced() + "\n" +
                                "Pitches Thrown: " + p.getPitchesThrown() + "\n\n";
                resultsTextArea.append(resultsString);
            }
        } else {
            resultsTextArea.setText(errorMessage);
        }
    }

    // Exit the program
    private void exitButtonClicked() {
        games.saveGames();
        System.exit(0);
    }

    // helper method for getting a GridBagConstraints object
    private GridBagConstraints getConstraints(int x, int y) {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(5, 5, 0, 5);
        c.gridx = x;
        c.gridy = y;
        return c;
    }

    // main method to create the Frame then set it to visible
    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {
            PitcherStatisticsFrame frame = new PitcherStatisticsFrame();
            frame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent evt) {
                    onExit();
                }

                private void onExit() {
                    games.saveGames();
                }
            });
            frame.setVisible(true);
        });
    }
}