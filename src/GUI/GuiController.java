package GUI;

import GAMES.ComputerGameInterface;
import GAMES.HumanGameInterface;
import javafx.application.Application;
import javafx.stage.Stage;

public class GuiController extends Application {


    /**
     * Constructor for the GuiController object
     */
    public GuiController(){}


    /**
     * Calls launch(args) which starts the JavaFX process
     * @param args - the user input passed in from the BeginGame class, unused
     */
    public void begin(String[] args){launch(args);}


    /**
     * Starts the GUI
     * @param stage the Stage where the scenes will be placed on
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Tic Tac Toe");
        Menu startMenu = new Menu();
        GameBoardPane onePlayerPane = new GameBoardPane(new ComputerGameInterface(), startMenu);
        GameBoardPane twoPlayerPane = new GameBoardPane(new HumanGameInterface(), startMenu);
        startMenu.addModes(onePlayerPane, twoPlayerPane);

        stage.setScene(startMenu.getScene());
        stage.show();
    }

}
