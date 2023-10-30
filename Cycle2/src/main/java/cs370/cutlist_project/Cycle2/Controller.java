package cs370.cutlist_project.Cycle2;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
    public StackPane labelPane;
    @FXML
    private TextField sheetInputW;

    @FXML
    private TextField cutInputL;
    Alert a = new Alert(Alert.AlertType.NONE);
    @FXML
    private TextField cutInputW;

    @FXML
    private TextField cutInputLabel;

    @FXML
    private Rectangle rectSheet;

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
    //Initializes the table view, allowing for the values of the cuts to be shown.
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lengthCol.setCellValueFactory(new PropertyValueFactory<>("length"));
        widthCol.setCellValueFactory(new PropertyValueFactory<>("width"));

        labelCol.setCellValueFactory(new PropertyValueFactory<>("cutPartCode"));
        cutTable.setItems(cutList);
    }

    //Makes the value of the sheet, if the values of the inputValues are nothing it wont go through,
    //also sets the dimensions of the rectangle
    public void sheetMaker(ActionEvent actionEvent) {
        if(sheetInputW.getText().isEmpty() || sheetInputL.getText().isEmpty()) {
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("You do not have a value for the Sheet");
            a.showAndWait();
        }
        else
        {
            s.setLength(Double.parseDouble(sheetInputL.getText()));
            s.setWidth(Double.parseDouble(sheetInputW.getText()));
            rectSheet.setVisible(true);
            rectSheet.setHeight(s.getLength());
            rectSheet.setWidth(s.getWidth());
            recPane.setPrefHeight(s.getLength());
            recPane.setPrefWidth(s.getWidth());
        }
    }
    //Creates the cut and put it in the observablelist of Cuts
    public void cutMaker(ActionEvent actionEvent) {
        if(cutInputL.getText().isEmpty() || cutInputW.getText().isEmpty() || cutInputLabel.getText().isEmpty())
        {
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("You do not have a value for the Cut");
            a.showAndWait();
        }
        else {
            Cut c = new Cut(Double.parseDouble(cutInputL.getText()),Double.parseDouble(cutInputW.getText()),cutInputLabel.getText());
            c.rec.setFill(Color.CYAN);
            c.rec.setStroke(Color.GRAY);
            cutList.add(c);
        }

    }
    /*
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
    }*/
    //Prints the rectangles on screen, and puts the labels in the center of each cut.
    public void printRec()
    {
        for (Cut cut : cutList)
        {
            recPane.getChildren().add(cut.rec);
            Label l = new Label(cut.getCutPartCode());
            l.relocate(cut.rec.getX() + (cut.rec.getWidth()/2) -10, cut.rec.getY()+(cut.rec.getHeight()/2) - 10);
            recPane.getChildren().add(l);
        }
    }

    /*

     */
//Sets the x and ys of the cuts on the sheet.
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
                x += 0.0001;
                if(isOverlap && x + cut.rec.getWidth() > rectSheet.getWidth())
                {
                    x = 0.0;
                    y += .0001;
                }
                else if(isOverlap && x + cut.rec.getWidth() > rectSheet.getWidth() && y +cut.rec.getHeight() > rectSheet.getHeight())
                {
                    cutList.remove(cut);
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setContentText("The cut did not fit, removing " + cut.getNotes());
                    a.showAndWait();
                    break;
                }
                isOverlap = recList.stream()
                        .anyMatch(rectSheet -> cut.rec.getBoundsInParent().intersects(rectSheet.getBoundsInParent()));

            } while (isOverlap);
            recList.add(cut.rec);
        }
    }
    //Making a class that will organize the cutList to have it ascending order based on
    //Area, taking the area of each cut, putting it in its own value and adding it to its own
    //ObservableList and then making it back.
    public ObservableList organizeCutList(ObservableList<Cut> cl)
    {
        int size = cl.size();
        int ind = 0;
        ObservableList<Cut> finale = FXCollections.observableArrayList();
        do {
            double largeArea=0.0;

            for(Cut cut : cl)
            {
                if(cut.getArea() > largeArea)
                {
                    ind = cl.indexOf(cut);
                    largeArea = cut.getArea();
                }
            }
            finale.add(cl.get(ind));
            cl.remove(cl.get(ind));
        }while(finale.size() != size);

        return finale;
    }

    //The activation of the whole algorithm that was made, it organizes the cut list in descending order, puts them on the
    //tableview, then finds their x and y, then prints.
    public void optimize(ActionEvent actionEvent) {
        cutList = organizeCutList(cutList);
        cutTable.setItems(cutList);
        makeCuts(cutList);
        printRec();
    }
}
