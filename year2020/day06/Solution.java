package AdventOfCode.year2020.day06;

import AdventOfCode.year2020.Reader;

public class Solution {

    public static void main(String[] args) {
        String[] input = Reader.readFile("./year2020/day06/input.txt");
        // I used different approaches in part 1 and 2 since I came up with it when thinking about part 2 and thought it was better.
        part1(input);
        part2(input);
    }

    static void part1(String[] input) {
        int sum = 0;
        char[] yes = new char[26];
        for (int i = 0; i < yes.length; i++)
            yes[i] = ' ';
        int index = 0;
        boolean answered = false;

        for (String s : input) {
            if (s.equals("")) {
                // check how many questions the previous group answered
                for (char c : yes) {
                    if (c != ' ')
                        sum++;
                }
                // reset group-specific variables
                for (int i = 0; i < yes.length; i++)
                    yes[i] = ' ';
                index = 0;
            } else {
                // read answers
                for (int i = 0; i < s.length(); i++) {
                    answered = false;
                    for (char c : yes) {
                        if (c == s.charAt(i))
                            answered = true;
                    }
                    if (!answered) {
                        yes[index] = s.charAt(i);
                        index++;
                    }
                }
            }
        }

        // if the last line is not an empty line
        if (input[input.length - 1] != "") {
            // check how many questions the last group answered
            for (char c : yes) {
                if (c != ' ')
                    sum++;
            }
        }

        System.out.println("Part one:");
        System.out.println("For each group, the number of questions to which anyone answered \"yes\" summed up is: " + sum);
    }

    static void part2(String[] input) {
        int sum = 0;
        String yes = "";

        for (String s : input) {
            if (s.equals("")) {
                // check how many questions the previous group answered
                sum += yes.length();
                // reset group-specific variables
                yes = "";
            } else {
                // read answers
                // if answers come from the first person
                if (yes == "") {
                    yes = s;
                } else {
                    for (int i = 0; i < yes.length(); i++) {
                        String a = Character.toString(yes.charAt(i));
                        if (!s.contains(a)) {
                            // delete a from yes
                            yes = yes.replace(a, "");
                            // set i back because the length of yes has decreased by one
                            i--;
                        }
                    }
                }
            }
        }

        // if the last line is not an empty line
        if (input[input.length - 1] != "") {
            // check how many questions the last group answered
            sum += yes.length();
        }

        System.out.println("Part two:");
        System.out.println("For each group, the number of questions to which everyone answered \"yes\" is: " + sum);
    }

}

