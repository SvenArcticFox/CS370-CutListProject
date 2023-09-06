package cs370.cutlist_project.architectural_spike;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelloWorldTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        Label helloLabel = new Label("Hello world!");

        Pane pane = new StackPane();
        pane.getChildren().add(helloLabel);

        Scene sc = new Scene(pane, 200, 200);

        primaryStage.setScene(sc);

        primaryStage.show();
    }
}
