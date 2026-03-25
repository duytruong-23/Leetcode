class Solution {
    // Time Complexity: O(n1 * n2), where n1 and n2 are the lengths of nums1 and
    // nums2 respectively.
    // Space Complexity: O(n1), where n1 is the length of nums1, as we are storing
    // the result in an array of size n1.
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;

        int[] result = new int[n1];

        for (int i = 0; i < n1; i++) {
            int greaterElement = -1;
            boolean isFound = false;
            for (int j = 0; j < n2; j++) {
                if (nums1[i] == nums2[j]) {
                    isFound = true;
                    continue;
                }

                if (isFound && nums1[i] < nums2[j]) {
                    greaterElement = nums2[j];
                    break;
                }
            }

            result[i] = greaterElement;
        }

        return result;
    }
}