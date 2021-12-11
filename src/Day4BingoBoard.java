import java.util.List;

public class Day4BingoBoard {
    List<List<Integer>> board;
    int[] totalCountForCols = new int[5];
    int[] totalCountForRows = new int[5];
    int boardId;

    public Day4BingoBoard(List<List<Integer>> board, int boardId) {
        this.board = board;
        this.boardId = boardId;
        int rowCount = 0;
        int colCount;
        for (List<Integer> row : board) {
            colCount = 0;
            for (Integer n : row) {
                totalCountForRows[rowCount] += n;
                totalCountForCols[colCount] += n;
                colCount++;
            }
            rowCount++;
        }
    }

    public int getBoardId() {
        return this.boardId;
    }

    private boolean isBingo(int row, int col) {
        return totalCountForCols[col] == 0 || totalCountForRows[row] == 0;
    }

    public boolean isBingo() {
        for (int i = 0; i < 5; i++) {
            if (isBingo(i,0)) {
                return true;
            }
        }
        for (int j = 0; j < 5; j++) {
            if (isBingo(0,j)) {
                return true;
            }
        }

        return false;
    }

    public int processGameInput(List<Integer> input) {
        int callNumber = 0;

        for (Integer v : input) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (board.get(i).get(j).equals(v)) {
                        boolean wasBingo = markBoard(i,j,v);
                        if (wasBingo) {
                            System.out.println("Bingo with board " + boardId);
                            System.out.println("Got bingo on call number: " + callNumber + " with this v " + v);
                            System.out.println("The board has this much left " + boardUnmarkedSum());
                            return callNumber;
                        }
                    }
                }
            }
            callNumber++;
        }
        System.out.println("Well I guess board " + boardId + " sucks");
        return callNumber;
    }

    private boolean markBoard(int row, int col, int v) {
        totalCountForRows[row] -= v;
        totalCountForCols[col] -= v;
        return isBingo(row, col);
    }

    public int boardUnmarkedSum() {
        int sum = 0;

        for (int i = 0; i < 5; i++) {
            sum += totalCountForRows[i];
        }
        return sum;
    }
}
