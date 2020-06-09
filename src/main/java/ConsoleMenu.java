
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleMenu {
    private Rps rps = Rps.getInstance();
    private RandomKey randomKey = new RandomKey();
    private Scanner scanner = new Scanner(System.in);
    private final int EXIT = 0;

    public void startGame() {
        int computerStep = rps.generateComputerStep();
        String hmacKey = randomKey.generateRandomKey();
        System.out.printf("HMAC: %s%n", new HmacSha.Builder()
                .setKey(hmacKey)
                .setValue(String.valueOf(computerStep))
                .setShaType("HmacSHA256").build()
                .generateHmacSha());
        formAvailableMoves();
        String userMove = scanner.next();
        while (!validate(userMove)) {
            System.out.println("Invalid menu item!");
            formAvailableMoves();
            userMove = scanner.next();
        }
        int checkedUserMove = Integer.parseInt(userMove);
        if (checkedUserMove != EXIT) {
            rps.userStep(checkedUserMove);
            System.out.println("Your move: " + rps.getNumbersToSteps().get(checkedUserMove));
            System.out.print("Computer move: ");
            System.out.println(rps.getNumbersToSteps().get(computerStep));
            rps.determineTheWinner(computerStep);
            System.out.println("HMAC key: " + hmacKey);
        }

    }

    private void formAvailableMoves() {
        System.out.println("Available moves:");
        for (int i = 1; i < rps.getNumbersToSteps().size() + 1; i++) {
            System.out.printf("%d - %s%n", i, rps.getNumbersToSteps().get(i));
        }
        System.out.println("0 - exit");
        System.out.print("Enter your move: ");
    }

    private boolean validate(String number) {
        final String givenString = "[0-" + rps.getNumbersToSteps().size() + "]";
        Pattern pattern = Pattern.compile(givenString);
        Matcher matcher;
        matcher = pattern.matcher(number);
        return matcher.matches();
    }
}
