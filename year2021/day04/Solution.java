package AdventOfCode.year2021.day04;

import AdventOfCode.year2021.Reader;

public class Solution {

    public static void main(String[] args) {
        String[] input = Reader.readFile("./year2021/day04/input.txt");

        // Convert input

        for (int i = 0; i < input.length; i++)
            input[i] = input[i].trim();

        String[] inputNumbers = input[0].split(",");
        int[] numbers = new int[inputNumbers.length];
        for (int i = 0; i < numbers.length; i++)
            numbers[i] = Integer.parseInt(inputNumbers[i]);

        int[][][] boards = new int[input.length / 6][5][5]; // input.length/6 "estimates" (accurately) the number of boards
        int index = 1;
        for (int i = 0; i < boards.length; i++) {
            for (int j = 0; j < boards[i].length; j++) {
                while (input[index].equals(""))
                    index++;

                String[] inputBoardRow = input[index].split("\\s{1,}"); // Split by 1 or more spaces
                for (int a = 0; a < inputBoardRow.length; a++) {
                    boards[i][j][a] = Integer.parseInt(inputBoardRow[a]);
                }
                index++;
            }
        }

        int[][][] boardsClone = boards.clone();
        for (int i = 0; i < boardsClone.length; i++) {
            boardsClone[i] = boards[i].clone();
            for (int j = 0; j < boardsClone[i].length; j++) {
                boardsClone[i][j] = boards[i][j].clone();
            }
        }

        part1(numbers, boards);
        part2(numbers, boardsClone);
    }

    public static void part1(int[] numbers, int[][][] boards) {
        // Read through the numbers
        for (int i = 0; i < numbers.length; i++) {
            // Iterate over all fields of every board
            for (int a = 0; a < boards.length; a++) {
                for (int b = 0; b < boards[a].length; b++) {
                    for (int c = 0; c < boards[a][b].length; c++) {
                        // If the board has the number, mark it (value -1)
                        if (boards[a][b][c] == numbers[i]) {
                            boards[a][b][c] = -1;
                            // Check adjacent values to see if the player has won
                            boolean won = true;
                            for (int j = 0; j < boards[a][b].length; j++) // Row
                                if (boards[a][b][j] != -1)
                                    won = false;
                            if (!won) { // If it is won already, skip this step
                                won = true;
                                for (int j = 0; j < boards[a].length; j++) // Column
                                    if (boards[a][j][c] != -1)
                                        won = false;
                            }

                            if (won) {
                                int score = 0; // Sum of all unmarked numbers * the number that was just called
                                for (int x = 0; x < boards[a].length; x++)
                                    for (int y = 0; y < boards[a][x].length; y++)
                                        if (boards[a][x][y] != -1)
                                            score += boards[a][x][y];
                                score *= numbers[i];
                                System.out.println("Part 1: " + score);
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    public static void part2(int[] numbers, int[][][] boards) {
        boolean[] hasWon = new boolean[boards.length];
        int winners = 0;

        // Read through the numbers
        for (int i = 0; i < numbers.length; i++) {
            // Iterate over all fields of every board
            for (int a = 0; a < boards.length; a++) {
                for (int b = 0; b < boards[a].length; b++) {
                    for (int c = 0; c < boards[a][b].length; c++) {
                        if (hasWon[a])
                            continue;

                        // If the board has the number, mark it (value -1)
                        if (boards[a][b][c] == numbers[i]) {
                            boards[a][b][c] = -1;
                            // Check adjacent values to see if the player has won
                            boolean won = true;
                            for (int j = 0; j < boards[a][b].length; j++) // Row
                                if (boards[a][b][j] != -1)
                                    won = false;
                            if (!won) { // If it is won already, skip this step
                                won = true;
                                for (int j = 0; j < boards[a].length; j++) // Column
                                    if (boards[a][j][c] != -1)
                                        won = false;
                            }

                            if (won) {
                                hasWon[a] = true;
                                winners++;
                                if (winners == boards.length) { // If the last board has won
                                    int score = 0; // Sum of all unmarked numbers multiplied by the number that was just called
                                    for (int x = 0; x < boards[a].length; x++)
                                        for (int y = 0; y < boards[a][x].length; y++)
                                            if (boards[a][x][y] != -1)
                                                score += boards[a][x][y];
                                    score *= numbers[i];
                                    System.out.println("Part 2: " + score);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
