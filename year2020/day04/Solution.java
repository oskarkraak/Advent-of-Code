package AdventOfCode.year2020.day04;

import AdventOfCode.Reader;

public class Solution {

    public static void main(String[] args) {
        String[] input = Reader.readFile("./AdventOfCode/year2020/day04/input.txt");
        part1(input);
        part2(input);
    }

    static void part1(String[] input) {
        String[] keys = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid"};
        boolean[] keysFound = {false, false, false, false, false, false, false, false};
        int valid = 0;

        for (String s : input) {
            if (s.equals("")) {
                // check if previously read passport is valid
                if (keysFound[0] && keysFound[1] && keysFound[2] && keysFound[3] && keysFound[4] && keysFound[5] && keysFound[6])
                    valid++;

                for (int i = 0; i < keysFound.length; i++)
                    keysFound[i] = false;
            } else {
                int i = 0;
                for (String k : keys) {
                    // add ":" to make sure the string is the key (not the value)
                    if (s.contains(k + ":"))
                        keysFound[i] = true;
                    i++;
                }
            }
        }

        // if last passport has not been checked
        if (!input[input.length - 1].equals("")) {
            // check if last passport is valid
            if (keysFound[0] && keysFound[1] && keysFound[2] && keysFound[3] && keysFound[4] && keysFound[5] && keysFound[6])
                valid++;
        }

        System.out.println("There are " + valid + " valid passports for part one.");
    }

    static void part2(String[] input) {
        String[] keys = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid"};
        boolean[] validFields = {false, false, false, false, false, false, false, false};
        int valid = 0;

        for (String s : input) {
            // empty line
            if (s.equals("")) {
                //check if previously read passport is valid
                if (validFields[0] && validFields[1] && validFields[2] && validFields[3] && validFields[4] && validFields[5] && validFields[6])
                    valid++;

                for (int i = 0; i < validFields.length; i++)
                    validFields[i] = false;
            } else {
                int i = 0;
                for (String k : keys) {
                    // add ":" to make sure the string is the key (not the value)
                    if (s.contains(k + ":")) {
                        String value = "";
                        // skip the key; only read the value
                        int index = s.indexOf(k + ":") + 4;
                        while (s.charAt(index) != ' ' && index < s.length() - 1) {
                            value = value + s.charAt(index);
                            index++;
                            if (index == s.length() - 1) {
                                value = value + s.charAt(s.length() - 1);
                            }
                        }

                        if (valueIsValid(k, value))
                            validFields[i] = true;
                    }
                    i++;
                }
            }
        }

        // if last passport has not been checked
        if (!input[input.length - 1].equals("")) {
            // check if last passport is valid
            if (validFields[0] && validFields[1] && validFields[2] && validFields[3] && validFields[4] && validFields[5] && validFields[6])
                valid++;
        }

        System.out.println("There are " + valid + " valid passports for part two.");
    }

    static boolean valueIsValid(String k, String value) {
        switch (k) {
            case "byr":
                int byr = Integer.parseInt(value);
                if (value.length() == 4 && byr >= 1920 && byr <= 2002)
                    return true;
                break;

            case "iyr":
                int iyr = Integer.parseInt(value);
                if (value.length() == 4 && iyr >= 2010 && iyr <= 2020)
                    return true;
                break;

            case "eyr":
                int eyr = Integer.parseInt(value);
                if (value.length() == 4 && eyr >= 2020 && eyr <= 2030)
                    return true;
                break;

            case "hgt":
                if (value.length() == 5 && value.charAt(3) == 'c' && value.charAt(4) == 'm') {
                    try {
                        int hgt = value.charAt(0) + value.charAt(1) + value.charAt(2);
                        if (hgt >= 150 && hgt <= 193)
                            return true;
                    } catch (Exception e) {
                        return false;
                    }
                } else if (value.length() == 4 && value.charAt(2) == 'i' && value.charAt(3) == 'n') {
                    try {
                        int x = value.charAt(0) - '0';
                        int y = value.charAt(1) - '0';
                        int hgt = Integer.parseInt(new String(x + "" + y));
                        if (hgt >= 59 && hgt <= 76)
                            return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
                break;

            case "hcl":
                if (value.length() == 7 && value.charAt(0) == '#') {
                    String allowed = "0123456789abcdef";
                    for (int i = 1; i < value.length(); i++) {
                        if (!allowed.contains(Character.toString(value.charAt(i))))
                            return false;
                    }
                    return true;
                }
                break;

            case "ecl":
                String[] allowed = {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};
                for (String s : allowed) {
                    if (s.equals(value))
                        return true;
                }
                break;

            case "pid":
                if (value.length() == 9) {
                    // if convertion to int via Integer.parseInt() does not throw an error, the value must be an integer
                    try {
                        int hgt = Integer.parseInt(value);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
                break;
        }
        return false;
    }

}

