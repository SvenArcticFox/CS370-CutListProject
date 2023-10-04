package cs370.cutlist_project.architectural_spike;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;


//import java.beans.EventHandler;

public class HelloWorldTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Sheet s = new Sheet();
        primaryStage.setTitle("Entry");

        Label swag = new Label("This will show the current value of sheets you have");
        TextField inp = new TextField();

        Button stop = new Button("Proceed");
        /*
            This event handler is used for getting the String from the InputField to
            become the quantity of the sheets needed for the project.
            After that it will show the user on the screen the value they have.
            Will activate when user presses Enter button.
         */
        EventHandler<ActionEvent> eve = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                String num = inp.getText();
                int numOfSheet = Integer.parseInt(num);
                s.setQuantity(numOfSheet);
                swag.setText(num);
            }
        };
        inp.setOnAction(eve);

        TilePane pane = new TilePane();

        pane.getChildren().add(inp);
        pane.getChildren().add(swag);
        pane.getChildren().add(stop);
        Scene sc = new Scene(pane, 300, 200);


        primaryStage.setScene(sc);
        primaryStage.show();
        /*
        This will cause the screen to close and go to the next screen when Button is pressed
        */
        EventHandler<ActionEvent> err = new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent e)
            {
                primaryStage.close();
                Display dis = new Display();
                dis.Pane(s);
            }
        };
        stop.setOnAction(err);

    }

}
