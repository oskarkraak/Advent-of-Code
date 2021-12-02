package AdventOfCode.year2021.day02;
import AdventOfCode.Reader;

public class Solution {
  
  public static void main(String[] args) {
    String[] input = Reader.readFile("./AdventOfCode/year2021/day02/input.txt");
    part1(input);
    part2(input);
  }
  
  public static void part1(String[] input) {
    int horizontalPosition = 0, depth = 0;
    for (String s : input) {
      // The number is always the last character; extract it
      int add = s.charAt(s.length()-1) - '0';
      if (s.charAt(0) == 'f')
        // Forward
        horizontalPosition += add;
      else if (s.charAt(0) == 'd') 
        // Down
        depth += add;
      else if (s.charAt(0) == 'u')
        // Up
        depth -= add;
    }
    
    System.out.println("Part 1: " + horizontalPosition*depth);
  }
  
  public static void part2(String[] input) {
    int horizontalPosition = 0, depth = 0, aim = 0;
    for (String s : input) {
      // The number is always the last character; extract it
      int add = s.charAt(s.length()-1) - '0';
      if (s.charAt(0) == 'f') {
        // Forward
        horizontalPosition += add;
        depth += aim*add;
      } else if (s.charAt(0) == 'd') 
        // Down
        aim += add;
      else if (s.charAt(0) == 'u')
        // Up
        aim -= add;
    }
    
    System.out.println("Part 1: " + horizontalPosition*depth);
  }
  
}
