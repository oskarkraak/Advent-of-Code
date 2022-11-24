package AdventOfCode.year2021.day03;

import AdventOfCode.year2021.Reader;

import java.util.ArrayList;

public class Solution {

    public static void main(String[] args) {
        String[] input = Reader.readFile("./year2021/day03/input.txt");
        part1(input);
        part2(input);
    }

    public static void part1(String[] input) {
        int gamma = 0, epsilon = 0;
        // Iterate through columns
        for (int i = 0; i < input[0].length(); i++) {
            int zero = 0, one = 0;
            // Iterate through lines
            for (int j = 0; j < input.length; j++) {
                if (input[j].charAt(i) == '0')
                    zero++;
                else if (input[j].charAt(i) == '1')
                    one++;
            }

            if (zero > one) {
                gamma = 2 * gamma;
                epsilon = 2 * epsilon + 1;
            } else if (zero < one) {
                gamma = 2 * gamma + 1;
                epsilon = 2 * epsilon;
            }
        }

        System.out.println("Part 1: " + gamma * epsilon);
    }

    public static void part2(String[] input) {
        // Convert to ArrayLists
        ArrayList<String> oxygen = new ArrayList<>(), co2 = new ArrayList<>();
        for (String s : input) {
            oxygen.add(s);
            co2.add(s);
        }

        // Iterate through columns
        for (int i = 0; i < input[0].length(); i++) {
            if (oxygen.size() == 1 && co2.size() == 1)
                break;

            // Oxygen
            char mostCommon = mostCommon(oxygen, i);
            if (oxygen.size() > 1)
                for (int a = oxygen.size() - 1; a >= 0; a--)
                    if (oxygen.get(a).charAt(i) != mostCommon)
                        oxygen.remove(a);

            // CO2
            mostCommon = mostCommon(co2, i);
            if (co2.size() > 1)
                for (int a = co2.size() - 1; a >= 0; a--)
                    if (co2.get(a).charAt(i) == mostCommon)
                        co2.remove(a);
        }

        int oxygenDecimal = Integer.parseInt(oxygen.get(0), 2);
        int co2Decimal = Integer.parseInt(co2.get(0), 2);
        System.out.println("Part 2: " + oxygenDecimal * co2Decimal);
    }

    public static char mostCommon(ArrayList<String> lines, int column) {
        char mostCommon = '0';
        int zero = 0, one = 0;
        for (String s : lines) {
            if (s.charAt(column) == '0')
                zero++;
            else
                one++;
        }

        if (zero <= one)
            mostCommon = '1';

        return mostCommon;
    }

}
