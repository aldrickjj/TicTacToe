package GAMES;

public interface GameInterface {
    public Player createPlayer(String name, String symbol);
    public Player getCurrentPlayer();
    public Player[] getPlayers();
    public String[][] getBoard();
    public Player getWinner();
    public String getCurrentSymbol();
    public boolean makeTerm(int x, int y);
    public boolean gameStarted();
    public boolean isGameOver();
    public void clearBoard();
    public void startGame();
}
