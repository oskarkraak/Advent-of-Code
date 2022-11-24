package AdventOfCode.year2020.day03;

import AdventOfCode.year2020.Reader;

public class Solution {

    public static void main(String[] args) {
        String[] input = Reader.readFile("./AdventOfCode/year2020/day03/input.txt");
        part1(input);
        part2(input);
    }

    static void part1(String[] input) {
        int counter = 0;
        // make sure first square isn't counted as a tree
        input[0] = ".";
        int x = -3;

        for (String s : input) {
            x += 3;

            if (x >= s.length())
                x -= s.length();

            if (s.charAt(x) == '#')
                counter++;
        }

        System.out.println("Part one:");
        System.out.println("You would encounter " + counter + " trees.");
    }

    static void part2(String[] input) {
        int[] x = {1, 3, 5, 7, 1},
                y = {1, 1, 1, 1, 2};

        System.out.println("Part two:");

        long solution = 1;
        int trees;
        for (int i = 0; i < x.length; i++) {
            trees = solve(input, x[i], y[i]);
            solution *= trees;
            System.out.println("Right " + x[i] + ", down " + y[i] + ": " + trees);
        }

        System.out.println("All trees multiplied: " + solution);
    }

    static int solve(String[] input, int dX, int dY) {
        int counter = 0;
        // make sure first square isn't counted as a tree
        input[0].replace('#', '.');
        int x = -dX;

        for (int i = 0; i < input.length; i += dY) {
            x += dX;

            if (x >= input[i].length())
                x -= input[i].length();

            if (input[i].charAt(x) == '#')
                counter++;
        }

        return counter;
    }

}

