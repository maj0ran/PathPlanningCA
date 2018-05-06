import javafx.application.Application;
import javafx.stage.Stage;
import mrn.ui.JfxUi;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Parent root = FXMLLoader.load(getClass().getResource("mrn.ui.view_1.fxml"));
        JfxUi ui = new JfxUi(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
