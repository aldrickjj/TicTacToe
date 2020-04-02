package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.util.*;
import GAMES.*;

import javax.swing.*;

public class GameBoard extends Application{

    public static void main(String[] args) {launch(args);}

    @FXML
    Label termLabel;

    @FXML
    Button tableButton1, tableButton2, tableButton3, tableButton4, tableButton5,
            tableButton6, tableButton7, tableButton8, tableButton9, tableButton10,
            tableButton11, tableButton12, tableButton13, tableButton14, tableButton15,
            tableButton16, tableButton17, tableButton18, tableButton19, tableButton20,
            tableButton21, tableButton22, tableButton23, tableButton24, tableButton25;

    Button[] tableButtonArray = {tableButton1, tableButton2, tableButton3, tableButton4, tableButton5,
            tableButton6, tableButton7, tableButton8, tableButton9, tableButton10,
            tableButton11, tableButton12, tableButton13, tableButton14, tableButton15,
            tableButton16, tableButton17, tableButton18, tableButton19, tableButton20,
            tableButton21, tableButton22, tableButton23, tableButton24, tableButton25};

    @FXML
    Button quitButton, restartButton;

    @FXML
    Label tableLabel1, tableLabel2, tableLabel3, tableLabel4, tableLabel5,
            tableLabel6, tableLabel7, tableLabel8, tableLabel9, tableLabel10,
            tableLabel11, tableLabel12, tableLabel13, tableLabel14, tableLabel15,
            tableLabel16, tableLabel17, tableLabel18, tableLabel19, tableLabel20,
            tableLabel21, tableLabel22, tableLabel23, tableLabel24, tableLabel25;

    Label[] tableLabelArray = {tableLabel1, tableLabel2, tableLabel3, tableLabel4, tableLabel5,
            tableLabel6, tableLabel7, tableLabel8, tableLabel9, tableLabel10,
            tableLabel11, tableLabel12, tableLabel13, tableLabel14, tableLabel15,
            tableLabel16, tableLabel17, tableLabel18, tableLabel19, tableLabel20,
            tableLabel21, tableLabel22, tableLabel23, tableLabel24, tableLabel25};

    HashMap<Button, Integer[]> tableButtonMap = new HashMap<>();
    HashMap<Button, Label> labelToButtonMap = new HashMap<>();

    GameInterface game = new HumanGameInterface();

    @Override
    public void start(Stage stage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("GameBoard.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Tic Tac Toe!");

        for(int i = 0; i < tableButtonArray.length; i += 1) {
            Integer[] tableIndex = {i % 5, i / 5};
            tableButtonMap.put(tableButtonArray[i], tableIndex);
            labelToButtonMap.put(tableButtonArray[i], tableLabelArray[i]);
        }

        for(int i = 0; i < tableButtonArray.length; i += 1) {
            tableButtonArray[i].setOnAction(new TableButtonHandler());
        }

        stage.show();
    }

    class TableButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            Button button = null;
            try {
                button = (Button) e.getSource();
            } catch (Exception error) {
                System.exit(0);
            }
            String symbol = game.getCurrentSymbol();
            Label label = labelToButtonMap.get(button);
            Integer[] tableIndex = tableButtonMap.get(button);
            if(game.makeTerm(tableIndex[0], tableIndex[2])) {
                label.setText(symbol);
            }

            if(game.isGameOver()) {
                System.exit(0);
            }
        }
    }

}
