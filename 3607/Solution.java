import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

// DFS to build connected components and process queries
class Solution {

    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < c; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] conn : connections) {
            int u = conn[0] - 1;
            int v = conn[1] - 1;
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        Map<Integer, Integer> nodeToIdPathMap = new HashMap<>();
        Map<Integer, TreeSet<Integer>> idPathToPathMap = new HashMap<>();
        boolean[] visited = new boolean[c];
        for (int i = 0; i < c; i++) {
            if (!visited[i]) {
                dfsBuildPathMap(graph, i, i, visited, nodeToIdPathMap, idPathToPathMap);
            }
        }

        List<Integer> results = new ArrayList<>();
        for (int[] query : queries) {
            int type = query[0];
            int node = query[1] - 1;
            int idPath = nodeToIdPathMap.get(node);
            TreeSet<Integer> pathNodes = idPathToPathMap.get(idPath);
            if (type == 1) {

                if (pathNodes.contains(node)) {
                    results.add(node + 1);
                } else if (!pathNodes.isEmpty()) {
                    results.add(pathNodes.first() + 1);
                } else {
                    results.add(-1);
                }
            } else if (type == 2) {
                pathNodes.remove(node);
            }
        }

        return results.stream().mapToInt(Integer::intValue).toArray();
    }

    private void dfsBuildPathMap(List<List<Integer>> graph, int id, int node, boolean[] visited,
            Map<Integer, Integer> nodeToIdPathMap,
            Map<Integer, TreeSet<Integer>> idPathToPathMap) {
        visited[node] = true;
        nodeToIdPathMap.put(node, id);
        idPathToPathMap.putIfAbsent(id, new TreeSet<>(Comparator.naturalOrder()));
        idPathToPathMap.get(id).add(node);
        List<Integer> neighbors = graph.get(node);
        for (int neighbor : neighbors) {
            if (!visited[neighbor]) {
                dfsBuildPathMap(graph, id, neighbor, visited, nodeToIdPathMap, idPathToPathMap);
            }
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int c = 5;
        int[][] connections = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 } };
        int[][] queries = { { 1, 3 }, { 2, 1 }, { 1, 1 }, { 2, 2 }, { 1, 2 } };
        int[] result = sol.processQueries(c, connections, queries);
        for (int res : result) {
            System.out.print(res + " ");
        }
    }
}