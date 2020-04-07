package GAMES;

public class HumanGameInterface implements GameInterface {
    private Player player1, player2;
    private String[][] board = null;
    private Player team = null;
    private Player winner = null;
    private int moveNumber = 0;
    private boolean gameOver;
    private boolean gameStarted;

    /**
     * Constructor for the HumanGameInterface
     */
    public HumanGameInterface() {
        this.player1 = createPlayer("Player 1", "X");
        this.player2 = createPlayer("Player 2", "O");
        this.board = new String[5][5];
        this.gameOver = false;
    }

    /**
     * Creates a player
     * @param name The name of the player
     * @param symbol Either "X" or "O"
     * @return the created Player object
     */
    @Override
    public Player createPlayer(String name, String symbol) {
        return new Player(name, symbol);
    }


    /**
     * Clears the board of any moves
     */
    @Override
    public void clearBoard() {
        for(int i = 0; i < board.length; i += 1) {
            for(int j = 0; j < board[i].length; j += 1) {
                board[i][j] = " ";
            }
        }
    }


    /**
     * Get the current Player
     * @return the current team which is the Player
     */
    @Override
    public Player getCurrentPlayer() {
        return this.team;
    }


    /**
     * Checks the legality of a move given an X and Y coordinate on the board
     * @param x the x position of the spot
     * @param y the y position of the spot
     * @return True if the move is legal, false if it not legal
     */
    public boolean isMoveLegal(int x, int y) {
        return this.board[y][x].equals(" ");
    }


    /**
     * Makes the turn given a spot on the board
     * @param x the x position of the move
     * @param y the y position of the move
     * @return
     */
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


    /**
     * Getter for moveNumber
     * @return the moveNumber
     */
    public int getMoveNumber(){return this.moveNumber;}


    /**
     * Getter for the currentSymbol
     * @return returns the currentSymbol based on whos turn it is, either "X" or "O"
     */
    @Override
    public String getCurrentSymbol() {return team.getSymbol();}


    /**
     * Checks if the game is over, ie there is a winner
     * @return returns true if there is a winner, false if there isn't
     */
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


    /**
     * Checks if the game has started
     * @return true if the game has started, false if it hasn't
     */
    @Override
    public boolean gameStarted() {
        return this.gameStarted;
    }


    /**
     * Checks if there is 4 in a row on any horizontal
     * @param symbol Either "X" or "O"
     * @param index the x position on the board
     * @return return false if there is not 4 in a row in any horizontal, true otherwise
     */
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


    /**
     * Checks the diagonals to see if there is 4 in a row on them
     * @param symbol Either "X" or "O"
     * @param side which diagonal to check
     * @return true if there is 4 in a row on the specified diagonal, false if not
     */
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


    /**
     * Checks vertical columns to see if there are 4 in a row of any symbol there
     * @param symbol Either "X" or "O"
     * @param index Which column to check
     * @return true if there is 4 in a row of a symbol there, false if there isn't
     */
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


    /**
     * Changes the team and updates the moveNumber variable and the team variable
     */
    private void changeTeam() {
        if(moveNumber % 2 == 0) {
            team = player1;
        }
        else {
            team = player2;
        }
    }


    /**
     * Starts the game
     */
    @Override
    public void startGame() {
        this.moveNumber = 0;
        this.gameStarted = true;
        this.team = player1;
        this.winner = null;
        clearBoard();
    }


    /**
     * Getter for the winner
     * @return the winner field
     */
    @Override
    public Player getWinner() {
        return this.winner;
    }
}
