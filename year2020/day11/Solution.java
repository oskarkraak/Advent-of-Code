package AdventOfCode.year2020.day11;

import AdventOfCode.Reader;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        String[] input = Reader.readFile("./AdventOfCode/year2020/day11/input.txt");
        // Transform input into char grid
        // 2 of them so part 2 does not work with the edited input
        char[][] grid1 = new char[input.length][input[0].length()]; // [grid height] [grid width]
        char[][] grid2 = new char[input.length][input[0].length()];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length(); j++) {
                grid1[i][j] = input[i].charAt(j);
                grid2[i][j] = input[i].charAt(j);
            }
        }

        part1(grid1);
        part2(grid2);
    }

    static void part1(char[][] input) {
        // Calculate through rounds:
        char[][] lastGrid = new char[input.length][input[0].length];
        do {
            // Copy input into lastGrid
            for (int i = 0; i < input.length; i++)
                for (int j = 0; j < input[i].length; j++)
                    lastGrid[i][j] = input[i][j];

            // Iterate through all seats
            for (int i = 0; i < input.length; i++) {
                for (int j = 0; j < input[0].length; j++) {
                    if (input[i][j] == 'L') {
                        // Occupy seat?
                        int occupiedAdjacentSeats = getOccupiedAdjacentSeats(lastGrid, j, i);
                        if (occupiedAdjacentSeats == 0)
                            // Occupy
                            input[i][j] = '#';
                    } else if (input[i][j] == '#') {
                        // Leave seat?
                        int occupiedAdjacentSeats = getOccupiedAdjacentSeats(lastGrid, j, i);
                        if (occupiedAdjacentSeats >= 4)
                            // Leave
                            input[i][j] = 'L';
                    }
                }
            }

        } while (!equals(input, lastGrid));

        // Count the number of occupied seats after the last round
        int occupied = 0;
        // Iterate through all seats
        for (int i = 0; i < input.length; i++)
            for (int j = 0; j < input[0].length; j++)
                if (input[i][j] == '#')
                    occupied++;

        System.out.println("Part 1: " + occupied);
    }

    static int getOccupiedAdjacentSeats(char[][] seats, int x, int y) {
        int occupiedAdjacentSeats = 0;
        int gridWidth = seats[0].length;

        // Check seats in the row above:
        // If not top row
        if (y > 0) {
            // Go through top left to top right
            for (int i = -1; i <= 1; i++) {
                // If not outside grid
                if (x + i >= 0 && x + i < gridWidth) {
                    if (seats[y - 1][x + i] == '#')
                        occupiedAdjacentSeats++;
                }
            }
        }

        // Check seats in the same row:
        // If not outside grid
        if (x - 1 >= 0)
            if (seats[y][x - 1] == '#')
                occupiedAdjacentSeats++;
        // If not outside grid
        if (x + 1 < gridWidth)
            if (seats[y][x + 1] == '#')
                occupiedAdjacentSeats++;

        // Check seats in the row below:
        // If not bottom row
        if (y < seats.length - 1) {
            // Go through bottom left to bottom right
            for (int i = -1; i <= 1; i++) {
                // If not outside grid
                if (x + i >= 0 && x + i < gridWidth) {
                    if (seats[y + 1][x + i] == '#')
                        occupiedAdjacentSeats++;
                }
            }
        }

        return occupiedAdjacentSeats;
    }

    static void part2(char[][] input) {
        // Calculate through rounds:
        char[][] lastGrid = new char[input.length][input[0].length];
        do {
            // Copy input into lastGrid
            for (int i = 0; i < input.length; i++)
                for (int j = 0; j < input[i].length; j++)
                    lastGrid[i][j] = input[i][j];

            // Iterate through all seats
            for (int i = 0; i < input.length; i++) {
                for (int j = 0; j < input[0].length; j++) {
                    if (input[i][j] == 'L') {
                        // Occupy seat?
                        int occupiedVisibleSeats = getOccupiedVisibleSeats(lastGrid, i, j);
                        if (occupiedVisibleSeats == 0)
                            // Occupy
                            input[i][j] = '#';
                    } else if (input[i][j] == '#') {
                        // Leave seat?
                        int occupiedVisibleSeats = getOccupiedVisibleSeats(lastGrid, i, j);
                        if (occupiedVisibleSeats >= 5) // This is now 5
                            // Leave
                            input[i][j] = 'L';
                    }
                }
            }

        } while (!equals(input, lastGrid));

        // Count the number of occupied seats after the last round
        int occupied = 0;
        // Iterate through all seats
        for (int i = 0; i < input.length; i++)
            for (int j = 0; j < input[0].length; j++)
                if (input[i][j] == '#')
                    occupied++;

        System.out.println("Part 2: " + occupied);
    }

    // It is now about the first seat in each direction
    static int getOccupiedVisibleSeats(char[][] seats, int ySeat, int xSeat) {
        int occupiedVisibleSeats = 0;
        int gridHeight = seats.length;
        int gridWidth = seats[0].length;

        // Check seats in every direction
        /*
         * Go through -1 to 1 as movement in y/x coordinates.
         * This way we get all possible directions.
         * 0/0 will be skipped because it would be stuck at the current seat.
         */
        for (int y = -1; y <= 1; y++) {
            for (int x = -1; x <= 1; x++) {
                if (y == 0 && x == 0)
                    continue;

                int yLooking = ySeat;
                int xLooking = xSeat;

                // Look over the floor until a seat was found
                do {
                    // Look 1 further
                    yLooking += y;
                    xLooking += x;

                    // Check if yLooking and xLooking are still inside the grid
                    // If we are outside this means we have not seen a seat - and no occupied seat
                    if (yLooking < 0 || yLooking >= gridHeight)
                        break;
                    if (xLooking < 0 || xLooking >= gridWidth)
                        break;
                } while (seats[yLooking][xLooking] == '.');

                // Either we have looked outside the grid...
                if (yLooking < 0 || yLooking >= gridHeight)
                    continue;
                if (xLooking < 0 || xLooking >= gridWidth)
                    continue;
                // ... or we have found a seat ...
                if (seats[yLooking][xLooking] == '#')
                    // ... that we count if it is occupied.
                    occupiedVisibleSeats++;
            }
        }

        return occupiedVisibleSeats;
    }

    static boolean equals(char[][] a, char[][] b) {
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[0].length; j++)
                if (a[i][j] != b[i][j])
                    return false;

        return true;
    }

}
