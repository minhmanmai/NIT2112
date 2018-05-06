public class Player {
    private String name;
    private int[] dieResults;

    public Player(String name) {
        this.dieResults = new int[] { 0, 0, 0, 0, 0, 0 };
        this.name = name;
    }

    public String getPlayerName() {
        return this.name;
    }

    public int roll() {
        return Die.possibleResult();
    }
}