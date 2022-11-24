package AdventOfCode.year2020.day08;

import AdventOfCode.Reader;

import java.util.ArrayList;

public class Solution {

    public static void main(String[] args) {
        String[] input = Reader.readFile("./AdventOfCode/year2020/day08/input.txt");
        System.out.println("The value in the accumulator immediately before any instruction is executed a second time is: " + part1(input));
        part2(input);
    }

    static int part1(String[] input) {
        int accumulator = 0;
        ArrayList<Integer> finishedInstructions = new ArrayList<Integer>();

        // Iterate through the instructions
        for (int index = 0; index < input.length; index++) {
            // Check if the instruction has already been finished once
            if (finishedInstructions.contains(index)) {
                // If so, stop
                break;
            }
            // Save the instruction as finished before (possibly) jumping
            finishedInstructions.add(index);

            // Identify the operation by the first letter of the instruction
            char firstLetter = input[index].charAt(0);
            if (firstLetter == 'a') {
                // acc - increase/decrease accumulator
                accumulator += getArgument(input[index]);
            } else if (firstLetter == 'j') {
                // jmp - jump to another line
                index += getArgument(input[index]) - 1; // subtract 1 because the for loop adds 1 by default
            }
            // We'll leave out "nop" since we wouldn't do anything anyway
        }

        return accumulator;
    }

    static void part2(String[] input) {
        int changedIndex = 0;
        String[] changedInput = new String[input.length];
        ArrayList<Integer> finishedInstructions = new ArrayList<Integer>();

        // Iterate through the instructions
        for (int index = 0; index < input.length; index++) {
            // Check if the instruction has already been finished once
            if (finishedInstructions.contains(index)) {
                // If so, stop
                System.out.println("Didn't find any instruction that should be changed. Part 2 failed.");
                return;
            }
            // Save the instruction as finished before (possibly) jumping
            finishedInstructions.add(index);

            // Identify the operation by the first letter of the instruction
            String[] changed = input.clone();
            char firstLetter = input[index].charAt(0);
            if (firstLetter == 'n') {
                // nop - try to change to jmp
                changed[index] = "jmp" + changed[index].substring(3, changed[index].length());
            } else if (firstLetter == 'j') {
                // jmp - try to change to nop
                changed[index] = "nop" + changed[index].substring(3, changed[index].length());
            } else {
                continue;
            }
            // We'll leave out "acc" since it cant be changed

            if (!isInfiniteLoop(changed)) {
                changedInput = changed;
                changedIndex = index;
                break;
            }

            if (firstLetter == 'j')
                // jmp - jump to another line
                index += getArgument(input[index]) - 1; // subtract 1 because the for loop adds 1 by default
        }

        int accumulator = part1(changedInput);
        System.out.println("The value in the accumulator after changing line " + changedIndex + " from " + input[changedIndex] + " to " + changedInput[changedIndex] + " is: " + accumulator);
    }

    static int getArgument(String instruction) {
        // Get a substring that contains only the argument
        String argument = instruction.substring(5, instruction.length());

        // Convert to int
        int toReturn = 0;
        try {
            toReturn = Integer.parseInt(argument);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Apply minus sign if necessary
        if (instruction.charAt(4) == '-')
            toReturn *= -1;

        return toReturn;
    }

    static boolean isInfiniteLoop(String[] instructions) {
        ArrayList<Integer> finishedInstructions = new ArrayList<Integer>();

        // Iterate through the instructions
        for (int index = 0; index < instructions.length; index++) {
            // Check if the instruction has already been finished once
            if (finishedInstructions.contains(index)) {
                // If so, stop
                return true;
            }
            // Save the instruction as finished before (possibly) jumping
            finishedInstructions.add(index);

            // Identify the operation by the first letter of the instruction
            char firstLetter = instructions[index].charAt(0);
            if (firstLetter == 'j') {
                // jmp - jump to another line
                index += getArgument(instructions[index]) - 1; // subtract 1 because the for loop adds 1 by default
            }
            // We'll leave out "nop" and "acc" since they don't change the order of instructions
        }

        return false;
    }

}
