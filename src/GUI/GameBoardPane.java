package GUI;

import GAMES.GameInterface;
import GAMES.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.shape.*;

import java.util.*;

public class GameBoardPane {

    private Pane pane;
    private HashMap<Button, Integer[]> tableButtonMap = new HashMap<>();
    private HashMap<Button, Label> labelToButtonMap = new HashMap<>();
    private GameInterface game;

    private Label gameOverLabel = new Label();
    private Label currentTermLabel;

    private Button tableButton1, tableButton2, tableButton3, tableButton4, tableButton5,
            tableButton6, tableButton7, tableButton8, tableButton9, tableButton10,
            tableButton11, tableButton12, tableButton13, tableButton14, tableButton15,
            tableButton16, tableButton17, tableButton18, tableButton19, tableButton20,
            tableButton21, tableButton22, tableButton23, tableButton24, tableButton25;

    public GameBoardPane(GameInterface game) {
        this.game = game;
        setUpPane();
    }

    public Pane getPane() {
        return this.pane;
    }

    private void setUpPane() {
        gameOverLabel.setMinSize(500.0, 500.0);
        gameOverLabel.setAlignment(Pos.CENTER);
        gameOverLabel.setFont(new Font(100));
        gameOverLabel.setLayoutX(0.0);
        gameOverLabel.setLayoutY(100.0);
        gameOverLabel.setText("");
        gameOverLabel.setVisible(false);

        Button[] tableButtonArray = {tableButton1, tableButton2, tableButton3, tableButton4, tableButton5,
                tableButton6, tableButton7, tableButton8, tableButton9, tableButton10,
                tableButton11, tableButton12, tableButton13, tableButton14, tableButton15,
                tableButton16, tableButton17, tableButton18, tableButton19, tableButton20,
                tableButton21, tableButton22, tableButton23, tableButton24, tableButton25};

        this.pane = new Pane();

        Label headerLabel = new Label();
        headerLabel.setFont(new Font(24));
        headerLabel.setLayoutX(98.0);
        headerLabel.setLayoutY(15.0);
        headerLabel.setText("Current Term: ");

        currentTermLabel = new Label();
        currentTermLabel.setFont(new Font(24));
        currentTermLabel.setLayoutY(15.0);
        currentTermLabel.setLayoutX(250.0);
        currentTermLabel.setText("X");

        pane.getChildren().addAll(headerLabel, currentTermLabel);

        for(int i = 0; i < tableButtonArray.length; i += 1) {
            Label tableLabel = new Label();
            tableLabel.setMinSize(100.0, 100.0);
            tableLabel.setLayoutX((i / 5) * 100.0);
            tableLabel.setLayoutY((i % 5) * 100.0 + 100.0);
            tableLabel.setAlignment(Pos.CENTER);
            tableLabel.setFont(new Font(72));
            tableLabel.setText("");
            tableLabel.setVisible(false);
            tableButtonArray[i] = new Button();
            tableButtonArray[i].setMinSize(100.0, 100.0);
            tableButtonArray[i].setLayoutX((i / 5) * 100.0);
            tableButtonArray[i].setLayoutY((i % 5) * 100.0 + 100.0);
            tableButtonArray[i].setText("");
            Integer[] tableIndex = {i % 5, i / 5};
            tableButtonMap.put(tableButtonArray[i], tableIndex);
            labelToButtonMap.put(tableButtonArray[i], tableLabel);
            pane.getChildren().addAll(tableButtonArray[i], tableLabel);
            tableButtonArray[i].setOnAction(new TableButtonHandler());
        }

        Button quitButton = new Button();
        quitButton.setText("QUIT");
        quitButton.setLayoutX(100.0);
        quitButton.setLayoutY(635.0);
        quitButton.setOnAction(new QuitButtonHandler());

        Button restartButton = new Button();
        restartButton.setText("RESTART");
        restartButton.setLayoutX(350.0);
        restartButton.setLayoutY(635.0);
        restartButton.setOnAction(new RestartButtonHandler());

        pane.getChildren().addAll(quitButton, restartButton, gameOverLabel);
    }

    private void createTable() {
        for(int i = 0; i < 5; i += 1) {
            Line horizontalLine = new Line();
            Line verticalLine = new Line();
            horizontalLine.setEndX(500.0);
            horizontalLine.setStartX(0.0);
            horizontalLine.setLayoutX(0.0);
            horizontalLine.setLayoutY(100.0 + (100.0 * i));
            verticalLine.setEndY(500.0);
            verticalLine.setStartY(0.0);
            verticalLine.setLayoutY(100.0);
            verticalLine.setLayoutX(100.0 * i);
            pane.getChildren().addAll(horizontalLine, verticalLine);
        }
    }

    class TableButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
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
                        currentTermLabel.setText(symbol + " Won!");
                    }
                    else {
                        currentTermLabel.setText("Tied!");
                    }
                    gameOverLabel.setVisible(true);
                    return;
                }
                symbol = game.getCurrentSymbol();
                currentTermLabel.setText(symbol.toUpperCase());
            }
        }
    }

    class QuitButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            System.exit(0);
        }
    }

    class RestartButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            game.startGame();
            currentTermLabel.setText("X");
            List<Button> buttonList = new Vector<>(tableButtonMap.keySet());
            for(Button button : buttonList) {
                Label label = labelToButtonMap.get(button);
                label.setText("");
                label.setVisible(false);
            }
            gameOverLabel.setVisible(false);
        }
    }
}
