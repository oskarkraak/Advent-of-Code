package AdventOfCode.year2021;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Reader {

    public static String[] readFile(String path) {

        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            ArrayList<String> a = new ArrayList();
            String line;
            while ((line = in.readLine()) != null) {
                a.add(line);
            }

            String[] input = new String[a.size()];
            for (int i = 0; i < a.size(); i++) {
                input[i] = a.get(i);
            }

            return input;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static int[] parseIntArray(String[] input) {
        int[] numbers = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            numbers[i] = Integer.parseInt(input[i]);
        }
        return numbers;
    }

}

