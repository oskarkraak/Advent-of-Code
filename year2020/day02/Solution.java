package AdventOfCode.year2020.day02;

import AdventOfCode.year2020.Reader;

public class Solution {

    public static void main(String[] args) {
        String[] input = Reader.readFile("./AdventOfCode/year2020/day02/input.txt");
        part1(input);
        part2(input);
    }

    static void part1(String[] input) {
        int valid = 0;

        for (String s : input) {
            char[] c = s.toCharArray();
            String temp = "";
            int min;
            int max;
            int index = 0;
            int counter = 0;

            //read minimum
            while (c[index] != '-') {
                temp = temp + c[index];
                index++;
            }
            min = Integer.parseInt(temp);
            //skip the "-"
            index++;
            //read maximum
            temp = "";
            while (c[index] != ' ') {
                temp = temp + c[index];
                index++;
            }
            max = Integer.parseInt(temp);
            //skip the space
            index++;
            //read letter
            char letter = c[index];
            //skip ": "
            index += 2;
            for (int i = index; i < c.length; i++) {
                if (c[i] == letter) {
                    counter++;
                }
            }

            if (min <= counter && counter <= max) {
                valid++;
            }
        }

        System.out.println("There are " + valid + " valid passwords.");
    }

    static void part2(String[] input) {
        int valid = 0;

        for (String s : input) {
            char[] c = s.toCharArray();
            String temp = "";
            int pos1, pos2;
            int index = 0;
            int counter = 0;

            //read minimum
            while (c[index] != '-') {
                temp = temp + c[index];
                index++;
            }
            pos1 = Integer.parseInt(temp);
            //skip the "-"
            index++;
            //read maximum
            temp = "";
            while (c[index] != ' ') {
                temp = temp + c[index];
                index++;
            }
            pos2 = Integer.parseInt(temp);
            //skip the space
            index++;
            //read letter
            char letter = c[index];
            //skip ": "
            index += 2;

            if (c[pos1 + index] == letter && c[pos2 + index] != letter) {
                valid++;
            }
            if (c[pos1 + index] != letter && c[pos2 + index] == letter) {
                valid++;
            }
        }

        System.out.println("There are " + valid + " valid passwords.");
    }

}

