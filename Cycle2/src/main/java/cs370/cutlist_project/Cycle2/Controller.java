package cs370.cutlist_project.Cycle2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable  {

    @FXML
    private TextField sheetInputL;

    @FXML
    private TextField sheetInputW;

    @FXML
    private TextField cutInputL;

    @FXML
    private TextField cutInputW;

    @FXML
    private TextField cutInputLabel;

    @FXML
    private Rectangle recta;

    @FXML
    private Pane recPane;

    @FXML
    private TableView<?> cutTable;

    @FXML
    private TableColumn<?, ?> lengthCol;

    @FXML
    private TableColumn<?, ?> widthCol;

    @FXML
    private TableColumn<?, ?> labelCol;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
