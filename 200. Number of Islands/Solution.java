class Solution {
    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        DSU dsu = new DSU(grid);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    int currentIndex = i * m + j;

                    // Check right neighbor
                    if (j + 1 < m && grid[i][j + 1] == '1') {
                        int rightIndex = i * m + (j + 1);
                        dsu.union(currentIndex, rightIndex);
                    }

                    // Check down neighbor
                    if (i + 1 < n && grid[i + 1][j] == '1') {
                        int downIndex = (i + 1) * m + j;
                        dsu.union(currentIndex, downIndex);
                    }
                }
            }
        }

        return dsu.getCountIslands();
    }
}

class DSU {
    private int[] parent;
    private int[] size;
    private int countIslands;

    public DSU(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        parent = new int[n * m];
        size = new int[n * m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int index = i * m + j;
                parent[index] = index;
                size[index] = 1;
                if (grid[i][j] == '1') {
                    countIslands++;
                }
            }
        }
    }

    public int find(int u) {
        if (parent[u] != u) {
            parent[u] = find(parent[u]);
        }
        return parent[u];
    }

    public void union(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);

        if (rootU == rootV) {
            return;
        }

        if (size[rootU] < size[rootV]) {
            parent[rootU] = rootV;
            size[rootV] += size[rootU];
        } else {
            parent[rootV] = rootU;
            size[rootU] += size[rootV];
        }
        countIslands--;
    }

    public int getCountIslands() {
        return countIslands;
    }
}