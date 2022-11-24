package AdventOfCode.year2020.day07;

import AdventOfCode.Reader;

import java.util.ArrayList;

public class Solution {

    public static void main(String[] args) {
        String[] input = Reader.readFile("./AdventOfCode/year2020/day07/input.txt");
        part1(input);
        part2(input);
    }

    public static void part1(String[] input) {
        // Purge input from bags that don't contain other bags
        ArrayList<String> purgedInput = new ArrayList<String>();
        for (String s : input) {
            // If the 5th word is not "no", don't purge the line
            if (!s.split(" ")[4].equals("no"))
                purgedInput.add(s);
        }

        // Split the input to make it processible
        String[][] rules = new String[purgedInput.size()][];
        for (int i = 0; i < rules.length; i++) {
            // Remove "bags" and "bag" from the Strong to avoid compications with the plural
            String removed = purgedInput.get(i).replace("bags", "");
            removed = removed.replace("bag", "");
            purgedInput.set(i, removed);

            rules[i] = purgedInput.get(i).split("( contain )|(, )"); // Split the Strings with these two delimiters
        }

        ArrayList<String> alreadyCounted = new ArrayList<String>();
        alreadyCounted.add("shiny gold");
        int combinations = getCombinations("shiny gold", rules, alreadyCounted);

        System.out.println(combinations + " bag colors can eventually contain at least one shiny gold bag.");
    }

    public static int getCombinations(String color, String[][] rules, ArrayList<String> alreadyCounted) {
        int combinations = 0;

        // If this color has not been counted already, do so
        if (!alreadyCounted.contains(color)) {
            combinations++;
            alreadyCounted.add(color);
        }

        // Get the number of bags that contain the color:

        // Iterate through rules
        for (String[] rule : rules) {
            // Iterate through the colors that the rule mentions
            for (int i = 1; i < rule.length; i++) {
                // If the color is mentioned
                if (rule[i].contains(color)) {
                    // Get the number of bags that contain the new color using this method recursively
                    combinations += getCombinations(rule[0], rules, alreadyCounted);
                }
            }
        }

        return combinations;
    }

    public static void part2(String[] input) {
        // Purge input from bags that don't contain other bags
        ArrayList<String> purgedInput = new ArrayList<String>();
        for (String s : input) {
            // If the 5th word is not "no", don't purge the line
            if (!s.split(" ")[4].equals("no"))
                purgedInput.add(s);
        }

        // Split the input to make it processible
        String[][] rules = new String[purgedInput.size()][];
        for (int i = 0; i < rules.length; i++) {
            // Remove "bags" and "bag" from the Strong to avoid compications with the plural
            String removed = purgedInput.get(i).replace("bags", "");
            removed = removed.replace("bag", "");
            purgedInput.set(i, removed);

            rules[i] = purgedInput.get(i).split("( contain )|(, )"); // Split the Strings with these two delimiters
        }

        int bags = countBags("shiny gold", rules);

        System.out.println(bags + " individual bags are required inside your single shiny gold bag.");
    }

    public static int countBags(String color, String[][] rules) {
        int bags = 0;

        // Get the number of bags that the color contains:

        // Iterate through rules to find the color
        for (String[] rule : rules) {
            // If the bag has the color
            if (rule[0].contains(color)) {
                // Iterate through the colors that the rule mentions
                for (int i = 1; i < rule.length; i++) {
                    // For each color, recursively count the number of bags contained
                    int bagCount = Integer.parseInt(rule[i].split(" ")[0]); // Number of bags of that color
                    String newColor = rule[i].substring(2, rule[i].length() - 2); // Trim the String to get only the color
                    int containedBags = countBags(newColor, rules); // Number of bags that each bag of this color contains
                    bags += bagCount + bagCount * containedBags; // Add result to bags
                }
            }
        }

        return bags;
    }

}
