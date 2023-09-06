package cs370.cutlist_project.architectural_spike;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.Debug;
import lombok.Setter;

//import java.beans.EventHandler;

public class HelloWorldTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Sheet s = new Sheet();
        primaryStage.setTitle("Entry");

        Label value = new Label("0");
        Label swag = new Label("This will show if the value went to the stupid fucking thing");
        TextField inp = new TextField("Enter the number of sheets you have");


        EventHandler<ActionEvent> eve = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                String num = inp.getText();
                int numOfSheet = Integer.parseInt(num);
                s.setQuantity(numOfSheet);
                String p = Integer.toString(numOfSheet);
                swag.setText(p);
            }
        };
        inp.setOnAction(eve);

        TilePane pane = new TilePane();
        pane.getChildren().add(value);
        pane.getChildren().add(inp);
        pane.getChildren().add(swag);
        Scene sc = new Scene(pane, 300, 200);


        primaryStage.setScene(sc);

        primaryStage.show();
    }
}
