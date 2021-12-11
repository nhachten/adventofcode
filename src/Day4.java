import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day4 {

    static void day4part1(List<Day4BingoBoard> boards, List<Integer> nums) {
        Day4BingoBoard best = null;
        Day4BingoBoard worst = null;
        int bestBoardCount = 9999999;
        int bestBoard = -1;
        int worstBoardCount = 0;
        int worstBoard = -1;
        for (Day4BingoBoard board : boards) {
            int t = board.processGameInput(nums);
            if (t < bestBoardCount) {
                bestBoardCount = t;
                bestBoard = board.getBoardId();
                best = board;
            }
            if (t > worstBoardCount) {
                worstBoardCount = t;
                worstBoard = board.getBoardId();
                worst = board;
            }
        }
        System.out.println("Best board: " + bestBoard + " with count " + bestBoardCount);
        assert best != null;
        System.out.println("Left unamrked: " + best.boardUnmarkedSum() + " last hit: " + nums.get(bestBoardCount));
        System.out.println("Best input: " + best.boardUnmarkedSum() * nums.get(bestBoardCount));
        System.out.println("Worst board: " + worstBoard + " with count " + worstBoardCount);
        assert worst != null;
        System.out.println("Left unamrked: " + worst.boardUnmarkedSum() + " last hit: " + nums.get(worstBoardCount));
        System.out.println("Best input: " + worst.boardUnmarkedSum() * nums.get(worstBoardCount));

    }
    public static void main(String[] args) {

        ArrayList<char[]> data = new ArrayList<>();
        File file = new File(args[0]);
        try (
                BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            /* get first line b/c it is bingo input */
            line = br.readLine();
            List<Integer> nums = Arrays.stream(line.split(","))
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
            System.out.println(nums.get(0));
            int bingoRowCount = 0;
            int boardNum = 0;
            List<List<Integer>> curBoard = new ArrayList<>();
            List<Day4BingoBoard> boardList = new ArrayList<>();
            while ((line = br.readLine()) != null) {

                if (line.isBlank()) {

                    bingoRowCount = 0;
                    boardNum++;
                    curBoard = new ArrayList<>();
                    continue;
                }

                //System.out.println("bingo board " + boardNum + "bingo row" + bingoRowCount + " " + line);
                try {
                    curBoard.add(Arrays.stream(line.trim().split("\\s+"))
                            .map(Integer::valueOf)
                            .collect(Collectors.toList()));
                } catch (NumberFormatException e) {
                    System.out.println(Arrays.toString(e.getStackTrace()));
                }

                bingoRowCount++;
                if (bingoRowCount == 5) {
                    boardList.add(new Day4BingoBoard(curBoard, boardNum));
                }

            }
            day4part1(boardList, nums);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("done");
    }
}
