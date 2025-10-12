
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class WordScraper {
    public static int lB;
    public static int uB;

    // Uses provided inputs to set the bounds
    public WordScraper(int lowerBound, int upperBound) {
        lowerBound = lB;
        upperBound = uB;
    }

    // Lets the user determine the bounds of the words
    public WordScraper() {
        GetBounds();
    }

    // Assumes 3 is the lowest number of letters needed to make up a word
    public WordScraper(int upperBound) {
        lB = 3;
        uB = upperBound;
    }

    public void CreateWordList() {
        try(Scanner reader = new Scanner(new File("AllWords.txt"))) {
            FileWriter fileWriter = new FileWriter("words.txt", true); 
            try (PrintWriter writer = new PrintWriter(fileWriter)) {
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();

                    if (line.length() < lB || line.length() > uB) {
                        continue;
                    }

                    boolean containsOnlyAlphabet = true;
                    for (int i = 0; i < line.length(); i++) {
                        Character c = line.charAt(i);
                        if (!Character.isLetterOrDigit(c) || Character.isDigit(c)) {
                            containsOnlyAlphabet = false;
                            break;
                        }
                    }
                    if (containsOnlyAlphabet) {
                        writer.println(line.toLowerCase());
                    }
                }
            } catch (Exception er) {
                System.err.println(er);
            }
        } catch (Exception er) {
            System.err.println(er);
        }
    }

    private static void GetBounds() {
        try(Scanner scanner = new Scanner(System.in)) {

            boolean validInput = false;

            while (!validInput) {
                try {
                    System.out.println("Input lower bound of valid words: ");
                    lB = scanner.nextInt();
                    validInput = true;
                } catch (Exception e) {
                    System.out.println("\n-- Error reading input. Make sure input is a number\n");
                    scanner.nextLine();
                }
            }
                
            scanner.nextLine();
            validInput = false;

            while (!validInput) {
                try {
                    System.out.println("Enter upper bound of valid words: ");
                    uB = scanner.nextInt();                 
                    validInput = true;
                } catch (Exception e) {
                    System.out.println("\n-- Error reading input. Make sure input is a number\n");
                    scanner.nextLine();
                }
            }
        }
    }

    public void CloseFile(String fileName) {
        File file = new File(fileName);
        file.delete();
    }
}
