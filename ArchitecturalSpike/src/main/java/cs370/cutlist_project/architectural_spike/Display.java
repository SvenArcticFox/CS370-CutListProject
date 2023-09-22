package cs370.cutlist_project.architectural_spike;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.Debug;
import lombok.Setter;
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
