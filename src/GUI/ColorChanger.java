package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;

public class ColorChanger extends Application {
    GridPane root;
    Button blue, yellow, red;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Color Picker");
        blue = new Button();
        yellow = new Button();
        red = new Button();

        blue.setText("Blue");
        yellow.setText("Yellow");
        red.setText("Red");

        root = new GridPane();
        root.add(blue, 0, 0);
        root.add(red, 1, 0);
        root.add(yellow, 0, 1);

        Scene s = new Scene(root, 200, 200);
        stage.setScene(s);
        stage.show();

        //Create a single handler to handle all three buttons
        ColorChangeHandler handler = new ColorChangeHandler();
        blue.setOnAction(handler);
        red.setOnAction(handler);
        yellow.setOnAction(handler);

        /**
         * Could also do the following - commented out for now, but you can comment out the above
         * and uncomment the below
         */

        //blue.setOnAction(e -> root.setStyle("-fx-background-color: #0000FF"));
        //red.setOnAction(e -> root.setStyle("-fx-background-color: #FF0000"));
        //yellow.setOnAction(e -> root.setStyle("-fx-background-color: #FFFF00"));
    }

    class ColorChangeHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            Button source = (Button) e.getSource();
            if (source == blue) {
                root.setStyle("-fx-background-color: #0000FF");
            } else if (source == red) {
                root.setStyle("-fx-background-color: #FF0000");
            } else {
                root.setStyle("-fx-background-color: #FFFF00");
            }
        }
    }
}
