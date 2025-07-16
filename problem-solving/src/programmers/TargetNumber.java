package programmers;

// https://school.programmers.co.kr/learn/courses/30/lessons/43165

public class TargetNumber {
    public int solution(int[] numbers, int target) {
        return calc(numbers, target, 0, 0);
    }

    public int calc(int[] nums, int target, int idx, int sum) {
        if (nums.length == idx) {
            if (sum == target) {
                return 1;
            }
            return 0;
        }

        return calc(nums, target, idx + 1, sum + nums[idx])
                + calc(nums, target, idx + 1, sum - nums[idx]);
    }
}
