import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;

        while (playAgain) {
            int numberToGuess = random.nextInt(100) + 1;
            int numberOfAttempts = 10;  // Limit the number of attempts
            int attemptsMade = 0;
            boolean hasGuessedCorrectly = false;

            System.out.println("I have generated a number between 1 and 100. Try to guess it!");
            System.out.println("You have " + numberOfAttempts + " attempts to guess the number.");

            while (attemptsMade < numberOfAttempts && !hasGuessedCorrectly) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attemptsMade++;

                if (userGuess < numberToGuess) {
                    System.out.println("Too low! Try again.");
                } else if (userGuess > numberToGuess) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Congratulations! You've guessed the number.");
                    hasGuessedCorrectly = true;
                }

                System.out.println("Attempts remaining: " + (numberOfAttempts - attemptsMade));
            }

            if (!hasGuessedCorrectly) {
                System.out.println("Sorry, you've used all your attempts. The number was " + numberToGuess + ".");
            }

            System.out.print("Would you like to play again? (yes/no): ");
            String userResponse = scanner.next();
            playAgain = userResponse.equalsIgnoreCase("yes");
        }

        System.out.println("Thanks for playing! Goodbye.");
        scanner.close();
    }
}
