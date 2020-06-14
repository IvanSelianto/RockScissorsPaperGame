
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Rps {
    private final Random RANDOM = new Random();
    private static Rps instance;
    private Map<Integer, String> numbersToSteps = new HashMap<>();
    private int[] stepsNumbers;

    public Map<Integer, String> getNumbersToSteps() {
        return numbersToSteps;
    }

    private Rps() {

    }

    public static Rps getInstance() {
        if (instance == null) {
            instance = new Rps();
        }
        return instance;
    }

    public void userStep(int stepNumber) {
        for (int i = 0; i < stepsNumbers.length; i++) {
            if (Arrays.stream(stepsNumbers).boxed().collect(Collectors.toList()).indexOf(stepNumber) == stepsNumbers.length / 2) {
                break;
            }
            rotateInline();
        }

    }

    public void determineTheWinner(int computerStep) {
        if (Arrays.asList(
                convertIntArrToIntegerArray(
                        Arrays.copyOfRange(stepsNumbers, 0, stepsNumbers.length / 2))).contains(computerStep)) {
            System.out.println("You win!");
        } else if (Arrays.asList(
                convertIntArrToIntegerArray(
                        Arrays.copyOfRange(stepsNumbers, (stepsNumbers.length / 2) + 1, stepsNumbers.length))).contains(computerStep)) {
            System.out.println("Computer win!");

        } else {
            System.out.println("Draw!");
        }

    }

    public int generateComputerStep() {
        return RANDOM.nextInt(stepsNumbers.length) + 1;
    }

    private Integer[] convertIntArrToIntegerArray(int[] array) {
        return IntStream.of(array)
                .boxed()
                .toArray(Integer[]::new);
    }


    private void swap(int first, int second) {
        int temp = stepsNumbers[second];
        stepsNumbers[second] = stepsNumbers[first];
        stepsNumbers[first] = temp;
    }

    private void rotateInline() {
        reverse(0, 1);
        reverse(2, stepsNumbers.length - 1);
        reverse(0, stepsNumbers.length - 1);
    }

    private void reverse(int start, int end) {
        for (int i = start; i <= (start + end) / 2; i++) {
            swap(i, start + end - i);
        }
    }

    public void createNumbersOfSteps(String[] steps) {
        for (int i = 0; i < steps.length; i++) {
            numbersToSteps.put(i + 1, steps[i]);
        }
        stepsNumbers = Arrays.stream(numbersToSteps.keySet().toArray(new Integer[0]))
                .mapToInt(number -> Integer.parseInt(number.toString()))
                .toArray();
    }

}
