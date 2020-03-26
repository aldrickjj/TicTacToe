package GAMES;

public class HumanGameInterface implements GameInterface {
    private Player player1, player2;
    private String[][] board = null;
    private Player term = null;
    private Player winner = null;
    private boolean gameOver;

    public HumanGameInterface(int boardDimension) {
        this.player1 = createPlayer("Player 1", "X");
        this.player2 = createPlayer("Player 2", "O");
        this.board = new String[boardDimension][boardDimension];
        this.gameOver = false;
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

    private boolean isMoveLegal(int x, int y) {
        return this.board[y][x].equals(" ");
    }

    public boolean makeTerm(int x, int y) {
        String symbol = this.term.getSymbol();
        if(isMoveLegal(x, y)){
            return false;
        }
        this.board[y][x] = symbol;
        return true;
    }

    private boolean isGameOver() {
        
        return false;
    }

    @Override
    public void startGame() {
        this.term = player1;
        clearBoard();
    }
}
