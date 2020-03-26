package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DontTouchTheButton extends Application {
    //A label field defined at the class level so the action listener can access it
    Label placeholder;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Don't touch the button!");
        //Create the labels
        Label text = new Label("Whatever you do, don't touch the button.");

        //Create our placeholder - initially blank, but the button listener
        //will update it
        placeholder = new Label("");
        placeholder.setVisible(false);

        //Create the button
        Button button = new Button();
        button.setText("Button"); //sets the button's text
        button.setOnAction(new ButtonHandler()); //defines what happens when the button is clicked
        //in this case, we are calling "handle" in our
        //ButtonHandler class

        FlowPane root = new FlowPane();
        //add our elements to the pane in the order we want them displayed
        root.getChildren().add(text);
        root.getChildren().add(button);
        root.getChildren().add(placeholder);
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    /**
     * Event Handler class
     * This inner class defines an action to perform when the button is hit
     */
    private class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            //
            placeholder.setVisible(true);
            placeholder.setTextFill(Color.RED);
            placeholder.setText("Oh my god!!!\nWhy would you do this!?!?\nThere's so much blood!!!");
        }
    }
}


