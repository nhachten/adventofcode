import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class Day3 {


    static void part1(ArrayList<char[]> data) {
        int col_num;
        int[] ones_by_col = new int[data.get(0).length];
        for (char[] row : data) {
            col_num = 0;
            for (char c : row) {
                if (c == '1') {
                    ones_by_col[col_num]++;
                }
                col_num++;
            }
        }
        StringBuilder gamma = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();

        int tot = data.size();

        for (int j : ones_by_col) {
            if (j >= tot / 2) {
                gamma.append('1');
                epsilon.append('0');
            } else {
                gamma.append('0');
                epsilon.append('1');
            }
        }
        System.out.println(gamma);
        int a = Integer.parseInt(gamma.toString(),2);
        int b = Integer.parseInt(epsilon.toString(),2);
        System.out.println(a);
        System.out.println(b);
        System.out.println(a*b);




    }

    static ArrayList<char[]> parseDataRows(int colNum, boolean keepMost, ArrayList<char[]> data) {
        int[] ones_by_col = new int[data.get(0).length];
        int col_num;
        for (char[] row : data) {
            col_num = 0;
            for (char c : row) {
                if (c == '1') {
                    ones_by_col[col_num]++;
                }
                col_num++;
            }
        }

        int rows = data.size();
        int cols = ones_by_col.length;

        if (colNum >= cols || rows == 1) {
            return data;
        }

        ArrayList<char[]> filteredList;

        int ones = ones_by_col[colNum];
        int zeroes = rows - ones;
        char numToKeep;
        /* the number 1 is most common or tied, and this is for O2 so keep
        the most common number, tie goes to number 1 */
        if (keepMost) {
            if (ones >= zeroes) {
                numToKeep = '1';
            /* the number 0 is most common, and this is for O2 so
            keep the most common number
             */
            } else {
                numToKeep = '0';
            /* the number 1 is most common, and this is CO2 so keep
            the least common number, tie goes to number 0
             */
            } 
        } else {
            if (ones >= zeroes) {
                numToKeep = '0';
            /* the number 0 is most common, and this is CO2 so keep
            the least common number
             */
            } else {
                numToKeep = '1';
            }
        }


        char finalNumToKeep = numToKeep;
        filteredList = (ArrayList<char[]>) data.stream()
            .filter(row -> row[colNum] == finalNumToKeep)
            .collect(Collectors.toList());

        return parseDataRows(colNum +1, keepMost, filteredList);

    }


    static void part2(ArrayList<char[]> data) {

        String o2GenRating = new String(parseDataRows(0, true, data).get(0));
        String  co2ScrubRating = new String(parseDataRows(0, false, data).get(0));

        int a = Integer.parseInt(o2GenRating,2);
        int b = Integer.parseInt(co2ScrubRating,2);
        System.out.println(o2GenRating);
        System.out.println(co2ScrubRating);
        System.out.println(Integer.parseInt(o2GenRating,2));
        System.out.println(Integer.parseInt(co2ScrubRating,2));
        System.out.println(a*b);

    }


    public static void main(String[] args) {
        ArrayList<char[]> data = new ArrayList<>();
        File file = new File(args[0]);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                // process the line.
                data.add(line.toCharArray());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        part2(data);
    }
}