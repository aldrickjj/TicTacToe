package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class Start extends Application{
    final ToggleGroup mode = new ToggleGroup();
    RadioButton onePlayer;
    RadioButton twoPlayer;
    Button start;
    Label placeholder;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Color Picker");

        //sets up buttons
        onePlayer = new RadioButton();
        twoPlayer = new RadioButton();
        start = new Button();
        onePlayer.setText("1 Player");
        twoPlayer.setText("2 Players");
        onePlayer.setToggleGroup(mode);
        twoPlayer.setToggleGroup(mode);
        start.setText("Start");
        start.setOnAction(new modeSelectionHandler());
        placeholder = new Label("");
        placeholder.setVisible(false);

        //makes pane and adds all buttons to plane
        GridPane pane = new GridPane();
        pane.add(onePlayer,0,0);
        pane.add(twoPlayer,1,0);
        pane.add(start,1,1);
        pane.add(placeholder, 1, 2);

        //makes scene and displays
        Scene scene = new Scene(pane, 600, 400);
        stage.setScene(scene);
        stage.show();
    }


    class modeSelectionHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {

            if(onePlayer.isSelected()){
                //start oneplayer mode
                placeholder.setVisible(true);
                placeholder.setTextFill(Color.RED);
                placeholder.setText("1 player mode selected");
            }
            else if(twoPlayer.isSelected()){
                //start twoplayer mode
                placeholder.setVisible(true);
                placeholder.setTextFill(Color.RED);
                placeholder.setText("2 player mode selected");
            }
            else{
                //display message to choose one
                placeholder.setVisible(true);
                placeholder.setTextFill(Color.RED);
                placeholder.setText("No valid mode selected");
            }
        }
    }

}
