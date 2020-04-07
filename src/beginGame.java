import GUI.GuiController;

public class beginGame {

    /**
     * Starts the game
     * @param args command line inputs - not used in this game
     */
    public static void main(String[] args) {
        GuiController gui = new GuiController();
        gui.begin(args);
    }
}
