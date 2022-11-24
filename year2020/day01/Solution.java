package AdventOfCode.year2020.day01;

import AdventOfCode.year2020.Reader;

public class Solution {

    public static void main(String[] args) {
        String[] input = Reader.readFile("./year2020/day01/input.txt");
        int[] numbers = Reader.parseIntArray(input);

        part1(numbers);
        part2(numbers);
    }

    static void part1(int[] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                if (input[i] + input[j] == 2020) {
                    int a = input[i];
                    int b = input[j];
                    System.out.println("Part one:");
                    System.out.println(a + " + " + b + " = " + (a + b));
                    System.out.println(a + " * " + b + " = " + a * b);
                }
            }
        }
    }

    static void part2(int[] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                //if first 2 nums summed up >= 2020 then don't even bother
                if (input[i] + input[j] < 2020) {
                    for (int k = j + 1; k < input.length; k++) {
                        if (input[i] + input[j] + input[k] == 2020) {
                            int a = input[i];
                            int b = input[j];
                            int c = input[k];
                            System.out.println("Part two:");
                            System.out.println(a + " + " + b + " + " + c + " = " + (a + b + c));
                            System.out.println(a + " * " + b + " * " + c + " = " + a * b * c);
                        }
                    }
                }
            }
        }
    }

}

