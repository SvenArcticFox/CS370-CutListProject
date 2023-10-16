package cs370.cutlist_project.architectural_spike;

import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Display {

    public void Pane(Sheet s){
        Stage secondary = new Stage();
        secondary.setTitle("Display");
        Label lb = new Label(Integer.toString(s.getQuantity()));


        TilePane pan = new TilePane();

        pan.getChildren().add(lb);
        Scene sc = new Scene(pan, 400, 100);
        secondary.setScene(sc);
        secondary.show();

    }

}
