package labor2_ISP;

import java.util.Random;
import java.util.Scanner;

public class Exercise7 {
    public static void main(String[] args) {
        Random random = new Random();
        int secretNumber = random.nextInt(100) + 1;

        Scanner scanner = new Scanner(System.in);

        int guessedNumber = -1;
        int previousGuessedNumber;
        int guessTries = 0;

        do {
            previousGuessedNumber = guessedNumber;
            System.out.print("Guess a number: ");
            guessedNumber = scanner.nextInt();
            if (previousGuessedNumber != guessedNumber) guessTries++;

            if (guessedNumber < secretNumber) {
                System.out.println("The guessed number is to small! Please try again!");
            } else if (guessedNumber > secretNumber) {
                System.out.println("The guessed number is to large! Please try again!");
            }
        } while (guessedNumber != secretNumber);

        System.out.println("Congratulation you guessed the secret number! It is " + secretNumber + ". You did with " + guessTries + " tries.");
    }
}
