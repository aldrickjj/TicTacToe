package GAMES;

public class Player {
    private String name = null;
    private String symbol = null;

    public Player(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return this.name;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public boolean equals(Player other) {
        return this.name.equals(other.name) && this.symbol.equals(other.symbol);
    }

}
