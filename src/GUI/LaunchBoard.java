package GUI;

import GAMES.GameInterface;
import GAMES.HumanGameInterface;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.shape.*;

import javax.sound.sampled.Control;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import java.util.*;

public class LaunchBoard extends Application {
    HashMap<Button, Integer[]> tableButtonMap = new HashMap<>();
    HashMap<Button, Label> labelToButtonMap = new HashMap<>();
    GameInterface game = new HumanGameInterface();

    Button tableButton1, tableButton2, tableButton3, tableButton4, tableButton5,
            tableButton6, tableButton7, tableButton8, tableButton9, tableButton10,
            tableButton11, tableButton12, tableButton13, tableButton14, tableButton15,
            tableButton16, tableButton17, tableButton18, tableButton19, tableButton20,
            tableButton21, tableButton22, tableButton23, tableButton24, tableButton25;

    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage stage) throws IOException {

        Button[] tableButtonArray = {tableButton1, tableButton2, tableButton3, tableButton4, tableButton5,
                tableButton6, tableButton7, tableButton8, tableButton9, tableButton10,
                tableButton11, tableButton12, tableButton13, tableButton14, tableButton15,
                tableButton16, tableButton17, tableButton18, tableButton19, tableButton20,
                tableButton21, tableButton22, tableButton23, tableButton24, tableButton25};

        stage.setTitle("Tic Tac Toe!");
        Pane pane = new Pane();

        Label headerLabel = new Label();
        headerLabel.setFont(new Font(24));
        headerLabel.setLayoutX(98.0);
        headerLabel.setLayoutY(15.0);
        headerLabel.setText("Current Term: ");

        Label currentTermLabel = new Label();
        currentTermLabel.setFont(new Font(24));
        currentTermLabel.setLayoutY(15.0);
        currentTermLabel.setLayoutX(250.0);

        pane.getChildren().addAll(headerLabel, currentTermLabel);

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

        for(int i = 0; i < tableButtonArray.length; i += 1) {
            Label tableLabel = new Label();
            tableLabel.setLayoutX((i / 5) * 100.0 + 25.0);
            tableLabel.setLayoutY((i % 5) * 100.0 + 100.0);
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

        Button restartButton = new Button();
        restartButton.setText("RESTART");
        restartButton.setLayoutX(350.0);
        restartButton.setLayoutY(635.0);

        pane.getChildren().addAll(quitButton, restartButton);

        stage.setScene(new Scene(pane, 500, 700));

        stage.show();
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
            }

            if(game.isGameOver()) {
                System.exit(0);
            }
        }
    }
}
