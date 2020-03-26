package GUI;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Game {
    private Label placeholder;
    private Label turn;
    private Button quit;
    private Button restart;
    private Scene scene;
    private boolean reset;
    private boolean exit;

    public Game(){
        GridPane grid = new GridPane();
        int BUTTON_PADDING = 5;
        grid.setPadding(new Insets(BUTTON_PADDING));
        grid.setHgap(BUTTON_PADDING);
        grid.setVgap(BUTTON_PADDING);

        //labels
        Label currentTurn = new Label("Current Turn: ");
        turn = new Label("X");
        placeholder = new Label("");
        placeholder.setVisible(false);
        grid.add(currentTurn,7,1);
        grid.add(turn,8, 1);
        grid.add(placeholder,7,5);

        // grid part
        for(int i = 1; i < 6; i++){
            for(int j = 0; j < 5; j++){
                Button button = new Button("  ");
                button.setOnAction(new gridButtonPressed());
                grid.add(button, j, i);
            }
        }

        //restart and quiting buttons
        restart = new Button("Restart");
        restart.setOnAction(new restartHandler());
        quit = new Button("Quit");
        quit.setOnAction(new quitHandler());
        grid.add(restart, 7,3);
        grid.add(quit, 7, 4);

        scene = new Scene(grid, 800, 600);
    }

    public Scene getScene(){
        return this.scene;
    }

    public boolean isReset(){
        return this.reset;
    }

    public boolean isExit(){
        return this.exit;
    }

    class restartHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            placeholder.setVisible(true);
            placeholder.setTextFill(Color.RED);
            placeholder.setText("*Restart*");
        }
    }

    class quitHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            placeholder.setVisible(true);
            placeholder.setTextFill(Color.RED);
            placeholder.setText("*Exit to menu*");
        }
    }

    class gridButtonPressed implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            placeholder.setVisible(true);
            placeholder.setTextFill(Color.RED);
            placeholder.setText("*Update board*");
        }
    }
}
