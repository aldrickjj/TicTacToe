package GUI;

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
import javafx.stage.Stage;
import javafx.scene.shape.*;

import javax.sound.sampled.Control;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import java.util.*;

public class LaunchBoard extends Application {

    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage stage) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("GameBoard.fxml"));
        stage.setTitle("Tic Tac Toe!");
        Pane pane = new Pane();
        pane.prefHeight(700.0);
        pane.prefWidth(500.0);
        List<Line> horizontalLines = new Vector<>();
        List<Line> verticalLines = new Vector<>();
        for(int i = 0; i < 5; i += 1) {

        }
        stage.setScene(new Scene(pane));

        stage.show();
    }
}
