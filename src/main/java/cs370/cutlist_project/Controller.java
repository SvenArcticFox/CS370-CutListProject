package cs370.cutlist_project;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class Controller {

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

    @FXML
    private Button addStockSheetButton;

    @FXML
    private Button addPartButton;

    @FXML
    private Button optimizeButton;

    @FXML
    void handleAddStockSheet(MouseEvent event) {
        // Implement logic to add stock sheet to the table
    }

    @FXML
    void handleAddPart(MouseEvent event) {
        // Implement logic to add part to the table
    }

    @FXML
    void handleOptimize(MouseEvent event) {
        // Implement logic to optimize cutting patterns
    }
}
