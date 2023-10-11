package cs370.cutlist_project.Cycle2;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primStage) throws Exception {
        primStage.setTitle("Cut List Optimizer");
        Parent root = FXMLLoader.load(getClass().getResource("/cutlist-app.fxml"));
        Scene scene = new Scene(root);

        primStage.setScene(scene);
        primStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}