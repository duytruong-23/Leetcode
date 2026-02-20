
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer> getRow(int rowIndex) {
        return getRowRecursion(rowIndex);
    }

    // Time: O(n)
    private List<Integer> getRowRecursion(int rowIndex) {
        if (rowIndex == 0) {
            return List.of(1);
        }

        if (rowIndex == 1) {
            return List.of(1, 1);
        }

        List<Integer> prevRow = getRowRecursion(rowIndex - 1);
        List<Integer> row = new ArrayList<>();
        int n = rowIndex + 1;
        row.add(1);
        for (int i = 1; i < n - 1; i++) {
            row.add(prevRow.get(i - 1) + prevRow.get(i));
        }
        row.add(1);
        return row;
    }

    private List<Integer> getRowWithoutRecursion(int rowIndex) {
        List<List<Integer>> rows = new ArrayList<>();
        rows.add(List.of(1));
        rows.add(List.of(1, 1));
        if (rowIndex == 0 || rowIndex == 1) {
            return rows.get(rowIndex);
        }

        for (int i = 2; i <= rowIndex; i++) {
            List<Integer> prevRow = rows.get(i - 1);
            List<Integer> row = new ArrayList<>();
            row.add(1);
            for (int j = 1; j <= i - 1; j++) {
                row.add(prevRow.get(j - 1) + prevRow.get(j));
            }
            row.add(1);
            rows.add(row);
        }

        return rows.get(rowIndex);
    }
}