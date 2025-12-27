import java.util.Arrays;

class Solution {
    public String longestCommonPrefix(String[] strs) {
        String result = "";
        int n = strs.length;
        int m = strs[0].length();
        for (int i = 0; i < m; i++) {
            String prefix = strs[0].substring(0, i + 1);
            boolean isSamePrefix = true;
            for (int j = 1; j < n; j++) {
                if (i + 1 > strs[j].length()) {
                    isSamePrefix = false;
                    break;
                }

                String checkedPrefix = strs[j].substring(0, i + 1);
                if (!checkedPrefix.equals(prefix)) {
                    isSamePrefix = false;
                    break;
                }
            }

            if (isSamePrefix) {
                result = prefix;
            } else {
                break;
            }
        }

        return result;
    }

    public String longestCommonPrefix2(String[] strs) {
        StringBuilder resultBuilder = new StringBuilder();
        Arrays.sort(strs);
        int n = strs.length;
        String firstStr = strs[0];
        String lastStr = strs[n - 1];
        int m = Math.min(firstStr.length(), lastStr.length());
        for (int i = 0; i < m; i++) {
            if (firstStr.charAt(i) != lastStr.charAt(i)) {
                return resultBuilder.toString();
            }

            resultBuilder.append(firstStr.charAt(i));
        }

        return resultBuilder.toString();
    }
}