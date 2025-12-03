import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution2 {
    private static final char REGION = 'O';
    private static final char NON_REGION = 'X';

    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }

        int numRows = board.length;
        int numCols = board[0].length;

        boolean[][] visited = new boolean[numRows][numCols];

        for (int col = 0; col < numCols; col++) {
            dfs(board, 0, col, visited);
            dfs(board, numRows - 1, col, visited);
        }

        for (int row = 0; row < numRows; row++) {
            dfs(board, row, 0, visited);
            dfs(board, row, numCols - 1, visited);
        }

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (!visited[row][col] && board[row][col] == REGION) {
                    board[row][col] = NON_REGION;
                }
            }
        }

    }

    private void dfs(char[][] board, int cellIndex, boolean[] visited, boolean[] isConnectedToBorder,
            Map<Integer, Integer> indexToGroupIdMap,
            Map<Integer, List<Integer>> groupIdToPathMap,
            int groupId) {
        if (cellIndex < 0 || cellIndex >= board.length * board[0].length || visited[cellIndex]) {
            return;
        }
        visited[cellIndex] = true;
        int numRows = board.length;
        int numCols = board[0].length;
        int row = cellIndex / numCols;
        int col = cellIndex % numCols;

        if (board[row][col] != REGION) {
            return;
        }

        dfs(board, row + 1, col, visited);
        dfs(board, row - 1, col, visited);
        dfs(board, row, col + 1, visited);
        dfs(board, row, col - 1, visited);
    }
}
