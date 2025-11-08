
// Disjoint Set Union (DSU) data structure to manage connected components and process queries

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution2 {
    private int[] parent;
    private int[] size;
    Map<Integer, PriorityQueue<Integer>> connMap = new HashMap<>();

    private int find(int node) {
        if (parent[node] != node) {
            parent[node] = find(parent[node]);
        }
        return parent[node];
    }

    private void union(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);

        if (rootU == rootV)
            return;

        PriorityQueue<Integer> pqU = connMap.get(rootU);
        PriorityQueue<Integer> pqV = connMap.get(rootV);

        if (size[rootU] < size[rootV]) {
            parent[rootU] = rootV;
            size[rootV] += size[rootU];
            while (!pqU.isEmpty()) {
                pqV.offer(pqU.poll());
            }
            connMap.remove(rootU);
        } else {
            parent[rootV] = rootU;
            size[rootU] += size[rootV];
            while (!pqV.isEmpty()) {
                pqU.offer(pqV.poll());
            }
            connMap.remove(rootV);
        }
    }

    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        parent = new int[c];
        size = new int[c];
        boolean[] onlineStatus = new boolean[c];
        for (int i = 0; i < c; i++) {
            parent[i] = i;
            size[i] = 1;
            onlineStatus[i] = true;
            connMap.put(i, new PriorityQueue<>());
            connMap.get(i).offer(i);
        }

        for (int[] conn : connections) {
            int u = conn[0] - 1;
            int v = conn[1] - 1;
            union(u, v);
        }

        List<Integer> results = new java.util.ArrayList<>();
        for (int[] query : queries) {
            int type = query[0];
            int node = query[1] - 1;

            if (type == 1) {

                if (onlineStatus[node]) {
                    results.add(node + 1);
                    continue;
                }

                int root = find(node);
                PriorityQueue<Integer> pq = connMap.get(root);
                if (!pq.isEmpty()) {
                    while (!pq.isEmpty() && !onlineStatus[pq.peek()]) {
                        pq.poll();
                    }
                    if (!pq.isEmpty()) {
                        results.add(pq.peek() + 1);
                    } else {
                        results.add(-1);
                    }
                } else {
                    results.add(-1);
                }
            } else if (type == 2) {
                onlineStatus[node] = false;
            }
        }

        return results.stream().mapToInt(Integer::intValue).toArray();
    }

}
