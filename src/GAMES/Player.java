package GAMES;

public class Player {
    private String name = null;
    private String symbol = null;


    /**
     * The constructor for the Player object
     * @param name The name of a Player
     * @param symbol Whether they will be using "X" or "O" to represent their moves
     */
    public Player(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    /**
     * Get the symbol of a player
     * @return the Symbol of the player
     */
    public String getSymbol() {
        return this.symbol;
    }

}
