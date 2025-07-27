package leetcode;

/*
https://leetcode.com/problems/trapping-rain-water/
Runtime: 0ms    | Beats: 100.00%
Memory: 46.6MB  | Beats: 28.07%
 */

public class TrappingRainWater {
    public int trap(int[] height) {
        int totalWater = 0;
        int left = 0;
        int right = height.length - 1;
        int leftMax = height[left];
        int rightMax = height[right];

        while(left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            if (leftMax <= rightMax) {
                totalWater += (leftMax - height[left]);
                left++;
            } else {
                totalWater += (rightMax - height[right]);
                right--;
            }
        }

        return totalWater;
    }
}
