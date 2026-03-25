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

    // Depth-First Search to mark connected 'O's as visited
    // Time Complexity: O(m * n) where m is the number of rows and n is the number
    // of columns
    // Space Complexity: O(m * n) in the worst case when all cells are 'O'
    private void dfs(char[][] board, int row, int col, boolean[][] visited) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || visited[row][col]) {
            return;
        }

        visited[row][col] = true;

        if (board[row][col] == NON_REGION) {
            return;
        }

        dfs(board, row + 1, col, visited);
        dfs(board, row - 1, col, visited);
        dfs(board, row, col + 1, visited);
        dfs(board, row, col - 1, visited);
    }
}
