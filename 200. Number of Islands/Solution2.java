public class Solution2 {
    private int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int countIslands = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j, n, m);
                    countIslands++;
                }
            }
        }
        return countIslands;
    }

    private void dfs(char[][] grid, int i, int j, int n, int m) {
        if (i < 0 || j < 0 || i >= n || j >= m || grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0'; // Mark as visited by sinking the island

        dfs(grid, i + 1, j, n, m);
        dfs(grid, i - 1, j, n, m);
        dfs(grid, i, j + 1, n, m);
        dfs(grid, i, j - 1, n, m);
    }

    private void dfs2(char[][] grid, int rowIndex, int columnIndex, int numRows, int numCols) {
        if (rowIndex < 0 || columnIndex < 0 || rowIndex >= numRows || columnIndex >= numCols
                || grid[rowIndex][columnIndex] == '0') {
            return;
        }

        grid[rowIndex][columnIndex] = '0'; // Mark as visited by sinking the island

        for (int[] direction : directions) {
            int r = rowIndex + direction[0];
            int c = columnIndex + direction[1];
            dfs2(grid, r, c, numRows, numCols);
        }
    }
}
