package GAMES;

public class HumanGameInterface implements GameInterface {
    private Player player1, player2;
    private String[][] board = null;
    private Player term = null;
    private Player winner = null;
    private int moveNumber = 0;
    private boolean gameOver;

    public HumanGameInterface() {
        this.player1 = createPlayer("Player 1", "X");
        this.player2 = createPlayer("Player 2", "O");
        this.board = new String[5][5];
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
        String symbol = this.term.getSymbol();
        for(int i = 0; i < board.length; i += 1) {
            if(checkHorizontal(symbol, i) || checkVertical(symbol, i)) {
                return true;
            }
        }
        if(checkDiagonal(symbol, "right") || checkDiagonal(symbol, "left")) {
            return true;
        }
        return false;
    }

    private boolean checkHorizontal(String symbol, int index) {
        int num_symbol = 0;
        for(int i = 0; i < board[index].length; i += 1) {
            if(i >= 1 && !board[index][i].equals(symbol)) {
                return false;
            }
            if(board[index][i].equals(symbol)) {
                num_symbol += 1;
                if(num_symbol == 4) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonal(String symbol, String side) {
        if(side.equalsIgnoreCase("right")) {
            if(board[0][3].equals(symbol) && board[1][2].equals(symbol) && board[2][1].equals(symbol) && board[3][0].equals(symbol)) {
                return true;
            }
            else if (board[1][3].equals(symbol) && board[2][2].equals(symbol) && board[3][1].equals(symbol)) {
                if(board[0][4].equals(symbol) || board[4][0].equals(symbol)) {
                    return true;
                }
            }
            else if(board[1][4].equals(symbol) && board[2][3].equals(symbol) && board[3][2].equals(symbol) && board[4][1].equals(symbol)) {
                return true;
            }
        }
        else if(side.equalsIgnoreCase("left")) {
            if(board[0][1].equals(symbol) && board[1][2].equals(symbol) && board[2][3].equals(symbol) && board[3][4].equals(symbol)) {
                return true;
            }
            else if (board[1][1].equals(symbol) && board[2][2].equals(symbol) && board[3][3].equals(symbol)) {
                if(board[0][0].equals(symbol) || board[4][4].equals(symbol)) {
                    return true;
                }
            }
            else if(board[1][0].equals(symbol) && board[2][1].equals(symbol) && board[3][2].equals(symbol) && board[4][3].equals(symbol)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkVertical(String symbol, int index) {
        int num_symbol = 0;
        for(int i = 0; i < board.length; i += 1) {
            if(i >= 1 && !board[i][index].equals(symbol)) {
                return false;
            }
            if(board[i][index].equals(symbol)) {
                num_symbol += 1;
                if(num_symbol == 4) {
                    return true;
                }
            }
        }
        return false;
    }

    private void changeTerm() {
        if(moveNumber % 2 == 0) {
            term = player1;
        }
        else {
            term = player2;
        }
    }

    @Override
    public void startGame() {
        this.term = player1;
        clearBoard();
        while (!gameOver) {
            changeTerm();
            moveNumber += 1;
            int x = 0;//to be changed
            int y = 0;//to be changed
            while(!makeTerm(x, y)) {
                //Do something here
            }
            gameOver = isGameOver();
        }
        winner = this.winner;
    }

    @Override
    public Player getWinner() {
        return this.winner;
    }
}
