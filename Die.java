import java.util.Random;

public class Die {
    private int faceValue;
    private final int MAX = 6;
    private final int MIN = 1;
    Random random = new Random();

    public Die() {};

    public int roll() {
        return random.nextInt(MAX) + MIN;
    }
}