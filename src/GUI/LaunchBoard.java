package GUI;

import GAMES.HumanGameInterface;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LaunchBoard extends Application {

    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage stage){
        stage.setTitle("Tic Tac Toe");
        GameBoardPane gameBoardPane = new GameBoardPane(new HumanGameInterface());
        stage.setScene(new Scene(gameBoardPane.getPane(), 500, 700));
        stage.show();
    }
}
