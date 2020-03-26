package GAMES;

public class HumanGameInterface implements GameInterface {
    private Player player1, player2;
    private String[][] board = null;

    public HumanGameInterface(int boardDimension) {
        this.player1 = createPlayer("Player 1", "X");
        this.player2 = createPlayer("Player 2", "O");
        this.board = new String[boardDimension][boardDimension];
    }

    @Override
    public Player createPlayer(String name, String symbol) {
        return new Player(name, symbol);
    }

    @Override
    public Player[] getPlayers() {
        Player[] playerList = {player1, player2};
        return playerList;
    }

    @Override
    public String[][] getBoard() {
        return this.board;
    }

    @Override
    public void clearBoard() {
        for(int i = 0; i < board.length; i += 1) {
            for(int j = 0; j < board[j].length; j += 1) {
                board[i][j] = " ";
            }
        }
    }
}
