package GAMES;

public interface GameInterface {
    /**
     * Creates a player
     * @param name The name of the player
     * @param symbol Either "X" or "O"
     * @return the created Player object
     */
    public Player createPlayer(String name, String symbol);

    /**
     * Get the current Player
     * @return the current team which is the Player
     */
    public Player getCurrentPlayer();

    /**
     * Getter for the winner
     * @return the winner field
     */
    public Player getWinner();

    /**
     * Getter for the currentSymbol
     * @return returns the currentSymbol based on whos turn it is, either "X" or "O"
     */
    public String getCurrentSymbol();

    /**
     * Makes the turn given a spot on the board
     * @param x the x position of the move
     * @param y the y position of the move
     * @return
     */
    public boolean makeTerm(int x, int y);

    /**
     * Checks if the game has started
     * @return true if the game has started, false if it hasn't
     */
    public boolean gameStarted();

    /**
     * Checks if the game is over, ie there is a winner
     * @return returns true if there is a winner, false if there isn't
     */
    public boolean isGameOver();

    /**
     * Clears the board of any moves
     */
    public void clearBoard();

    /**
     * Starts the game
     */
    public void startGame();
}
