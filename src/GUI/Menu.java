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
    private int modeChoice = 0;
    private GameBoardPane onePlayerPane;
    private GameBoardPane twoPlayerPane;

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
        GridPane grid = new GridPane();
        grid.add(onePlayer,0,0);
        grid.add(gap, 1, 1);
        grid.add(twoPlayer,2,0);
        grid.add(start,1,3);
        //pane.add(gap, 1, 2);
        grid.add(placeholder, 1, 4);

        //makes scene
        scene = new Scene(grid, 500, 700);
    }

    public void addModes(GameBoardPane onePlayerPane, GameBoardPane twoPlayerPane){
        this.onePlayerPane = onePlayerPane;
        this.twoPlayerPane = twoPlayerPane;
    }

    public int getModeChoice() {return modeChoice;}

    public Scene getScene(){
        return this.scene;
    }

    class modeSelectionHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {

            if(onePlayer.isSelected()){
                modeChoice = 1;
            }
            else if(twoPlayer.isSelected()){
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
