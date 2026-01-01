public class Solution2 {
    public boolean isMatch(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        int sIdx = 0;
        int pIdx = 0;
        int star = -1;
        int matchedIdx = 0;
        while (sIdx < sLen) {
            if (pIdx < pLen) {
                if (s.charAt(sIdx) == p.charAt(pIdx) || p.charAt(pIdx) == '?') {
                    sIdx++;
                    pIdx++;
                    continue;
                }

                if (p.charAt(pIdx) == '*') {
                    if (pIdx == pLen - 1) {
                        return true;
                    }

                    star = pIdx;
                    matchedIdx = sIdx;
                    // match zero element case
                    pIdx++;
                    continue;
                }
            }

            if (star != -1) {
                // match at least one case
                matchedIdx++;
                sIdx = matchedIdx;
                pIdx = star + 1;
                continue;
            }

            return false;
        }

        while (pIdx < pLen && p.charAt(pIdx) == '*') {
            pIdx++;
        }

        return pIdx == pLen;
    }

    public static void main(String[] args) {

        String s = "aaaa";
        String p = "***a";
        Solution2 solution2 = new Solution2();

        System.out.println(solution2.isMatch(s, p));
    }

}
