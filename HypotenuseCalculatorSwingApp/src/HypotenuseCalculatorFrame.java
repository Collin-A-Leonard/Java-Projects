import java.awt.*;
import javax.swing.*;
import java.lang.Math;
import java.math.RoundingMode;
import java.text.NumberFormat;


public class HypotenuseCalculatorFrame extends JFrame {

    // Initialize instance variables
    JTextField sideA;
    JTextField sideB;
    JTextField sideC;
    JButton calculateButton;
    JButton exitButton;

    // Constructor sets looks and feel and calls initComponents method.
    public HypotenuseCalculatorFrame() {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException |
                IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }
        initComponents();
    }

    private void initComponents() {
        // Set title, close operation, and setLocationByPlatform
        setTitle("Right Triangles");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);

        // Create text fields from instance variables and set the appropriate ones to not be editable.
        sideA = new JTextField();
        sideB = new JTextField();
        sideC = new JTextField();
        sideC.setEditable(false);

        // Set preferred and minimum size using dimension object.
        Dimension dim = new Dimension(200, 25);
        sideA.setPreferredSize(dim);
        sideB.setPreferredSize(dim);
        sideC.setPreferredSize(dim);
        sideA.setMinimumSize(dim);
        sideB.setMinimumSize(dim);
        sideC.setMinimumSize(dim);

        // Create buttons and add their even listeners.
        calculateButton = new JButton("Calculate");
        exitButton = new JButton("Exit");
        calculateButton.addActionListener(
                e -> calculateButtonClicked()
        );
        exitButton.addActionListener(
                e -> exitButtonClicked()
        );

        // Create both panels and add their components.
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(calculateButton);
        buttonPanel.add(exitButton);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.add(new JLabel("Side A:"), getConstraints(0, 0));
        panel.add(sideA, getConstraints(1, 0));
        panel.add(new JLabel("Side B:"), getConstraints(0, 1));
        panel.add(sideB, getConstraints(1, 1));
        panel.add(new JLabel("Side C:"), getConstraints(0, 2));
        panel.add(sideC, getConstraints(1, 2));

        // Add panels to JFrame in appropriate locations.
        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // setSize and setVisible to true
        setSize(new Dimension(330, 200));
        setVisible(true);
    }

    // Helper method to return GridBagConstraints objects
    private GridBagConstraints getConstraints(int x, int y) {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(5, 5, 0, 5);
        c.gridx = x;
        c.gridy = y;
        return c;
    }

    private void calculateButtonClicked() {
        // Create Validator object and use it to create errorMsg for validation.
        Validator v = new Validator();
        String errorMsg = "";
        errorMsg += v.isDouble(sideA.getText(), "Side A");
        errorMsg += v.isDouble(sideB.getText(), "Side B");

        // if there are no error messages run the program when calculateButton is clicked
        if (errorMsg.isEmpty()) {
            double aValue = Double.parseDouble(sideA.getText());
            double bValue = Double.parseDouble(sideB.getText());
            double hypotenuse = Math.sqrt((aValue * aValue) + (bValue * bValue));
            NumberFormat formatter = NumberFormat.getInstance();
            formatter.setRoundingMode(RoundingMode.HALF_UP);
            formatter.setMaximumFractionDigits(3);
            String formattedHypotenuse = formatter.format(hypotenuse);
            sideC.setText(formattedHypotenuse);
        } else { // if there are error messages display them.
            JOptionPane.showMessageDialog(this, errorMsg, "Invalid Data", JOptionPane.ERROR_MESSAGE);
        }
    }

    // if button is exit program.
    private void exitButtonClicked() {
        System.exit(0);
    }

    // run GUI
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            JFrame frame = new HypotenuseCalculatorFrame();
        });
    }
}
