package cs370.cutlist_project;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Controller {
    public Pane rectPane;
    Sheet s = new Sheet();
    Cut c = new Cut();
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
    TableView<Sheet> cuttingPatternsTable = new TableView<Sheet>();

    @FXML
    private TableColumn<?, ?> stockSheetColumn;

    @FXML
    private TableColumn<?, ?> partsColumn;
    @FXML
    public TableColumn<Sheet, Double> lengthColumn;
    @FXML
    public TableColumn<Sheet, Double> widthColumn;
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
        s.setLength(Double.parseDouble(stockSheetLengthField.getText()));
        s.setWidth(Double.parseDouble(stockSheetWidthField.getText()));
        System.out.println("The length is: " + s.getLength() + ". The width is: " + s.getWidth() + "\n" + "The area is: " + s.getTotalArea());
        createRect(rec, s);

        if(!cutLengthField.getText().isEmpty() || !cutWidthField.getText().isEmpty())
        {
            c.setLength(Double.parseDouble(cutLengthField.getText()));
            c.setWidth(Double.parseDouble(cutWidthField.getText()));
            Rectangle rec2 = new Rectangle();
            makeCut(rec2,s,c);
        }
    }

    private void createRect(Rectangle rec, Sheet s)
    {
        if(rectBox.getChildren().contains(rectBox)) {
            rectBox.getChildren().removeAll();
        }
        rec.setWidth(s.getWidth());
        rec.setHeight(s.getLength());
        rec.setFill(Color.RED);
        rec.setStroke(Color.BLACK);
        rectPane.getChildren().add(rec);
    }

    private void makeCut(Rectangle rec2, Sheet s, Cut c)
    {
        if(s.getLength() < c.getLength() || s.getWidth() < c.getWidth())
        {
            System.out.println("DOES NOT WORK");

        }
        else{
            rec2.setWidth(c.getWidth());
            rec2.setHeight(c.getLength());
            rec2.setFill(Color.GREEN);
            rec2.setStroke(Color.BLACK);
            rectPane.getChildren().add(rec2);
        }
    }

}
