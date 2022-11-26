package AdventOfCode.year2020.day12;

import AdventOfCode.year2021.Reader;

public class Solution {

    public static void main(String[] args) {
        String[] input = Reader.readFile("./year2020/day12/input.txt");
        part1(input);
        part2(input);
    }

    public static void part1(String[] input) {
        // Ship coordinates
        int x = 0;
        int y = 0;
        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int direction = 0;
        for (int i = 0; i < input.length; i++) {
            // Decode
            char action = input[i].charAt(0);
            int amt = Integer.parseInt(input[i].substring(1));
            // Get direction
            int dirX = 0, dirY = 0;
            switch (action) {
                case 'N':
                    dirY = 1;
                    break;
                case 'S':
                    dirY = -1;
                    break;
                case 'E':
                    dirX = 1;
                    break;
                case 'W':
                    dirX = -1;
                    break;
                case 'L':
                    direction += amt / 90;
                    while (direction > 3)
                        direction -= 4;
                    break;
                case 'R':
                    direction -= amt / 90;
                    while (direction < 0)
                        direction += 4;
                    break;
                case 'F':
                    dirX = directions[direction][0];
                    dirY = directions[direction][1];
                    break;
                default:
                    System.out.println("Error: Invalid action");
                    break;
            }
            // Move
            x += dirX * amt;
            y += dirY * amt;
        }

        System.out.println("Part 1: " + (Math.abs(x) + Math.abs(y)));
    }

    public static void part2(String[] input) {
        // Ship coordinates
        int sx = 0;
        int sy = 0;
        // Waypoint
        int wx = 10;
        int wy = 1;
        int[][] quadrants = {{1, 1}, {-1, 1}, {-1, -1}, {1, -1}};
        for (int i = 0; i < input.length; i++) {
            int quadrant;
            if (wx > 0 && wy > 0)
                quadrant = 0;
            else if (wx > 0)
                quadrant = 3;
            else if (wy > 0)
                quadrant = 1;
            else
                quadrant = 2;
            int prev = quadrant; // Store previous quadrant for 'L' and 'R'
            // Decode
            char action = input[i].charAt(0);
            int amt = Integer.parseInt(input[i].substring(1));
            switch (action) {
                case 'N':
                    wy += amt;
                    break;
                case 'S':
                    wy -= amt;
                    break;
                case 'E':
                    wx += amt;
                    break;
                case 'W':
                    wx -= amt;
                    break;
                case 'L':
                    quadrant += amt / 90;
                    while (quadrant > 3)
                        quadrant -= 4;
                    if (Math.abs(quadrant - prev) % 2 == 1) {
                        int tempL = wx;
                        wx = wy;
                        wy = tempL;
                    }
                    wx = Math.abs(wx) * quadrants[quadrant][0];
                    wy = Math.abs(wy) * quadrants[quadrant][1];
                    break;
                case 'R':
                    quadrant -= amt / 90;
                    while (quadrant < 0)
                        quadrant += 4;
                    if (Math.abs(quadrant - prev) % 2 == 1) {
                        int tempL = wx;
                        wx = wy;
                        wy = tempL;
                    }
                    wx = Math.abs(wx) * quadrants[quadrant][0];
                    wy = Math.abs(wy) * quadrants[quadrant][1];
                    break;
                case 'F':
                    sx += wx * amt;
                    sy += wy * amt;
                    break;
                default:
                    System.out.println("Error: Invalid action");
                    break;
            }
        }
        System.out.println("Part 2: " + (Math.abs(sx) + Math.abs(sy)));
    }

}
