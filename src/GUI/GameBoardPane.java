package GUI;

import GAMES.GameInterface;
import GAMES.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.util.*;

public class GameBoardPane {

    private Pane pane;
    private HashMap<Button, Integer[]> tableButtonMap = new HashMap<>();
    private HashMap<Button, Label> labelToButtonMap = new HashMap<>();
    private GameInterface game;

    private Label gameOverLabel = new Label();
    private Label currentTeamLabel;

    public GameBoardPane(GameInterface game) {
        this.game = game;
        setUpPane();
    }

    public Pane getPane() {
        return this.pane;
    }

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

    private void setWidgetPosition(Control widget, double xLayout, double yLayout) {
        widget.setLayoutX(xLayout);
        widget.setLayoutY(yLayout);
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
            currentTeamLabel.setText("X");
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
