// This file only contains part 1. Have a look at Part_2.txt for an explaination.

package AdventOfCode.year2020.day10;

import AdventOfCode.Reader;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        String[] input = Reader.readFile("./AdventOfCode/year2020/day10/input.txt");
        int[] numbers = Reader.parseIntArray(input);

        part1(numbers);
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

}
