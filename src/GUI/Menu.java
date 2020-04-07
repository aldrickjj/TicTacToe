package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Menu {
    private final ToggleGroup mode = new ToggleGroup();
    private RadioButton onePlayer;
    private RadioButton twoPlayer;
    private Button start;
    private Label placeholder;
    private Scene scene;
    private GameBoardPane onePlayerPane;
    private GameBoardPane twoPlayerPane;
    private GridPane pane;

    public Menu(){
        //sets up buttons
        onePlayer = new RadioButton("1 Player");
        twoPlayer = new RadioButton("2 Players");
        start = new Button("Start");
        onePlayer.setToggleGroup(mode);
        twoPlayer.setToggleGroup(mode);
        start.setOnAction(new modeSelectionHandler());
        placeholder = new Label("");
        placeholder.setVisible(false);
        Label gap = new Label("");
        gap.setVisible(false);

        //makes pane and adds all buttons to plane
        pane = new GridPane();
        pane.add(onePlayer,0,0);
        pane.add(gap, 1, 1);
        pane.add(twoPlayer,2,0);
        pane.add(start,1,3);
        //pane.add(gap, 1, 2);
        pane.add(placeholder, 1, 4);

        //makes scene
        scene = new Scene(pane, 500, 700);
    }

    public void addModes(GameBoardPane onePlayerPane, GameBoardPane twoPlayerPane){
        this.onePlayerPane = onePlayerPane;
        this.twoPlayerPane = twoPlayerPane;
    }

    public Scene getScene(){
        return this.scene;
    }

    public GridPane getPane() {return this.pane;}

    class modeSelectionHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {

            if(onePlayer.isSelected()){
                onePlayerPane.reset();
                scene.setRoot(onePlayerPane.getPane());
            }
            else if(twoPlayer.isSelected()){
                twoPlayerPane.reset();
                scene.setRoot(twoPlayerPane.getPane());
            }
            else{
                //display message to choose one
                placeholder.setVisible(true);
                placeholder.setTextFill(Color.RED);
                placeholder.setText("No mode selected");
            }
        }
    }

}
