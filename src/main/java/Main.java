
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class Main {
    public static void main(String[] args) {
        Rps rps = Rps.getInstance();
        Set<String> uniqueArgs = new HashSet<>();
        uniqueArgs.addAll(Arrays.asList(args));

        if (args.length % 2 != 0 && args.length == uniqueArgs.size() && args.length > 1) {
            rps.createNumbersOfSteps(args);
            ConsoleMenu menu = new ConsoleMenu();
            menu.startGame();

        } else {
            System.out.println("Error!");
        }
    }
}








