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
            c.rec.setStroke(Color.GRAY);
            cutList.add(c);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.showAndWait();
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
    public void printRec()
    {
        for (Cut cut : cutList)
        {
            recPane.getChildren().add(cut.rec);
            Label l = new Label(cut.getCutPartCode());
            l.relocate(cut.rec.getX() + (cut.rec.getWidth()/2) -10, cut.rec.getY()+(cut.rec.getHeight()/2));
            recPane.getChildren().add(l);

            System.out.println(recPane.getChildren());
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
                x += 0.01;
                if(isOverlap && x + cut.rec.getWidth() > rect.getWidth())
                {
                    x = 0.0;
                    y += .01;
                }
                else if(isOverlap && x + cut.rec.getWidth() > rect.getWidth() && y +cut.rec.getHeight() > rect.getHeight())
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.showAndWait();
                    break;
                }
                isOverlap = recList.stream()
                        .anyMatch(rect -> cut.rec.getBoundsInParent().intersects(rect.getBoundsInParent()));

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


    public void optimize(ActionEvent actionEvent) {
        cutList = organizeCutList(cutList);
        cutTable.setItems(cutList);
        makeCuts(cutList);
        printRec();
    }
}
