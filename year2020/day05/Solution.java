package AdventOfCode.year2020.day05;

import AdventOfCode.Reader;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        String[] input = Reader.readFile("./AdventOfCode/year2020/day05/input.txt");
        part1(input);
        part2(input);
    }

    static void part1(String[] input) {
        int highestSeatID = 0;
        int row, column, seatID;

        for (String s : input) {
            // get row
            row = solve(s, 0, 127, 0, 7, 'F');
            // get column
            column = solve(s, 0, 7, 7, 10, 'L');
            // get seat ID
            seatID = row * 8 + column;

            if (seatID > highestSeatID)
                highestSeatID = seatID;
        }

        System.out.println("Part one:");
        System.out.println("The highest seat ID is " + highestSeatID + ".");
    }

    static void part2(String[] input) {
        int row, column,
                yourSeatID = 0;
        int[] seatIDs = new int[input.length];

        for (int i = 0; i < input.length; i++) {
            // get row
            row = solve(input[i], 0, 127, 0, 7, 'F');
            // get column
            column = solve(input[i], 0, 7, 7, 10, 'L');
            // get seat ID
            seatIDs[i] = row * 8 + column;
        }

        Arrays.sort(seatIDs);
        for (int i = 1; i < seatIDs.length; i++) {
            if (seatIDs[i] != seatIDs[i - 1] + 1)
                yourSeatID = seatIDs[i - 1] + 1;
        }

        System.out.println("Part two:");
        System.out.println("Your seat ID: " + yourSeatID);
    }

    static int solve(String s, int min, int max, int start, int stop, char c) {
        for (int i = start; i < stop; i++) {
            if (s.charAt(i) == c) {
                // parse to float so it has the .5 (which then will be left away)
                max -= (float) (max - min) / 2;
            } else {
                // +1 because it will round down
                min += (max - min) / 2 + 1;
            }
        }
        return min;
    }

}

