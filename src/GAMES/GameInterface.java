package GAMES;

public interface GameInterface {
    public Player createPlayer(String name, String symbol);
    public Player[] getPlayers();
    public String[][] getBoard();
    public void clearBoard();
    public void startGame();
}
