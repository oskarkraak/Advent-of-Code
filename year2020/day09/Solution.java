package AdventOfCode.year2020.day09;
import AdventOfCode.Reader;

import java.util.ArrayList;
import java.util.Collections;

public class Solution {
  
  public static void main(String[] args) {    
    String[] input = Reader.readFile("./AdventOfCode/year2020/day09/input.txt");
    long l = part1(input);
    part2(input, l);
  }
  
  static long part1(String[] input) {
    System.out.println("Part one:");
    
    long[] preamble = new long[25];
    int index = 0;
    int line = 0;
    boolean legit = false;
    long current = 0;
    
    for (String s: input) {
      current = Integer.parseInt(s);
      if (line < preamble.length) {
        // read first lines
        preamble[line] = current;
      } else {
        // check if the current number follows the rule
        legit = false;
        for (int i = 0; i < preamble.length; i++) {
          for (int j = i + 1; j < preamble.length; j++) {
            if (preamble[i] + preamble[j] == current)
              legit = true;
          }
        }
        
        if (! legit) {
          System.out.println(current + " is the first number that does not follow the rule.");
          return current;
        }
        
        // always have the last lines in preamble
        if (index >= preamble.length)
          index = 0;
        preamble[index] = current;
        index++;
      }
      line++;
    }
    
    System.out.println("Everything legit here.");
    return 0;
  }
  
  static void part2(String[] input, long invalid) {
    System.out.println("Part two:");
    
    ArrayList<Long> set = new ArrayList<Long>(); // contiguous set of numbers
    long current = 0;
    long sum = 0;
    
    for (String s: input) {
      current = Integer.parseInt(s);
      if (current >= invalid) {
        // reset
        sum = 0;
        for (int i = 0; i < set.size(); i++)
          set.remove(0);
      } else {
        set.add(current);
        // iterate through until either the set was found or everything was deleted
        for (int i = 0; i < set.size(); i++) {
          sum = 0;
          for (long l: set)
            sum += l;
          
          if (sum > invalid) {
            // delete first element
            set.remove(0);
          } else if (sum == invalid) {
            // print sum of smallest and largest
            Collections.sort(set); // sort set 
            sum = set.get(0) + set.get(set.size()-1);
            System.out.println(sum + " is the sum of the smallest and largest number in the contiguous set.");
            return;
          }
        }
      } 
    }
    
    System.out.println("No set found.");
  }

}
