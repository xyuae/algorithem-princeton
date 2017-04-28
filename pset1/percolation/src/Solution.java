import java.util.*;
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { i, map.get(complement) };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
    public static void main(String arg[]) {
        Solution s = new Solution();
        int[] nums = new int[] {2, 7, 11, 15};
        int target = 9;
        int[] answer = s.twoSum(nums, target);
        for (int i = 0; i < answer.length; i++) {
            System.out.printf(answer[i]+" ");
        }
        
    }
}