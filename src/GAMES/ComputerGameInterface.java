package GAMES;

import java.util.Random;

public class ComputerGameInterface implements GameInterface {
    private Player player1, computer;
    private String[][] board = null;
    private Player team = null;
    private Player winner = null;
    private int moveNumber = 0;
    private boolean gameOver;
    private boolean gameStarted;

    public ComputerGameInterface() {
        this.player1 = createPlayer("Player 1", "X");
        this.computer = createPlayer("Computer", "O");
        this.board = new String[5][5];
        this.gameOver = false;
    }

    @Override
    public Player createPlayer(String name, String symbol) {
        return new Player(name, symbol);
    }

    @Override
    public void clearBoard() {
        for(int i = 0; i < board.length; i += 1) {
            for(int j = 0; j < board[i].length; j += 1) {
                board[i][j] = " ";
            }
        }
    }

    @Override
    public Player getCurrentPlayer() {
        return this.team;
    }

    public boolean isMoveLegal(int x, int y) {
        return this.board[y][x].equals(" ");
    }

    @Override
    public boolean makeTerm(int x, int y) {
        String symbol = this.team.getSymbol();
        if(! isMoveLegal(x, y)){
            return false;
        }
        this.board[y][x] = symbol;
        this.moveNumber += 1;
        if(isGameOver()) {
            if(moveNumber != 25)
                this.winner = this.team;
            return true;
        }
        changeTeam();
        return true;
    }

    public int getMoveNumber(){return this.moveNumber;}

    @Override
    public String getCurrentSymbol() {
        return team.getSymbol();
    }

    @Override
    public boolean isGameOver() {
        if(moveNumber == 25) {
            return true;
        }
        String symbol = this.team.getSymbol();
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

    @Override
    public boolean gameStarted() {
        return this.gameStarted;
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

    private void changeTeam() {
        if(moveNumber % 2 == 0) {
            team = player1;
        }
        else {
            team = computer;
        }
    }

    @Override
    public void startGame() {
        this.moveNumber = 0;
        this.gameStarted = true;
        this.team = player1;
        this.winner = null;
        clearBoard();
    }

    @Override
    public Player getWinner() {
        return this.winner;
    }
}
