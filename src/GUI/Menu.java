package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Menu {
    private RadioButton onePlayer;
    private RadioButton twoPlayer;
    private Label placeholder;
    private Scene scene;
    private GameBoardPane onePlayerPane;
    private GameBoardPane twoPlayerPane;
    private Pane pane;

    /**
     * Constructor for Menu which also sets up the layout of the Menu
     */
    public Menu(){
        //sets up buttons
        Label header = new Label();
        header.setText("Ready to Start?\nSelect a mode.");
        header.setFont(new Font(27));
        header.setTextFill(Color.BLUE);
        header.setLayoutX(160.0);
        header.setLayoutY(200.0);
        onePlayer = new RadioButton("1 Player");
        onePlayer.setLayoutX(100.0);
        onePlayer.setLayoutY(350.0);
        twoPlayer = new RadioButton("2 Players");
        twoPlayer.setLayoutX(320.0);
        twoPlayer.setLayoutY(350.0);
        Button start = new Button("Start");
        start.setLayoutX(220.0);
        start.setLayoutY(400.0);
        ToggleGroup mode = new ToggleGroup();
        onePlayer.setToggleGroup(mode);
        twoPlayer.setToggleGroup(mode);
        start.setOnAction(new modeSelectionHandler());
        placeholder = new Label("");
        placeholder.setVisible(false);
        placeholder.setLayoutX(200.0);
        placeholder.setLayoutY(440.0);
        Label gap = new Label("");
        gap.setVisible(false);

        //makes pane and adds all buttons and labels to plane
        pane = new Pane();
        pane.getChildren().addAll(header, onePlayer, twoPlayer, start, placeholder);

        //makes scene
        scene = new Scene(pane, 500, 700);
    }


    /**
     * Adds the Panes for the two modes to the menu
     * @param onePlayerPane the GameBoardPane for the one player mode
     * @param twoPlayerPane the GameBoardPane for the two player mode
     */
    public void addModes(GameBoardPane onePlayerPane, GameBoardPane twoPlayerPane){
        this.onePlayerPane = onePlayerPane;
        this.twoPlayerPane = twoPlayerPane;
    }


    /**
     * Simple getter for the Menu scene
     * @return the scene
     */
    public Scene getScene(){
        return this.scene;
    }


    /**
     * Simple getter for the pane
     * @return the pane
     */
    public Pane getPane() {return this.pane;}

    /**
     * Resets the menu
     */
    private void menuReset(){
        placeholder.setVisible(false);
        onePlayer.setSelected(false);
        twoPlayer.setSelected(false);
    }


    /**
     * The handler for the start button, checks which button is selected
     */
    class modeSelectionHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {

            if(onePlayer.isSelected()){
                onePlayerPane.reset();
                menuReset();
                scene.setRoot(onePlayerPane.getPane());
            }
            else if(twoPlayer.isSelected()){
                twoPlayerPane.reset();
                menuReset();
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
