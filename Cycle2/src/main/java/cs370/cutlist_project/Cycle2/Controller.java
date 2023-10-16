package cs370.cutlist_project.Cycle2;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable  {
    //Variables
    Sheet s = new Sheet();
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
    private Rectangle rect;

    @FXML
    private Pane recPane;

    @FXML
    private TableView<Cut> cutTable;

    @FXML
    private TableColumn<Cut, Double> lengthCol;

    @FXML
    private TableColumn<Cut, Double> widthCol;

    @FXML
    private TableColumn<Cut, String> labelCol;

    ObservableList<Cut> cutList = FXCollections.observableArrayList();
    ObservableList<Rectangle> recList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lengthCol.setCellValueFactory(new PropertyValueFactory<>("length"));
        widthCol.setCellValueFactory(new PropertyValueFactory<>("width"));

        labelCol.setCellValueFactory(new PropertyValueFactory<>("cutPartCode"));
        cutTable.setItems(cutList);
    }


    public void sheetMaker(ActionEvent actionEvent) {
        if(!sheetInputL.getText().isEmpty() || !sheetInputW.getText().isEmpty()) {
            s.setLength(Double.parseDouble(sheetInputL.getText()));
            s.setWidth(Double.parseDouble(sheetInputW.getText()));
            rect.setVisible(true);
            rect.setHeight(s.getLength());
            rect.setWidth(s.getWidth());
            recPane.setPrefHeight(s.getLength());
            recPane.setPrefWidth(s.getWidth());
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.showAndWait();
        }
    }

    public void cutMaker(ActionEvent actionEvent) {
        if(!cutInputL.getText().isEmpty() || !cutInputW.getText().isEmpty() || !cutInputLabel.getText().isEmpty())
        {
            Cut c = new Cut(Double.parseDouble(cutInputL.getText()),Double.parseDouble(cutInputW.getText()),cutInputLabel.getText());
            c.rec.setFill(Color.CYAN);
            cutList.add(c);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.showAndWait();
        }

    }
    private Rectangle findLargestRectangle() {
        Rectangle largest = null;
        double largestArea = 0;

        for (Node node : recPane.getChildren()) {
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
    public void printRec()
    {
        for (Cut cut : cutList)
        {
            recPane.getChildren().add(cut.rec);
            System.out.println(recPane.getChildren());
        }
        Rectangle largestRectangle = findLargestRectangle();

        if (largestRectangle != null)
        {
            double x = rect.getLayoutX();
            double y = rect.getLayoutY();
            boolean isOverlap;
            // Move the largest rectangle
            largestRectangle.setX(x);
            largestRectangle.setY(y);
            do {
                isOverlap = false;
                //largestRectangle.setX(x);
                //largestRectangle.setY(y);
                // Check for collisions with other rectangles
                for (Cut cut: cutList ) {
                    if (cut.rec != largestRectangle && largestRectangle.getBoundsInParent().intersects(cut.rec.getBoundsInParent())) {
                        isOverlap = true;
                        cut.rec.setX(cut.rec.getX() + .01); // Adjust the x position (you can change this step size as needed)
                        if (x + largestRectangle.getWidth() > rect.getX() + rect.getWidth()) {
                            x = rect.getX(); // Reset x position if it goes beyond the original rectangle's boundary
                            cut.rec.setY(rect.getY() + .01);
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

    public void makeCuts(ObservableList<Cut> cl){
        if(!recPane.getChildren().isEmpty())
        {
            recPane.getChildren().clear();
            recList.clear();
        }
        for(Cut cut : cl)
        {
            boolean isOverlap = false;
            double x =0.0;// Sets Each cut as (0,0)
            double y =0.0;
            //When initially set up, it will find the
            do {
                cut.rec.setX(x);
                cut.rec.setY(y);
                x += 0.1;
                if(isOverlap && x + cut.rec.getWidth() > rect.getWidth())
                {
                    x = 0.0;
                    y += .1;
                }
                isOverlap = recList.stream()
                        .anyMatch(rect -> cut.rec.getBoundsInParent().intersects(rect.getBoundsInParent()));

            } while (isOverlap);
                recList.add(cut.rec);
        }
    }

    public void optimize(ActionEvent actionEvent) {
        makeCuts(cutList);
        printRec();
    }
}
