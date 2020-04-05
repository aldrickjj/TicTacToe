package GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GuiController extends Application {

    public GuiController(){

    }

    public void begin(String[] args){launch(args);}

    @Override
    public void start(Stage window) throws Exception {
        Menu startMenu = new Menu();
        Game gameGUI = new Game();
        Scene startScene = gameGUI.getScene();
        window.setScene(startScene);
        window.show();
    }

}
