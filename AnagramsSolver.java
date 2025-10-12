
import java.util.Scanner;

public class AnagramsSolver {
    public static int boardSize = 0;
    public static String board = new String();

    public static void main(String[] args) {
        System.out.println("This program uses valid words from Scrabble to find valid words \n!!Some words may not work!!\n");

        // Gets the inputs for for the size and content of the list
        GetInputs();

        // Gets all the words that are within the size limit
        WordScraper scraper = new WordScraper(boardSize);
        scraper.CreateWordList();

        // Finds the valid anagrams
        AnagramBoard anagrams = new AnagramBoard(board, boardSize);
        anagrams.Solve();
        scraper.CloseFile("words.txt");
    }

    public static void GetInputs() {
        try(Scanner scanner = new Scanner(System.in)) {

            boolean validInput = false;

            while (!validInput) {
                try {
                    System.out.println("Input the size of the anagrams board (Minimum 3): ");
                    boardSize = scanner.nextInt();
                    if (boardSize < 3) {
                        System.out.println("\n-- Size of board is not greater than 3\n");
                        continue;
                    }
                    validInput = true;
                } catch (Exception e) {
                    System.out.println("\n-- Error reading input. Make sure input is number greater than 3\n");
                    scanner.nextLine();
                }
            }
                
            scanner.nextLine();
            validInput = false;

            while (!validInput) {
                try {
                    System.out.println("Enter tiles in board (eg: abcdef): ");
                    board = scanner.nextLine();
                    if (board.length() != boardSize) {
                        System.out.println("\n-- Number of tiles does not match earlier input\n");
                        continue;
                    }                    
                    validInput = true;
                    System.out.println("");
                } catch (Exception e) {
                    System.out.println("\n-- Error reading input. Make sure input is a string the size of the previous input with no spaces\n");
                    scanner.nextLine();
                }
            }
        }
    }
}
