package one_dimention;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * CodingNinjas: Maximum sum of non-adjacent elements
 * 
 * Link:
 * https://www.codingninjas.com/studio/problems/maximum-sum-of-non-adjacent-elements_843261
 * 
 */
public class P3_Maximum_Sum_Of_Non_Adjacent_Elements_In_Array {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 1, 3, 5, 8, 1, 9 };
        ArrayList<Integer> nums = new ArrayList<Integer>();
        for (Integer it : arr) {
            nums.add(it);
        }
        int maxSum = maximumNonAdjacentSum(nums);
        System.out.println("Maximum sum of Non-adjacent elements in array " + nums + " is : " + maxSum);
    }

    private static int maximumNonAdjacentSum(ArrayList<Integer> nums) {
        // return maximumNonAdjacentSumRecursive(nums, nums.size() - 1);
        int[] dp = new int[nums.size()];
        Arrays.fill(dp, -1);
        // return maximumNonAdjacentSumMemoization(nums, nums.size() - 1, dp);
        // return maximumNonAdjacentSumTabulation(nums, nums.size() - 1, dp);
        return maximumNonAdjacentSumSpaceOptimization(nums, nums.size());
    }

    /**
     * Using Recursion
     * 
     * TC: O(2^N)
     * SC: O(N)
     * 
     * @param nums
     * @param n
     * @return
     */
    private static int maximumNonAdjacentSumRecursive(ArrayList<Integer> nums, int n) {
        if (n == 0 || n == 1) {
            return nums.get(n);
        }
        if (n < 0) {
            return 0;
        }
        // pick
        int pick = Integer.MIN_VALUE;
        if (n > 1) {
            pick = nums.get(n) + maximumNonAdjacentSumRecursive(nums, n - 2);
        }
        // not pick
        int notpick = 0 + maximumNonAdjacentSumRecursive(nums, n - 1);
        return Math.max(pick, notpick);
    }

    /**
     * Using Memoization - Top Down Approach
     * TC: O(N)
     * SC: O(N) for recursion stack + O(N) for dp Array ~ O(N)
     * 
     * @param nums
     * @param n
     * @param dp
     * @return
     */
    private static int maximumNonAdjacentSumMemoization(ArrayList<Integer> nums, int n, int[] dp) {
        if (n == 0) {
            return nums.get(n);
        }
        if (n < 0) {
            return 0;
        }
        if (dp[n] != -1) {
            return dp[n];
        }
        // pick
        int pick = nums.get(n) + maximumNonAdjacentSumMemoization(nums, n - 2, dp);
        // not pick
        int notpick = 0 + maximumNonAdjacentSumMemoization(nums, n - 1, dp);
        dp[n] = Math.max(pick, notpick);
        return dp[n];
    }

    /**
     * Using Tabulation - Bottom Up Approach
     * 
     * TC: O(N)
     * SC: O(N)
     * 
     * @param nums
     * @param n
     * @param dp
     * @return
     */
    private static int maximumNonAdjacentSumTabulation(ArrayList<Integer> nums, int n, int[] dp) {
        dp[0] = nums.get(0);
        for (int i = 1; i <= n; i++) {
            // pick
            int pick = i > 1 ? nums.get(i) + dp[i - 2] : nums.get(i);
            // not pick
            int notpick = dp[i - 1];
            dp[i] = Math.max(pick, notpick);
        }
        return dp[n];
    }

    /**
     *
     * Using Space Optimization
     * 
     * TC: O(N)
     * SC: O(1)
     * 
     * @param nums
     * @param n
     * @return
     */
    private static int maximumNonAdjacentSumSpaceOptimization(ArrayList<Integer> nums, int n) {
        int prev2 = 0;
        int prev1 = 0;
        int current = 0;
        for (int i = 0; i < n; i++) {
            // pick
            int pick = nums.get(i) + prev2;
            // not pick
            int notpick = prev1;
            current = Math.max(pick, notpick);
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }
}
