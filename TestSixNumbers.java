import java.util.Scanner;

public class TestSixNumbers {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Before we start. What is your name?: ");
        String playerName = scan.nextLine();

        Player player1 = new Player(playerName);

        System.out.print(player1.roll());
        System.out.print(player1.getPlayerName());
    }
}