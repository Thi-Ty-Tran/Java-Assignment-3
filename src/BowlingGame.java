/*
 * Name: Thi Ty Tran
 * Date: July 1st, 2024
 * File: BowlingGame
 * Description: This program implements a scoring system for the game of bowling.
 */

import java.util.Scanner;

public class BowlingGame {

    private static final int NUM_FRAMES = 10;
    private static final int MAX_PINS = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get player names
        System.out.print("Enter Player 1 name: ");
        String player1Name = scanner.nextLine();
        System.out.print("Enter Player 2 name: ");
        String player2Name = scanner.nextLine();

        // Initialize player scores
        int[] player1Scores = new int[NUM_FRAMES];
        int[] player2Scores = new int[NUM_FRAMES];

        // Play the game
        boolean playAgain = true;
        while (playAgain) {
            System.out.println("\nNew Game:");
            playGame(player1Name, player1Scores, player2Name, player2Scores, scanner);

            // Ask if they want to play again
            System.out.print("Would you like to play again? (yes/no) ");
            String response = scanner.nextLine().toLowerCase();
            playAgain = response.equals("yes");
        }

        System.out.println("\nThank you for playing!");
        scanner.close();
    }

    private static void playGame(String player1Name, int[] player1Scores, String player2Name, int[] player2Scores, Scanner scanner) {
        for (int frame = 0; frame < player1Scores.length; frame++) {
            System.out.printf("%s's turn:%n", player1Name);
            player1Scores[frame] = getTurnScores(player1Name, scanner);
            System.out.printf("%s's turn:%n", player2Name);
            player2Scores[frame] = getTurnScores(player2Name, scanner);
        }

        // Determine the winner
        int player1Total = getGameScores(player1Scores);
        int player2Total = getGameScores(player2Scores);

        if (player1Total > player2Total) {
            System.out.printf("%s wins with a score of %d!%n", player1Name, player1Total);
        } else if (player2Total > player1Total) {
            System.out.printf("%s wins with a score of %d!%n", player2Name, player2Total);
        } else {
            System.out.println("It's a tie!");
        }

        // Print the scores
        System.out.println("\nScores:");
        System.out.printf("%-10s", "Frame");
        for (int i = 1; i <= player1Scores.length; i++) {
            System.out.printf("%-10d", i);
        }
        System.out.println();

        System.out.printf("%-10s", player1Name);
        for (int score : player1Scores) {
            System.out.printf("%-10d", score);
        }
        System.out.println();

        System.out.printf("%-10s", player2Name);
        for (int score : player2Scores) {
            System.out.printf("%-10d", score);
        }
        System.out.println();
    }

    private static int getTurnScores(String playerName, Scanner scanner) {
        int firstRoll = getRoll(playerName, scanner);
        if (firstRoll == MAX_PINS) {
            // Strike
            return MAX_PINS + getRoll(playerName, scanner) + getRoll(playerName, scanner);
        } else {
            int secondRoll = getRoll(playerName, scanner);
            if (firstRoll + secondRoll == MAX_PINS) {
                // Spare
                return MAX_PINS + getRoll(playerName, scanner);
            } else {
                // Open frame
                return firstRoll + secondRoll;
            }
        }
    }

    private static int getRoll(String playerName, Scanner scanner) {
        int score;
        while (true) {
            System.out.printf("%s, enter the number of pins knocked down (0-%d): ", playerName, MAX_PINS);
            String input = scanner.nextLine().trim();
            try {
                score = Integer.parseInt(input);
                if (score >= 0 && score <= MAX_PINS) {
                    break; // Valid input, break the loop
                } else {
                    System.out.printf("Invalid input. Please enter a number between 0 and %d.\n", MAX_PINS);
                }
            } catch (NumberFormatException e) {
                System.out.printf("\"%s\" is not a valid number. Please enter again!\n", input);
            }
        }
        return score;
    }

    private static int getGameScores(int[] scores) {
        int total = 0;
        for (int score : scores) {
            total += score;
        }
        return total;
    }
}
