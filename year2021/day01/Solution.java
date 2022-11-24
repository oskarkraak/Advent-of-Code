package AdventOfCode.year2021.day01;

import AdventOfCode.year2021.Reader;

public class Solution {

    public static void main(String[] args) {
        String[] input = Reader.readFile("./AdventOfCode/year2021/day01/input.txt");
        int[] intInput = Reader.parseIntArray(input);
        part1(intInput);
        part2(intInput);
    }

    public static void part1(int[] input) {
        int increased = 0;
        for (int i = 1; i < input.length; i++)
            if (input[i] > input[i - 1])
                increased++;
        System.out.println("Part 1: " + increased);
    }

    public static void part2(int[] input) {
        int increased = 0;

        int lastWindow = input[0] + input[1] + input[2];
        for (int i = 1; i < input.length - 2; i++) {
            int currentWindow = input[i] + input[i + 1] + input[i + 2];
            if (currentWindow > lastWindow)
                increased++;
            lastWindow = currentWindow;
        }

        System.out.println("Part 2: " + increased);
    }

}
