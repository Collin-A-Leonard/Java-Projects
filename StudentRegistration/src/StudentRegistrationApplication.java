import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StudentRegistrationApplication extends Application {

    // Override start method and create GUI.
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("StudentRegistration.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Student Registration");
        stage.show();
    }

    // Main method
    public static void main(String[] args) {
        launch(args);
    }
    
}
