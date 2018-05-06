import java.util.Random;

public class Die {
    private static final int MAX = 6;
    private static final int MIN = 1;

    public Die() {};

    public static int possibleResult() {
        Random random = new Random();
        return random.nextInt(MAX) + MIN;
    }
}