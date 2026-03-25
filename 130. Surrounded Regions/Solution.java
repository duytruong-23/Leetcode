class Solution {
    private static final char REGION = 'O';
    private static final char NON_REGION = 'X';

    private int[] parent;
    private boolean[] isConnectedToBorder;

    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }

        int numRows = board.length;
        int numCols = board[0].length;
        int totalCells = numRows * numCols;
        parent = new int[totalCells];
        isConnectedToBorder = new boolean[totalCells];

        for (int i = 0; i < totalCells; i++) {
            parent[i] = i;
            int row = i / numCols;
            int col = i % numCols;
            if (board[row][col] == REGION && (row == 0 || row == numRows - 1 || col == 0 || col == numCols - 1)) {
                isConnectedToBorder[i] = true;
            }
        }

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (board[i][j] == REGION) {
                    if (i < numRows - 1 && board[i + 1][j] == REGION) {
                        union(i * numCols + j, (i + 1) * numCols + j);
                    }
                    if (j < numCols - 1 && board[i][j + 1] == REGION) {
                        union(i * numCols + j, i * numCols + (j + 1));
                    }
                }
            }
        }

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (board[i][j] == REGION) {
                    int root = find(i * numCols + j);
                    if (!isConnectedToBorder[root]) {
                        board[i][j] = NON_REGION;
                    }
                }
            }
        }

    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    private void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return;
        }

        parent[rootY] = rootX;
        isConnectedToBorder[rootX] = isConnectedToBorder[rootX] || isConnectedToBorder[rootY];
    }
}