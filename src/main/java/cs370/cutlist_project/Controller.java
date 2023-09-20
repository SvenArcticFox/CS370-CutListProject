package cs370.cutlist_project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import java.awt.event.ActionEvent;



public class Controller {
    @FXML
    public TextField lengthInput;
    @FXML
    public TextField widthInput;
    @FXML
    public Button sheetButton;


    @FXML
    void sheetMaker(ActionEvent event)
    {
        String len = lengthInput.getText();
        String wid = widthInput.getText();
        double leng = Double.parseDouble(len);
        double width = Double.parseDouble(wid);
        Sheet s = new Sheet(leng, width);
    }


}
