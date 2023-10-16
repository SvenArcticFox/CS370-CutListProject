package cs370.cutlist_project.Cycle1.architectural_spike;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class RectangleApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Rectangle Creator");

        // Create UI elements
        Label widthLabel = new Label("Enter width:");
        TextField widthField = new TextField();
        Label lengthLabel = new Label("Enter length:");
        TextField lengthField = new TextField();
        Rectangle rectangle = new Rectangle();

        // Add event handlers
        widthField.setOnAction(e -> createRectangle(rectangle, widthField, lengthField));
        lengthField.setOnAction(e -> createRectangle(rectangle, widthField, lengthField));

        // Create a layout
        VBox layout = new VBox(20);
        layout.getChildren().addAll(widthLabel, widthField, lengthLabel, lengthField, rectangle);

        // Create a scene and set it on the stage
        Scene scene = new Scene(layout, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createRectangle(Rectangle rectangle, TextField widthField, TextField lengthField) {
        try {
            double width = Double.parseDouble(widthField.getText());
            double length = Double.parseDouble(lengthField.getText());

            // Create a rectangle with the specified width and length
            rectangle.setWidth(width);
            rectangle.setHeight(length);
            rectangle.setFill(Color.RED);
            rectangle.setStroke(Color.BLACK);
        } catch (NumberFormatException e) {
            // Handle invalid input (non-numeric values)
            widthField.setText("Invalid Input");
            lengthField.setText("Invalid Input");
        }
    }
}
