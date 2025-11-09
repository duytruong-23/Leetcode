import java.util.HashMap;
import java.util.Map;

public class Solution {
    Map<Integer, Integer> valueToIndexMap = new HashMap<>();

    public int longestConsecutive(int[] nums) {
        DSU dsu = new DSU(nums.length);

        for (int i = 0; i < nums.length; i++) {
            if (valueToIndexMap.containsKey(nums[i])) {
                continue;
            }

            valueToIndexMap.put(nums[i], i);

            if (valueToIndexMap.containsKey(nums[i] - 1)) {
                dsu.union(i, valueToIndexMap.get(nums[i] - 1));
            }

            if (valueToIndexMap.containsKey(nums[i] + 1)) {
                dsu.union(i, valueToIndexMap.get(nums[i] + 1));
            }
        }

        return dsu.getMaxSize();
    }
}

class DSU {
    int[] parent;
    int[] size;

    public DSU(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return;
        }

        if (size[rootX] < size[rootY]) {
            parent[rootX] = rootY;
            size[rootY] += size[rootX];
        } else {
            parent[rootY] = rootX;
            size[rootX] += size[rootY];
        }
    }

    public int getMaxSize() {
        int maxSize = 0;
        for (int s : size) {
            if (s > maxSize) {
                maxSize = s;
            }
        }
        return maxSize;
    }
}