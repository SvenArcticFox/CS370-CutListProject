package cs370.cutlist_project.Cycle1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Button cutButton;
    public Pane rPane;
    Sheet s = new Sheet();

    @FXML
    public Rectangle rec;

    @FXML
    private TextField stockSheetLengthField;

    @FXML
    private TextField stockSheetWidthField;

    private Button delButton;
    @FXML
    private TextField cutLengthField;

    @FXML
    private TextField cutWidthField;



    ObservableList<Rectangle> rectangles = FXCollections.observableArrayList();
    ObservableList<Cut> cutList = FXCollections.observableArrayList();
    @FXML
    public TableView<Cut> cuttingPatternsTable;

    @FXML
    public TableColumn<Cut, Double> lengthColumn;
    @FXML
    public TableColumn<Cut, Double> widthColumn;

    public TableColumn<Cut, String> labelColumn;


    @FXML
    private Button optimizeButton;


    /*
    As of right now, the idea here to have the button be pressed, and the sheet is displayed at the bottom of the screen
    Right now, they show that the sheet is being made, and in the console telling the length, width and area.S
*/
    @FXML
    void handleOptimize(MouseEvent event) {
            makeCut(cutList);
            printAllRectangles();
            Cut[] cList = cutList.toArray(new Cut[0]);
    }

    @FXML
    void cutCreator(MouseEvent event)
    {
        if(!cutLengthField.getText().isEmpty() || !cutWidthField.getText().isEmpty())
        {

            Cut c = new Cut();
            c.setLength(Double.parseDouble(cutLengthField.getText()));
            c.setWidth(Double.parseDouble(cutWidthField.getText()));

            if (s.getLength() < c.getLength() || s.getWidth() < c.getWidth()) {
                System.out.println("DOES NOT WORK");

            } else {
                c.setNotes(cutList.size() + 1 +"");
                cutList.add(c);
            }
        }
    }

    @FXML
    void sheetCreator(MouseEvent event){
        s.setLength(Double.parseDouble(stockSheetLengthField.getText()));
        s.setWidth(Double.parseDouble(stockSheetWidthField.getText()));
        System.out.println("The length is: " + s.getLength() + ". The width is: " + s.getWidth() + "\n" + "The area is: " + s.getTotalArea());
        rec.setVisible(true);
        rec.setWidth(s.getWidth());
        rec.setHeight(s.getLength());
        rec.setFill(Color.RED);
        rec.setStroke(Color.BLACK);
        rPane.setPrefSize(rec.getWidth(), rec.getHeight());
        rPane.setLayoutX(rec.getX());
        rPane.setLayoutY(rec.getY());
    }
    /*
        In this section, it creates the rectangle that will represent the cut on the grid
        it takes in the ObservList, creates a for loop, and if the size of the cut is larger in any way to the sheet
        it wont make it.
     */
    private void makeCut(ObservableList<Cut> cl)
    {
        if(!rectangles.isEmpty())
        {
            rPane.getChildren().clear();
            rectangles.clear();
        }
        for (Cut cut : cl) {
            Rectangle subRectangle = new Rectangle(cut.getWidth(), cut.getLength());
            subRectangle.setFill(Color.GREEN);
            subRectangle.setStroke(Color.DARKGRAY);
            boolean isOverlap = false;
            double x =0.0;// Sets Each cut as (0,0)
            double y =0.0;
            //When initially set up, it will find the
            do {
                subRectangle.setX(x);
                subRectangle.setY(y);
                x += 0.1;
                if(isOverlap && x + subRectangle.getWidth() > rec.getWidth())
                {
                    x = 0.0;
                    y += .1;
                }
                isOverlap = rectangles.stream()
                        .anyMatch(rect -> subRectangle.getBoundsInParent().intersects(rect.getBoundsInParent()));

            } while (isOverlap);

            rectangles.add(subRectangle);
        }
    }
    private Rectangle findLargestRectangle() {
        Rectangle largest = null;
        double largestArea = 0;

        for (Node node : rPane.getChildren()) {
            if (node instanceof Rectangle) {
                Rectangle rect = (Rectangle) node;
                double area = rect.getWidth() * rect.getHeight();
                if (area > largestArea) {
                    largest = rect;
                    largestArea = area;
                }
            }
        }

        return largest;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lengthColumn.setCellValueFactory(new PropertyValueFactory<>("length"));
        widthColumn.setCellValueFactory(new PropertyValueFactory<>("width"));

        labelColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));
        cuttingPatternsTable.setItems(cutList);

    }

    private void printAllRectangles() {
        // Add all rectangles to the root
        rPane.getChildren().addAll(rectangles);
        // Find the largest rectangle in the list
        Rectangle largestRectangle = findLargestRectangle();

        if (largestRectangle != null) {
            // Calculate the position to move the largest rectangle to the top left of the original rectangle
            double x = rec.getLayoutX();
            double y = rec.getLayoutY();
            boolean isOverlap;
            // Move the largest rectangle
            largestRectangle.setX(x);
            largestRectangle.setY(y);
            do {
                isOverlap = false;
                //largestRectangle.setX(x);
                //largestRectangle.setY(y);
                // Check for collisions with other rectangles
                for (Rectangle rect : rectangles) {
                    if (rect != largestRectangle && largestRectangle.getBoundsInParent().intersects(rect.getBoundsInParent())) {
                        isOverlap = true;
                        rect.setX(rect.getX() + .01); // Adjust the x position (you can change this step size as needed)
                        if (x + largestRectangle.getWidth() > rec.getX() + rec.getWidth()) {
                            x = rec.getX(); // Reset x position if it goes beyond the original rectangle's boundary
                            rect.setY(rect.getY() + .01);
                        }
                        break;

                    }

                    System.out.println("////////");
                    System.out.println("x: " + rect.getX());
                    System.out.println("y: " + rect.getY());
                }
            } while (isOverlap);
            System.out.println("END");
        }
    }
    //End of Controller
    // /*
    //    private void editableCols(){
    //        widthColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    //        widthColumn.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setWidth(e.getNewValue()));
    //
    //        lengthColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    //        lengthColumn.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setLength(e.getNewValue()));
    //
    //    }*/
}
