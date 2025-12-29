import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> resultList = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            resultList.add(generateHelper(resultList, i));
        }

        return resultList;
    }

    private List<Integer> generateHelper(List<List<Integer>> triangle, int i) {
        if (i == 0) {
            return List.of(1);
        }

        if (i == 1) {
            return List.of(1, 1);
        }

        List<Integer> resultList = new ArrayList<>();
        resultList.add(1);
        for (int j = 1; j < i; j++) {
            List<Integer> previousRow = triangle.get(i - 1);
            int element = previousRow.get(j - 1) + previousRow.get(j);
            resultList.add(element);
        }
        resultList.add(1);

        return resultList;
    }
}