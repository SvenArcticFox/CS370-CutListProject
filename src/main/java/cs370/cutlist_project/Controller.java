package cs370.cutlist_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Pane rectPane;
    Sheet s = new Sheet();

    @FXML
    public Rectangle rec;

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


    ObservableList<Cut> cutList = FXCollections.observableArrayList();
    @FXML
    public TableView<Cut> cuttingPatternsTable;

    @FXML
    public TableColumn<Cut, Double> lengthColumn;
    @FXML
    public TableColumn<Cut, Double> widthColumn;
    @FXML
    private Button addStockSheetButton;

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


    As of right now, the idea here to have the button be pressed, and the sheet is displayed at the bottom of the screen
    Right now, they show that the sheet is being made, and in the console telling the length, width and area.S
*/
    @FXML
    void handleOptimize(MouseEvent event) {
        if(!cutLengthField.getText().isEmpty() || !cutWidthField.getText().isEmpty())
        {
            Cut c = new Cut();
            c.setLength(Double.parseDouble(cutLengthField.getText()));
            c.setWidth(Double.parseDouble(cutWidthField.getText()));
            Rectangle rec2 = new Rectangle();
            makeCut(rec2,s,c);
        }
    }
    @FXML
    void sheetCreator(MouseEvent event){
        s.setLength(Double.parseDouble(stockSheetLengthField.getText()));
        s.setWidth(Double.parseDouble(stockSheetWidthField.getText()));
        System.out.println("The length is: " + s.getLength() + ". The width is: " + s.getWidth() + "\n" + "The area is: " + s.getTotalArea());
        createRect(s);

    }
    private void createRect(Sheet si)
    {
        rec.setWidth(si.getWidth());
        rec.setHeight(si.getLength());
        rec.setFill(Color.RED);
        rec.setStroke(Color.BLACK);
    }
    private void makeCut(Rectangle rec2, Sheet s1, Cut c1)
    {
        if(s1.getLength() < c1.getLength() || s1.getWidth() < c1.getWidth())
        {
            System.out.println("DOES NOT WORK");

        }
        else{
            rec2.setWidth(c1.getWidth());
            rec2.setHeight(c1.getLength());
            rec2.setFill(Color.GREEN);
            rec2.setStroke(Color.BLACK);
            cutList.add(c1);
            rectPane.getChildren().add(rec2);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lengthColumn.setCellValueFactory(new PropertyValueFactory<Cut, Double>("length"));
        widthColumn.setCellValueFactory(new PropertyValueFactory<Cut, Double>("width"));

        cuttingPatternsTable.setItems(cutList);
    }
}
