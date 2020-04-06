package GUI;

import GAMES.HumanGameInterface;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GuiController extends Application {
    //private GameBoardPane gameBoardPane = new GameBoardPane(new HumanGameInterface());

    public GuiController(){

    }

    public void begin(String[] args){launch(args);}

    @Override
    public void start(Stage stage) throws Exception {
        /*
        Menu startMenu = new Menu();
        Game gameGUI = new Game();
        Scene startScene = gameGUI.getScene();
        stage.setScene(startScene);
        stage.show();*/
        stage.setTitle("Tic Tac Toe");
        GameBoardPane gameBoardPane = new GameBoardPane(new HumanGameInterface());
        stage.setScene(new Scene(gameBoardPane.getPane(), 500, 700));
        stage.show();
    }

}
