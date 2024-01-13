package one_dimention;

import java.util.Arrays;

/**
 * 
 * CodingNinjas: Frog Jump
 * 
 * Link: https://www.codingninjas.com/studio/problems/frog-jump_3621012
 * 
 */
public class P2_Minimum_Energy_While_Frog_Jumps {
    public static void main(String[] args) {
        int[] heights = { 7, 4, 4, 2, 6, 6, 3, 4 };
        int n = 8;
        int minEnergy = frogJump(n, heights);
        System.out.println("Minimum energy need for the Frog to jump from 1st to " + n + "th stair : " + minEnergy);
    }

    private static int frogJump(int n, int[] heights) {
        // return frogJumpRecursion(n - 1, heights);
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        // return frogJumpMemoization(n - 1, heights, dp);
        // return frogJumpTabulation(n - 1, heights, dp);
        return frogJumpOptimization(n - 1, heights);
    }

    /**
     * Using Recursion
     * 
     * TC: O(2^N)
     * SC: O(N)
     * 
     * @param n
     * @param heights
     * @return
     */
    private static int frogJumpRecursion(int n, int[] heights) {
        if (n == 0) {
            return 0;
        }
        int twostep = Integer.MAX_VALUE;
        int onestep = frogJump(n - 1, heights) + Math.abs(heights[n] - heights[n - 1]);
        if (n > 1) {
            twostep = frogJump(n - 2, heights) + Math.abs(heights[n] - heights[n - 2]);
        }
        return Math.min(onestep, twostep);
    }

    /**
     * Using Memoization - Top Down Approach
     * TC: O(N)
     * SC: O(N) for recursion stack + O(N) for dp Array ~ O(N)
     * 
     * @param n
     * @param heights
     * @param dp
     * @return
     */
    private static int frogJumpMemoization(int n, int[] heights, int[] dp) {
        if (n == 0) {
            return 0;
        }
        if (dp[n] != -1) {
            return dp[n];
        }
        int onestep = Integer.MAX_VALUE;
        int twostep = Integer.MAX_VALUE;
        onestep = frogJumpMemoization(n - 1, heights, dp) + Math.abs(heights[n] - heights[n - 1]);
        if (n > 1) {
            twostep = frogJumpMemoization(n - 2, heights, dp) + Math.abs(heights[n] - heights[n - 2]);
        }
        dp[n] = Math.min(onestep, twostep);
        return dp[n];
    }

    /**
     * Using Tabulation - Bottom Up Approach
     * 
     * TC: O(N)
     * SC: O(N)
     * 
     * @param n
     * @param heights
     * @param dp
     * @return
     */
    private static int frogJumpTabulation(int n, int[] heights, int[] dp) {
        dp[0] = 0;
        int onestep = Integer.MAX_VALUE;
        int twostep = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            onestep = dp[i - 1] + Math.abs(heights[i] - heights[i - 1]);
            if (i > 1) {
                twostep = dp[i - 2] + Math.abs(heights[i] - heights[i - 2]);
            }
            dp[i] = Math.min(onestep, twostep);
        }
        return dp[n];
    }

    /**
     * Using Space Optimization
     * 
     * TC: O(N)
     * SC: O(1)
     * 
     */
    private static int frogJumpOptimization(int n, int[] heights) {
        int prev2 = 0;
        int prev = 0;
        int current = 0;
        for (int i = 1; i <= n; i++) {
            int onestep = prev + Math.abs(heights[i] - heights[i - 1]);
            int twostep = Integer.MAX_VALUE;
            if (i > 1) {
                twostep = prev2 + Math.abs(heights[i] - heights[i - 2]);
            }
            current = Math.min(onestep, twostep);
            prev2 = prev;
            prev = current;
        }
        return prev;
    }
}
