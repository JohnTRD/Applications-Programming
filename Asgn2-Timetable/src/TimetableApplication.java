import au.edu.uts.ap.javafx.*;
import model.*;
import javafx.application.*;
import javafx.stage.*;
import javafx.fxml.*;
import javafx.scene.*;


public class TimetableApplication extends Application {
    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage stage) throws Exception {
        //start of test code
        //ViewLoader.showStage(new University(), "/view/university.fxml", "Add Student", new Stage());
FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/university.fxml"));
		Parent root = loader.load();
		stage.setTitle("University");
		stage.setScene(new Scene(root));
		stage.sizeToScene();
		stage.show();
    }
}
