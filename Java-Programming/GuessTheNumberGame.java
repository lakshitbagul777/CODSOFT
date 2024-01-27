import java.util.Random;
import java.util.Scanner;

public class GuessTheNumberGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int lowerBound = 1;
        int upperBound = 100;
        int maxAttempts = 5;
        int score = 0;

        System.out.println("Welcome to the Guess the Number Game!");
        System.out.println("You need to guess a number between " + lowerBound + " and " + upperBound);

        boolean playAgain = true;

        while (playAgain) {
            int generatedNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int attempts = 0;
            boolean correctGuess = false;

            System.out.println("\nNew round! Try to guess the number.");

            while (attempts < maxAttempts && !correctGuess) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == generatedNumber) {
                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                    correctGuess = true;
                    score++;
                } else if (userGuess < generatedNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }

            if (!correctGuess) {
                System.out.println("Sorry, you've run out of attempts. The correct number was: " + generatedNumber);
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainInput = scanner.next().toLowerCase();
            playAgain = playAgainInput.equals("yes");

            if (playAgain) {
                System.out.println("Your current score: " + score);
            }
        }

        System.out.println("Thanks for playing! Your final score: " + score);
        scanner.close();
    }
}
