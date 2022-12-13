import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class StudentRegistrationController {

    // Import variables from fxml file
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField yob;
    @FXML private TextField tempPassword;
    @FXML private Label messageLabel;

    @FXML
    protected void registerButtonClicked() {
        // if any required field is empty display error message.
        if (firstName.getText().isEmpty() || lastName.getText().isEmpty() || yob.getText().isEmpty()) {
            tempPassword.setText("");
            messageLabel.setText("Please enter first and last name and year of birth.");
        } else {
            // display welcome message and set temporary password field.
            messageLabel.setText("Welcome " + firstName.getText() + " " + lastName.getText() + "!");
            String password = firstName.getText() + "*" + yob.getText();
            tempPassword.setText(password);
        }
    }

    // Exit app on exit button click
    @FXML
    protected void exitButtonClicked() {
        System.exit(0);
    }

}
