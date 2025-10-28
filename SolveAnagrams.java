import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.text.NumberFormat;
import javax.swing.*;

public class SolveAnagrams {

    static int windowSize = 500;
    public static void main(String[] args) {
        DrawMainWindow();
    }

    public static void DrawMainWindow() {
        JFrame frame = new JFrame("Anagrams Solver");
        JLabel instruction = new JLabel("Input the anagrams board into the boxes below");


        JPanel numOfTilesPanel = new JPanel();
        numOfTilesPanel.setLayout(null);
        numOfTilesPanel.setPreferredSize(new Dimension(0, 100));
        JLabel numberOfTilesInstruction = new JLabel("Set the number of tiles in the anagrams board: ");
        numberOfTilesInstruction.setBounds(75, 25, 300, 50);
        numberOfTilesInstruction.setFont(new Font("Arial", Font.PLAIN, 14));
        NumberFormat integerFormat = NumberFormat.getIntegerInstance();
        JFormattedTextField tilesNumber = new JFormattedTextField(integerFormat);
        tilesNumber.setBounds(375, 25, 50, 50);
        tilesNumber.setValue(6);
        tilesNumber.setFont(new Font("Arial", Font.PLAIN, 18));
        numOfTilesPanel.add(numberOfTilesInstruction);
        numOfTilesPanel.add(tilesNumber);

        JPanel anagramsTilesPanel = new JPanel();
        anagramsTilesPanel.setLayout(null);
        JTextField anagramsTiles = new JTextField("AAAAAA", 6);
        anagramsTiles.setHorizontalAlignment(JTextField.CENTER);
        anagramsTiles.setEditable(true);
        anagramsTiles.setBounds(100, 100, 300, 75);
        anagramsTiles.setFont(new Font("Arial", Font.PLAIN, 36));
        anagramsTilesPanel.add(anagramsTiles);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setPreferredSize(new Dimension(0, 100));
        JButton solveButton = new JButton("Solve");
        solveButton.setFont(new Font("Arial", Font.BOLD, 24));
        solveButton.setBounds(200, 25, 100, 50);
        buttonPanel.add(solveButton);
        solveButton.addActionListener((e) -> {
            try {
                tilesNumber.commitEdit();
                long tmp = (long) tilesNumber.getValue();
                int totalNumberOfTiles = (int) tmp;
                
                System.out.println("Number value: " + totalNumberOfTiles);

                String anagramsBoard = anagramsTiles.getText();
                System.out.println("Board: " + anagramsBoard);

                if (anagramsBoard.length() != totalNumberOfTiles) {
                    System.err.println("String not correct length");
                }
                else {
                    System.out.println("Runnign word scrapper");
                    WordScraper scraper = new WordScraper(totalNumberOfTiles);
                    scraper.CreateWordList();
                    System.out.println("Runnign solver");
                    // Finds the valid anagrams
                    AnagramBoard ana = new AnagramBoard(anagramsBoard, totalNumberOfTiles);
                    ana.Solve();
                    scraper.CloseFile("words.txt");         
                }
                
                
            }
            catch (java.text.ParseException ex) {
                System.err.println("Error parsing number: " + ex.getMessage());
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(windowSize, windowSize);
        frame.add(anagramsTilesPanel, BorderLayout.CENTER);
        frame.add(numOfTilesPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
