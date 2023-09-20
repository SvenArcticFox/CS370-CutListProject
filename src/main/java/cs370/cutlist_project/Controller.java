package cs370.cutlist_project;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Controller {

    @FXML
    public VBox rectBox;
    Rectangle rec = new Rectangle();
    @FXML
    private TextField stockSheetLengthField;

    @FXML
    private TextField stockSheetWidthField;

   /*
    @FXML
    private TextField numberOfStockSheetsField;

    */

    @FXML
    private TextField cutLengthField;

    @FXML
    private TextField cutWidthField;

    @FXML
    private TextField numberOfCutSheetsField;


    @FXML
    private TableView<?> cuttingPatternsTable;

    @FXML
    private TableColumn<?, ?> stockSheetColumn;

    @FXML
    private TableColumn<?, ?> partsColumn;

    //@FXML
    //private Button addStockSheetButton;

    //@FXML
    //private Button addPartButton;

    @FXML
    private Button optimizeButton;

    /*This will be added back soon

        @FXML
        void handleAddStockSheet(MouseEvent event) {
            // Implement logic to add stock sheet to the table
        }

        @FXML
        void handleAddPart(MouseEvent event) {
            // Implement logic to add part to the table
        }
        */
/*
    As of right now, the idea here to have the button be pressed, and the sheet is displayed at the bottom of the screen
    Right now, they show that the sheet is being made, and in the console telling the length, width and area.S
*/
    @FXML
    void handleOptimize(MouseEvent event) {
        Sheet s = new Sheet(Double.parseDouble(stockSheetLengthField.getText()), Double.parseDouble(stockSheetWidthField.getText()));
        System.out.println("The length is: " + s.getLength() + ". The width is: " + s.getWidth() + "\n" + "The area is: " + s.getTotalArea());
        createRect(rec, s);
    }

    private void createRect(Rectangle rec, Sheet s)
    {
        rec.setWidth(s.getWidth());
        rec.setHeight(s.getLength());
        rec.setFill(Color.RED);
        rec.setStroke(Color.BLACK);
        if(rectBox.getChildren().contains(rectBox)) {
            rectBox.getChildren().removeAll();
            rectBox.getChildren().add(rec);
        }
        else
        {
          rectBox.getChildren().add(rec);
        }
    }

}
