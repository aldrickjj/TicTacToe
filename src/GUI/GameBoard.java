package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.awt.*;

public class GameBoard extends Application{

    public static void main(String[] args) {launch(args);}

    @FXML
    Label termLabel;

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GameBoard.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Tic Tac Toe!");
        stage.show();
    }
}
