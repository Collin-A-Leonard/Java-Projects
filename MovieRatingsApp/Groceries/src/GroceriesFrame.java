import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
*Author: Collin Leonard
*Date: 3/12/21
* This program creates a grocery list that is saved to a file. If no item is entered to be added when
* the add button is clicked an error message is displayed.
*/
public class GroceriesFrame extends JFrame {

    // Class instance variables to be used throughout.
    private JTextField item;
    private JButton addButton;
    private JButton clearButton;
    private JButton removeButton;
    private JButton moveItemUpButton;
    private JButton moveItemDownButton;
    private JList groceriesList;
    private DefaultListModel<String> groceries;
    private File file;
    private Path filePath;

    // Instantiating method to create txt file for use in program and call initComponents();
    public GroceriesFrame() {
        filePath = Paths.get("groceries.txt");
        try {
            if (Files.notExists(filePath)) {
                System.out.println("Data file not found.");
                System.out.println("Creating file: " +
                        filePath.toAbsolutePath() + "\n");
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        file = filePath.toFile();
        groceries = this.getGroceries();
        initComponents();
    }

    // initComponents method creates layout for GUI and sets event listeners for obejects.
    private void initComponents() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
        }

        setTitle("Groceries");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);

        JLabel itemLabel = new JLabel("Item:");
        item = new JTextField();
        item.setPreferredSize(new Dimension(170, 25));
        addButton = new JButton("Add");
        addButton.addActionListener( e -> {
            addButtonClicked();
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.add(itemLabel, getConstraints(0, 0));
        panel.add(item, getConstraints(1, 0));
        panel.add(addButton, getConstraints(2, 0));

        JLabel listLabel = new JLabel("List:");
        groceriesList = new JList();
        groceriesList.setModel(groceries);
        JScrollPane groceryScroll = new JScrollPane(groceriesList);
        groceryScroll.setPreferredSize(new Dimension(160, 170));

        panel.add(listLabel, getConstraints(0, 1));
        panel.add(groceryScroll, getConstraints(1, 1));

        moveItemUpButton = new JButton("Move Up");
        moveItemDownButton = new JButton("Move Down");
        moveItemUpButton.addActionListener(e -> { moveItemUpButtonPressed(); });
        moveItemDownButton.addActionListener(e -> { moveItemDownButtonPressed(); });

        JPanel movePanel = new JPanel();
        movePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        movePanel.add(moveItemUpButton);
        movePanel.add(moveItemDownButton);

        clearButton = new JButton("Clear");
        removeButton = new JButton("Remove");
        clearButton.addActionListener(e -> { clearButtonClicked(); });
        removeButton.addActionListener(e -> { removeButtonClicked(); });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(clearButton);
        bottomPanel.add(removeButton);
        movePanel.add(bottomPanel);

        add(panel, BorderLayout.CENTER);
        add(movePanel, BorderLayout.SOUTH);
        pack();
    }

    // Method to get list of groceries from txt file.
    private DefaultListModel<String> getGroceries() {
        groceries = new DefaultListModel<>();
        if (Files.exists(filePath)) { // prevent the FileNotFoundException
            try (BufferedReader in
                         = new BufferedReader(
                    new FileReader(file))) {
                String line = in.readLine();
                while (line != null) {
                    groceries.addElement(line);
                    line = in.readLine();
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error, no file found.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return groceries;
    }

    // Method to save list of groceries to text file.
    private void saveGroceries() {
        try (PrintWriter out = new PrintWriter(
                new BufferedWriter(
                        new FileWriter(file)))) {
            for (int i = 0; i < groceries.size(); i++) {
                out.println(groceries.get(i));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to get constraints for GridBagLayout objects.
    private GridBagConstraints getConstraints(int x, int y) {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = x;
        c.gridy = y;
        return c;
    }


    // Button event listener to add item to list and save to file.
    private void addButtonClicked() {
        if (item.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No items added.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            groceries.addElement(item.getText());
            item.setText("");
            saveGroceries();
        }
    }

    // Method to move single item on list up.
    private void moveItemUpButtonPressed() {
        int[] index = groceriesList.getSelectedIndices();
        if(index.length > 1 ) {
            JOptionPane.showMessageDialog(this, "You can only move one item at a time.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (index[0] == 0) {
            JOptionPane.showMessageDialog(this, "This item can not move up any more.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String temp = groceries.get(index[0] - 1);
            String indexValue = groceries.get(index[0]);
            groceries.set(index[0] - 1, indexValue);
            groceries.set(index[0], temp);
            groceriesList.setSelectedIndex(index[0] - 1);
            saveGroceries();
        }
    }

    // Method ot move single item on list down.
    private void moveItemDownButtonPressed() {
        int[] index = groceriesList.getSelectedIndices();
        if(index.length > 1 ) {
            JOptionPane.showMessageDialog(this, "You can only move one item at a time.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (index[0] == groceries.size() - 1) {
            JOptionPane.showMessageDialog(this, "This item can not move down any more.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String temp = groceries.get(index[0] + 1);
            String indexValue = groceries.get(index[0]);
            groceries.set(index[0] + 1, indexValue);
            groceries.set(index[0], temp);
            groceriesList.setSelectedIndex(index[0] + 1);
            saveGroceries();
        }
    }

    // Method to clear list and save file.
    private void clearButtonClicked() {
        groceries.clear();
        saveGroceries();
    }

    // Method to remove selected items from list.
    private void removeButtonClicked() {
        int[] selectedIndices = groceriesList.getSelectedIndices();
        for (int index = selectedIndices.length - 1; index >= 0; index--) {
            groceries.remove(selectedIndices[index]);
        }
        saveGroceries();
    }

    // Main method to launch program.
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            JFrame frame = new GroceriesFrame();
            frame.setVisible(true);
        });
    }

}


