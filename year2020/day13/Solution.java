package AdventOfCode.year2020.day13;

import AdventOfCode.year2021.Reader;

import java.util.ArrayList;

public class Solution {

    public static void main(String[] args) {
        String[] input = Reader.readFile("./year2020/day13/input.txt");
        part1(input);
        part2(input[1].split(","));
    }

    public static void part1(String[] input) {
        // Parse input
        int arrival = Integer.parseInt(input[0]);
        ArrayList<Integer> buses = new ArrayList<>();
        for (String id : input[1].split(",")) {
            if (Character.isDigit(id.charAt(0))) {
                buses.add(Integer.parseInt(id));
            }
        }
        // Calculate waiting time for each bus
        int lowestWaitingTime = Integer.MAX_VALUE;
        int lowestWaitingTimeBus = -1;
        for (int bus : buses) {
            int waitingTime = bus - (arrival % bus);
            if (waitingTime < lowestWaitingTime) {
                lowestWaitingTime = waitingTime;
                lowestWaitingTimeBus = bus;
            }
        }
        System.out.println("Part 1: " + (lowestWaitingTime * lowestWaitingTimeBus));
    }

    public static void part2(String[] input) {
        long stepSize = Long.parseLong(input[0]);
        long timestamp = 0;
        for (int i = 1; i < input.length; i++) {
            if (input[i].equals("x"))
                continue;
            int bus = Integer.parseInt(input[i]);
            // Find the first occurence of correct departing times
            while (((timestamp + i) % bus) != 0)
                timestamp += stepSize;
            // This pattern repeats; adjust step size
            stepSize *= bus;
        }
        System.out.println("Part 2: " + (timestamp));
    }

}
