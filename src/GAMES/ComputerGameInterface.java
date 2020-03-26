package GAMES;

public class ComputerGameInterface implements GameInterface {
    private Player player, pc;
    private String[][] board = null;

    public ComputerGameInterface(int boardDimension) {
        this.player = createPlayer("Player", "X");
        this.pc = createPlayer("Computer", "O");
        this.board = new String[boardDimension][boardDimension];
    }

    @Override
    public Player createPlayer(String name, String symbol) {
        return new Player(name, symbol);
    }

    @Override
    public Player[] getPlayers() {
        Player[] playerList = {player, pc};
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
