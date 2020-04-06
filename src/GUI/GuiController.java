package GUI;

import GAMES.ComputerGameInterface;
import GAMES.HumanGameInterface;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GuiController extends Application {

    public GuiController(){
    }

    public void begin(String[] args){launch(args);}

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Tic Tac Toe");
        Menu startMenu = new Menu();
        GameBoardPane onePlayerPane = new GameBoardPane(new ComputerGameInterface(), startMenu);
        GameBoardPane twoPlayerPane = new GameBoardPane(new HumanGameInterface(), startMenu);
        startMenu.addModes(onePlayerPane, twoPlayerPane);

        stage.setScene(startMenu.getScene());
        stage.show();

        //GameBoardPane gameBoardPane = new GameBoardPane(new HumanGameInterface());
        //stage.setScene(new Scene(gameBoardPane.getPane(), 500, 700));
        //stage.show();
    }

}
