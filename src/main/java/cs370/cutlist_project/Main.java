package cs370.cutlist_project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/CutListAPp.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();



        EventHandler<ActionEvent> eve = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                String num;
            }
        };



    }
    public static void main(String[] args) {
        launch(args);
    }

}
