package cs370.cutlist_project.Cycle3;


import cs370.cutlist_project.Cycle3.algorithm.Algorithm;
import cs370.cutlist_project.Cycle3.algorithm.CutTree;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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

    @FXML
    private TableColumn<Cut, Double> xCol;
    @FXML
    private TableColumn<Cut, Double> yCol;

    ObservableList<Cut> cutList = FXCollections.observableArrayList();
    //Initializes the table view, allowing for the values of the cuts to be shown.
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lengthCol.setCellValueFactory(new PropertyValueFactory<>("length"));
        widthCol.setCellValueFactory(new PropertyValueFactory<>("width"));
        labelCol.setCellValueFactory(new PropertyValueFactory<>("cutPartCode"));
        xCol.setCellValueFactory(new PropertyValueFactory<>("rec.getX()"));
        yCol.setCellValueFactory(new PropertyValueFactory<>("rec.getY()"));

        cutTable.setItems(cutList);
    }

    //Makes the value of the sheet, if the values of the inputValues are nothing it wont go through,
    //also sets the dimensions of the rectangle
    public void sheetMaker(ActionEvent actionEvent) {
        //If the inputs for the sheet are empty, create an error message
        if(sheetInputW.getText().isEmpty() || sheetInputL.getText().isEmpty()) {
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("You do not have a value for the Sheet");
            a.showAndWait();
        }
        else
        {
            double x = Double.parseDouble(sheetInputW.getText());
            double y = Double.parseDouble(sheetInputL.getText());
            if(x > 0 && y > 0) {
                s.setLength(Double.parseDouble(sheetInputL.getText()));
                s.setWidth(Double.parseDouble(sheetInputW.getText()));
                rectSheet.setVisible(true);
                rectSheet.setHeight(s.getLength());
                rectSheet.setWidth(s.getWidth());
                recPane.setPrefHeight(s.getLength());
                recPane.setPrefWidth(s.getWidth());
            }
            else {
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Sheet size can NOT be negative OR zero");
                a.showAndWait();
            }
        }
    }

    //Creates the cut and put it in the observablelist of Cuts
    public void cutMaker(ActionEvent actionEvent) {
        //if any value is empty, create an error message
        double x = Double.parseDouble(cutInputW.getText());
        double y = Double.parseDouble(cutInputL.getText());
        if (cutInputL.getText().isEmpty() || cutInputW.getText().isEmpty() || cutInputLabel.getText().isEmpty()) {
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("You do not have a value for the Cut");
            a.showAndWait();
        } else if (x <= 0 || y <= 0)
        {
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Cut cannot be negative!");
            a.showAndWait();
        }
        else if (x > rectSheet.getWidth() || y > rectSheet.getHeight())
        {
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Cut can NOT be larger than the sheet");
            a.showAndWait();
        }
        else {
            Cut c = new Cut(y,x,cutInputLabel.getText());
            c.rec.setFill(Color.CYAN);
            c.rec.setStroke(Color.GRAY);
            cutList.add(c);
        }

    }
    //Prints the rectangles on screen, and puts the labels in the center of each cut.
    public void printRec()
    {
        for (Cut cut : cutList)
        {
            recPane.getChildren().add(cut.rec);
            Label l = new Label(cut.getCutPartCode());
            l.setAlignment(Pos.CENTER);
            l.relocate(cut.rec.getX() + (cut.rec.getWidth()/2)-10, cut.rec.getY()+(cut.rec.getHeight()/2)-10);
            recPane.getChildren().add(l);
        }
    }
    //Sets the x and ys of the cuts on the sheet.
    public void displayCuts(ObservableList<Cut> cl){
        if(!recPane.getChildren().isEmpty())
        {
            recPane.getChildren().clear();
        }


        Cut[] cut = new Cut[cl.size()];
        CutTree optimizedCuts = Algorithm.entrance(s, cl.toArray(cut));
        CutTree.Node rootNode = optimizedCuts.getRoot();

        traverseTree(rootNode , null , false);
    }

    private void traverseTree(CutTree.Node currentNode, CutTree.Node prevNode, boolean placeOnWidthAxis) {
        if (prevNode == null) {
            currentNode.getCut().getRec().setX(0);
            currentNode.getCut().getRec().setY(0);
        }
        else if (placeOnWidthAxis) {
            currentNode.getCut().getRec().setX(prevNode.getCut().getRec().getX() + prevNode.getCut().getWidth());
            currentNode.getCut().getRec().setY(prevNode.getCut().getRec().getY());
        }

        else {
            currentNode.getCut().getRec().setX(prevNode.getCut().getRec().getX());
            currentNode.getCut().getRec().setY(prevNode.getCut().getRec().getY() + prevNode.getCut().getLength());
        }

        if (currentNode.getWidthAxis() != null) {
            traverseTree(currentNode.getWidthAxis() , currentNode , true);
        }
        if (currentNode.getLengthAxis() != null) {
            traverseTree(currentNode.getLengthAxis() , currentNode , false);
        }

    }

    /*
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
 */
    //The activation of the whole algorithm that was made, it organizes the cut list in descending order, puts them on the
    //tableview, then finds their x and y, then prints.
    public void optimize(ActionEvent actionEvent) {
        cutTable.setItems(cutList);
        displayCuts(cutList);
        printRec();
      /*  for(Cut cut: cutList) {
            System.out.println("Length: " + cut.getLength() + "  Width: " + cut.getWidth());
        }*/
    }

    public void deleteCut(ActionEvent actionEvent) {
        Cut highlighted = cutTable.getSelectionModel().getSelectedItem();
        int find = 0;
        for(Cut cut: cutList)
        {
            if (cut == highlighted)
            {
                find = cutList.indexOf(cut);
            }
        }
        cutList.remove(find);
        cutTable.getItems().remove(highlighted);
    }
}
