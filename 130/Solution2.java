import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution2 {
    private static final char REGION = 'O';
    private static final char NON_REGION = 'X';
    List<List<Integer>> regionsGraph = new ArrayList<>();

    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }

        int numRows = board.length;
        int numCols = board[0].length;
        int totalCells = numRows * numCols;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                regionsGraph.add(new ArrayList<>());
            }
        }

        boolean[] visited = new boolean[totalCells];
        boolean[] isConnectedToBorder = new boolean[totalCells];

        Map<Integer, Integer> indexToGroupIdMap = new HashMap<>();
        Map<Integer, List<Integer>> groupIdToPathMap = new HashMap<>();
        for (int i = 0; i < totalCells; i++) {

            if (!visited[i]) {
                dfs(board, i, visited, isConnectedToBorder, indexToGroupIdMap, groupIdToPathMap, i);
            }
        }

        for (int groupId = 0; groupId < totalCells; groupId++) {
            if (groupIdToPathMap.containsKey(groupId) && !isConnectedToBorder[groupId]) {
                for (int cellIndex : groupIdToPathMap.get(groupId)) {
                    int row = cellIndex / numCols;
                    int col = cellIndex % numCols;
                    board[row][col] = NON_REGION;
                }
            }
        }

    }

    private void dfs(char[][] board, int cellIndex, boolean[] visited, boolean[] isConnectedToBorder,
            Map<Integer, Integer> indexToGroupIdMap,
            Map<Integer, List<Integer>> groupIdToPathMap,
            int groupId) {
        if (cellIndex >= board.length * board[0].length || visited[cellIndex]) {
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

        if (row == 0 || row == numRows - 1 || col == 0 || col == numCols - 1) {
            isConnectedToBorder[groupId] = true;
        }

        indexToGroupIdMap.put(cellIndex, groupId);
        groupIdToPathMap.computeIfAbsent(groupId, k -> new ArrayList<>()).add(cellIndex);

        dfs(board, (row + 1) * numCols + col, visited, isConnectedToBorder, indexToGroupIdMap, groupIdToPathMap,
                groupId);
        dfs(board, row * numCols + col + 1, visited, isConnectedToBorder, indexToGroupIdMap, groupIdToPathMap,
                groupId);
        dfs(board, (row - 1) * numCols + col, visited, isConnectedToBorder, indexToGroupIdMap, groupIdToPathMap,
                groupId);
        dfs(board, row * numCols + col - 1, visited, isConnectedToBorder, indexToGroupIdMap, groupIdToPathMap,
                groupId);
    }
}
