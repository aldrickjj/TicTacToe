package GUI;

import GAMES.ComputerGameInterface;
import GAMES.GameInterface;
import GAMES.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.*;

public class GameBoardPane {
    private Pane pane;
    private HashMap<Button, Integer[]> tableButtonMap = new HashMap<>();
    private HashMap<Button, Label> labelToButtonMap = new HashMap<>();
    private GameInterface game;
    private Label gameOverLabel = new Label();
    private Label currentTeamLabel;
    private Menu menu;


    /**
     * Constructor for GameBoardPane
     * @param game the mode of the game (one player or two player)
     * @param menu reference to the main menu
     */
    public GameBoardPane(GameInterface game, Menu menu) {
        this.game = game;
        this.menu = menu;
        setUpPane();
    }

    /**
     * Getter for pane
     * @return the pane field
     */
    public Pane getPane() {
        return this.pane;
    }

    /**
     * Sets up pane with the appropriate layout
     */
    private void setUpPane() {
        setLabel(gameOverLabel, Pos.CENTER, 500.0, 520.0, 0.0, 90.0, 12.0, false, "");

        this.pane = new Pane();

        Label headerLabel = new Label();
        setLabel(headerLabel, null, null, null, 98.0, 15.0, 24.0, null, "Current Team: ");

        currentTeamLabel = new Label();
        setLabel(currentTeamLabel, null, null, null, 250.0, 15.0, 24.0, null, "X");

        pane.getChildren().addAll(headerLabel, currentTeamLabel);

        for(int i = 0; i < 25; i += 1) {
            Label tableLabel = new Label();
            setLabel(tableLabel, Pos.CENTER, 100.0, 100.0, (i / 5) * 100.0, (i % 5) * 100.0 + 100.0, 72.0, false, "");
            Button tableButton = new Button();
            tableButton.setMinSize(100.0, 100.0);
            setWidgetPosition(tableButton, (i / 5) * 100.0, (i % 5) * 100.0 + 100.0);
            tableButton.setText("");
            Integer[] tableIndex = {i % 5, i / 5};
            tableButtonMap.put(tableButton, tableIndex);
            labelToButtonMap.put(tableButton, tableLabel);
            pane.getChildren().addAll(tableButton, tableLabel);
            tableButton.setOnAction(new TableButtonHandler());
        }

        Button quitButton = new Button();
        quitButton.setText("QUIT");
        setWidgetPosition(quitButton, 100.0, 635.0);
        quitButton.setOnAction(new QuitButtonHandler());

        Button restartButton = new Button();
        restartButton.setText("RESTART");
        setWidgetPosition(restartButton, 350.0, 635.0);
        restartButton.setOnAction(new RestartButtonHandler());

        pane.getChildren().addAll(quitButton, restartButton, gameOverLabel);
    }

    /**
     * Sets a label based on the parameters
     * @param label a reference to the label that is going to be set
     * @param pos Pos object used for centering
     * @param width the specified width of the label
     * @param height the specified height of the label
     * @param xLayout the specified x layout of the label
     * @param yLayout the specified y layout of the label
     * @param fontSize the specified font size
     * @param visible the specified visibility status of the label
     * @param text the text that will be set on the label
     */
    private void setLabel(Label label, Pos pos, Double width, Double height, Double xLayout, Double yLayout, Double fontSize, Boolean visible, String text) {
        if(width != null && height != null) {
            label.setMinSize(width, height);
        }
        label.setText(text);
        setWidgetPosition(label, xLayout, yLayout);
        if(pos != null) {
            label.setAlignment(pos);
        }
        if(fontSize != null) {
            label.setFont(new Font(fontSize));
        }
        if(visible != null) {
            label.setVisible(visible);
        }
    }


    /**
     * Sets the position of a widget based on the parameters
     * @param widget a reference to the widget that is going to be positioned
     * @param xLayout the x position it will be moved to
     * @param yLayout the y positiong the widget will be moved to
     */
    private void setWidgetPosition(Control widget, double xLayout, double yLayout) {
        widget.setLayoutX(xLayout);
        widget.setLayoutY(yLayout);
    }


    /**
     * Gets a random button that has not been played yet in the game, only used in 1 player mode
     * @return The random button
     */
    private Button getRandomButton(){
        Random rand = new Random();
        Object[] buttons = tableButtonMap.keySet().toArray();
        Button randButton = (Button)buttons[rand.nextInt(buttons.length)];
        Integer[] index = tableButtonMap.get(randButton);
        if(((ComputerGameInterface)game).isMoveLegal(index[0], index[1]))
            return randButton;
        else
            return getRandomButton();
    }


    /**
     * Resets the GameBoard to its beginning state
     */
    public void reset(){
        game.startGame();
        currentTeamLabel.setText("X");
        List<Button> buttonList = new Vector<>(tableButtonMap.keySet());
        for(Button button : buttonList) {
            Label label = labelToButtonMap.get(button);
            label.setText("");
            label.setVisible(false);
        }
        gameOverLabel.setVisible(false);
    }


    /**
     * The handler for when any of the buttons in the TicTacToe grid are pressed
     */
    class TableButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            gameOverLabel.setVisible(false);
            if(! game.gameStarted()) {
                game.startGame();
            }
            Button button = null;
            try {
                button = (Button) e.getSource();
            } catch (Exception error) {
                System.exit(0);
            }
            String symbol = game.getCurrentSymbol();
            Label label = labelToButtonMap.get(button);
            Integer[] tableIndex = tableButtonMap.get(button);
            if(button == null) {
                System.out.println("Here");
            }
            if(game.makeTerm(tableIndex[0], tableIndex[1])) {
                label.setText(symbol);
                label.setVisible(true);
                if(game.isGameOver()) {
                    Player winner = game.getWinner();
                    if(winner != null) {
                        symbol = winner.getSymbol();
                        currentTeamLabel.setText(symbol + " Won!");
                    }
                    else {
                        currentTeamLabel.setText("Tied!");
                    }
                    gameOverLabel.setVisible(true);
                    return;
                }
                symbol = game.getCurrentSymbol();
                currentTeamLabel.setText(symbol.toUpperCase());
            }
            if(game instanceof ComputerGameInterface && ((ComputerGameInterface) game).getMoveNumber()%2==1){
                gameOverLabel.setVisible(true);
                Button computerMove = getRandomButton();
                Timeline timeline = new Timeline(new KeyFrame(
                        Duration.millis(1500),
                        ae -> computerMove.fire()));
                timeline.play();
            }
        }
    }


    /**
     * The handler for the Quit button
     */
    class QuitButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            pane.getScene().setRoot(menu.getPane());
        }
    }


    /**
     * The handler for the Restart button
     */
    class RestartButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            reset();
        }
    }
}
