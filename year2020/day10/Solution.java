package AdventOfCode.year2020.day10;

import AdventOfCode.year2020.Reader;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        String[] input = Reader.readFile("./year2020/day10/input.txt");
        int[] numbers = Reader.parseIntArray(input);

        part1(numbers);
        part2(numbers);
    }

    static void part1(int[] input) {
        int dif1 = 0;
        int dif3 = 1; // 1 since there is always a difference of 3
        Arrays.sort(input);

        // test the first element against 0
        if (input[0] == 1)
            dif1++;
        else if (input[0] == 3)
            dif3++;

        for (int i = 1; i < input.length; i++) {
            // if the element equals the previous element + 1
            if (input[i] == input[i - 1] + 1)
                dif1++;
                // if the element equals the previous element + 3
            else if (input[i] == input[i - 1] + 3)
                dif3++;
        }

        System.out.println("Part one:");
        System.out.println(dif1 + " differences of 1 jolt * " + dif3 + " differences of 3 jolts =");
        System.out.println(dif1 * dif3);
    }

    static void part2(int[] input) {
        Arrays.sort(input);
        // We only need to cache the last 3 results
        int[] adapters = new int[3];
        long[] combinations = new long[3];
        // Initialize last indices so that they get deleted last
        adapters[2] = input[0];
        combinations[2] = 1L;
        for (int i = 0; i < input.length; i++) {
            // Add combinations of previous 3
            long sum = 0;
            for (int j = 0; j < 3; j++)
                if (input[i] - adapters[j] <= 3)
                    sum += combinations[j];
            adapters[i % 3] = input[i];
            combinations[i % 3] = sum;
        }

        System.out.println("Part two:");
        System.out.println(combinations[(input.length - 1) % 3]);
    }

}
