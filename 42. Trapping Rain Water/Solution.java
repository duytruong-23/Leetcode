class Solution {
    // Bruce force
    public int trap(int[] height) {
        int n = height.length;
        int result = 0;
        for (int i = 0; i < n; i++) {
            int leftMax = 0;
            for (int j = 0; j < i; j++) {
                leftMax = Math.max(leftMax, height[j]);
            }

            int rightMax = 0;
            for (int j = i + 1; j < n; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }

            int baseHeight = Math.min(leftMax, rightMax);
            if (baseHeight > height[i]) {
                result += baseHeight - height[i];
            }
        }

        return result;
    }

    public int trapOptimizedVersion(int[] height) {
        int n = height.length;
        int left = 0;
        int right = n - 1;
        int leftMax = 0;
        int rightMax = 0;
        int result = 0;
        while (left <= right) {
            if (height[left] <= height[right]) {
                if (leftMax > height[left]) {
                    result += leftMax - height[left];
                } else {
                    leftMax = height[left];
                }
                left++;
            } else {
                if (rightMax > height[right]) {
                    result += rightMax - height[right];
                } else {
                    rightMax = height[right];
                }
                right--;
            }
        }

        return result;
    }
}