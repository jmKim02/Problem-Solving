package leetcode;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/two-sum/description/
Runtime: 2ms    | Beats: 98.97%
Memory: 44.78MB | Beats: 89.29%
 */

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }

        return null;
    }
}
