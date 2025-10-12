import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class AnagramBoard {
    public static ArrayList<Character> tiles = new ArrayList<>();
    HashSet<String> dictionary = new HashSet<>();
    private int boardSize;

    // board - 6 letter string of all the characters in the board
    public AnagramBoard(String board, int size) {

        boardSize = size;
        // Set provided string to board tiles
        for (int i = 0; i < board.length(); i++) {
            tiles.add(board.charAt(i));
        }

        try (Scanner scanner = new Scanner(new File("words.txt"))) {
            while (scanner.hasNextLine()) {
                //System.err.println("Adding line");
                // Initialize the dictionary
                String line = scanner.nextLine();
                dictionary.add(line);   
            }
            
        } catch (Exception er) {
            System.out.println(er);
        }
    }

    // Prints the stored list of tiles
    public void printBoard() {
        for (Character tile : tiles) {
            System.out.print(tile);
        }
        System.out.println("\n");
    }

    // Prints a given list of tiles
    public void printBoard(ArrayList<Character> board) {
        for (Character tile : board) {
            System.out.print(tile);
        }
        System.out.println("\n");
    }

    // Prints the class ditionary
    @SuppressWarnings("unused")
    private void printDict() {
        for (String word : dictionary) {
            System.out.println(word);
        }
    }

    // Finds all valid permutations of the classes tiles
    public void Solve() {
        int[] traversed = new int[boardSize];
        ArrayList<Character> board = new ArrayList<>();
        ArrayList<String> validWords = SolveRecursion(traversed, board, 0, new ArrayList<>());
        PrintFinalAnswers(validWords);
        
    }

    // Solves the anagrams board and displays the answers
    // traversed - 0 if not yet traversed this permute and 1 if has been traversed this permute
    private ArrayList<String> SolveRecursion(int[] traversed, ArrayList<Character> board, int boardLength, ArrayList<String> validWords) {
        // Print out the board, minimum 3 tiles to make a word
        if (boardLength >= 3) {
            StringBuilder boardStr = new StringBuilder();
            for (Character c : board) {
                boardStr.append(c);
            }
            String boardSTR = boardStr.toString();
            if(IsValidWord(boardSTR)) {
                if (!validWords.contains(boardSTR)) {
                    validWords.add(boardSTR);
                }
            }
        }

        // Loop through every tile in anagrams board
        for (int i = 0; i < tiles.size(); i++) {

            // Adds to board and sets traversed
            if (traversed[i] == 0) {
                // Add tile to the board and mark it as added
                board.add(tiles.get(i));
                traversed[i] = 1;

                // Go deeper into recursion
                validWords = SolveRecursion(traversed, board, boardLength + 1, validWords);

                // Remove the tile to allow new ones to be inserted
                board.remove(boardLength);
                traversed[i] = 0;
            }
        }

        return validWords;
    }

    // Checks if provided string is a valid word
    public boolean IsValidWord(String string) {
        return dictionary.contains(string);
    }

    private static void PrintFinalAnswers(ArrayList<String> array) {
        Collections.sort(array, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
        int currentLength;
        int prevLength = 0;
        for (String answer : array) {
            currentLength = answer.length();
            if (currentLength != prevLength) {
                System.out.println("\nLength == " + currentLength);
            }
            System.out.println(answer);
            prevLength = currentLength;
        }
    }
}