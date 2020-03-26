package GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GuiController extends Application {

    @Override
    public void start(Stage window) throws Exception {
        Start startMenu = new Start();
        Game gameGUI = new Game();
        Scene startScene = gameGUI.getScene();
        window.setScene(startScene);
        window.show();
    }

}
