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
        String num = "";
        int u = s.getQuantity();
        String swag = Integer.toString(u);
        primaryStage.setTitle("Entry");
        Label q = new Label(swag);
        Label value = new Label("0");

        TextField inp = new TextField("Enter the number of sheets you have");


        EventHandler<ActionEvent> eve = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                value.setText(inp.getText());

            }
        };
        inp.setOnAction(eve);

        TilePane pane = new TilePane();
        pane.getChildren().add(value);
        pane.getChildren().add(inp);
        pane.getChildren().add(q);
        Scene sc = new Scene(pane, 300, 200);

        primaryStage.setScene(sc);
        num = value.getText();
        int numOfSheet = Integer.parseInt(num);
        s.setQuantity(numOfSheet);

        primaryStage.show();
    }
}
