package AdventOfCode.year2021.day05;

import AdventOfCode.year2021.Reader;

public class Solution {

    public static void main(String[] args) {
        String[] input = Reader.readFile("./AdventOfCode/year2021/day05/input.txt");

        int[][] intInput = new int[input.length][4];
        for (int i = 0; i < input.length; i++) {
            String[] split = input[i].split("(,)|( \\-\\> )"); // Split by "," and " -> "
            for (int j = 0; j < split.length; j++)
                intInput[i][j] = Integer.parseInt(split[j]);
        }

        part1(intInput);
        part2(intInput);
    }

    public static void part1(int[][] input) {
        // Determine the largest number
        int max = 0;
        for (int i = 0; i < input.length; i++)
            for (int j = 0; j < input[i].length; j++)
                if (input[i][j] > max)
                    max = input[i][j];

        // Create a grid with the size of max
        int[][] grid = new int[max + 1][max + 1];

        for (int i = 0; i < input.length; i++) {
            int x1 = input[i][0];
            int y1 = input[i][1];
            int x2 = input[i][2];
            int y2 = input[i][3];

            // Only consider horizontal and vertical lines
            // Skip diagonals
            if (x1 != x2 && y1 != y2)
                continue;

            int moveX = 0, moveY = 0;
            if (Math.abs(x1 - x2) != 0)
                moveX = (x1 - x2) / Math.abs(x1 - x2);
            if (Math.abs(y1 - y2) != 0)
                moveY = (y1 - y2) / Math.abs(y1 - y2);

            while (!(x1 == x2 && y1 == y2)) {
                // Mark that point
                grid[x2][y2]++;
                // Move towards the other point
                if (x1 != x2)
                    x2 += moveX;
                if (y1 != y2)
                    y2 += moveY;

                // Mark the last point too
                if (x1 == x2 && y1 == y2)
                    grid[x2][y2]++;
            }
        }

        // Count the number of points with 2 or more lines
        int overlaps = 0;
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[i].length; j++)
                if (grid[i][j] >= 2)
                    overlaps++;

        System.out.println("Part 1: " + overlaps);
    }

    // Same as part one except we will not skip diagonal lines
    public static void part2(int[][] input) {
        // Determine the largest number
        int max = 0;
        for (int i = 0; i < input.length; i++)
            for (int j = 0; j < input[i].length; j++)
                if (input[i][j] > max)
                    max = input[i][j];

        // Create a grid with the size of max
        int[][] grid = new int[max + 1][max + 1];

        for (int i = 0; i < input.length; i++) {
            int x1 = input[i][0];
            int y1 = input[i][1];
            int x2 = input[i][2];
            int y2 = input[i][3];

            int moveX = 0, moveY = 0;
            if (Math.abs(x1 - x2) != 0)
                moveX = (x1 - x2) / Math.abs(x1 - x2);
            if (Math.abs(y1 - y2) != 0)
                moveY = (y1 - y2) / Math.abs(y1 - y2);

            while (!(x1 == x2 && y1 == y2)) {
                // Mark that point
                grid[x2][y2]++;
                // Move towards the other point
                if (x1 != x2)
                    x2 += moveX;
                if (y1 != y2)
                    y2 += moveY;

                // Mark the last point too
                if (x1 == x2 && y1 == y2)
                    grid[x2][y2]++;
            }
        }

        // Count the number of points with 2 or more lines
        int overlaps = 0;
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[i].length; j++)
                if (grid[i][j] >= 2)
                    overlaps++;

        System.out.println("Part 2: " + overlaps);
    }

}
