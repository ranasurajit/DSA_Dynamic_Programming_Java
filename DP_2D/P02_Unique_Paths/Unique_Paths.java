package DP_2D.P02_Unique_Paths;

import java.util.Arrays;

public class Unique_Paths {
    public static void main(String[] args) {
        Unique_Paths solution = new Unique_Paths();

        int m = 3, n = 7;

        int countUniquePathsRecursion = solution.uniquePathsRecursion(m, n);
        System.out.println(countUniquePathsRecursion);

        int countUniquePathsMemoization = solution.uniquePathsMemoization(m, n);
        System.out.println(countUniquePathsMemoization);

        int countUniquePathsTabulation = solution.uniquePathsTabulation(m, n);
        System.out.println(countUniquePathsTabulation);

        int countUniquePathsOptimization = solution.uniquePathsSpaceOptimization(m, n);
        System.out.println(countUniquePathsOptimization);
    }

    /**
     * Approach IV : Using Space Optimization Approach
     *
     * TC: O(M x N)
     * SC: O(2 x N) ~ O(N)
     *
     * Accepted (63 / 63 testcases passed)
     */
    public int uniquePathsSpaceOptimization(int m, int n) {
        // Initialization
        int[] prev = new int[n]; // SC: O(N)
        Arrays.fill(prev, 1);
        // Iterative Calls
        for (int i = 1; i < m; i++) { // TC: O(M)
            int[] current = new int[n]; // SC: O(N)
            current[0] = 1;
            for (int j = 1; j < n; j++) { // TC: O(N)
                current[j] = prev[j] + current[j - 1];
            }
            prev = current;
        }
        return prev[n - 1];
    }

    /**
     * Approach III : Using Tabulation Approach
     *
     * TC: O((M x N) + (M + N)) ~ O(M x N)
     * SC: O(M x N)
     *
     * Accepted (63 / 63 testcases passed)
     */
    public int uniquePathsTabulation(int m, int n) {
        // Initialization
        int[][] dp = new int[m][n]; // SC: O(M x N)
        for (int i = 0; i < m; i++) { // TC: O(M)
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) { // TC: O(N)
            dp[0][j] = 1;
        }
        // Iterative Calls
        for (int i = 1; i < m; i++) { // TC: O(M)
            for (int j = 1; j < n; j++) { // TC: O(N)
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * Approach II : Using Memoization Approach
     *
     * TC: O(M x N)
     * SC: O((M x N) + (M + N))
     *
     * Accepted (63 / 63 testcases passed)
     */
    public int uniquePathsMemoization(int m, int n) {
        int[][] memo = new int[m + 1][n + 1]; // SC: O(M x N)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(m - 1, n - 1, memo);
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(M x N)
     * SC: O((M x N) + (M + N))
     */
    private int solveMemoization(int i, int j, int[][] memo) {
        // Base Case
        if (i == 0 && j == 0) {
            // reached destination
            return 1;
        }
        if (i < 0 || j < 0) {
            // out of bounds of the grid
            return 0;
        }
        // Memoization Check
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        // Recursive Calls
        int up = solveMemoization(i - 1, j, memo);
        int left = solveMemoization(i, j - 1, memo);
        return memo[i][j] = up + left;
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ (M x N))
     * SC: O((M - 1) + (N - 1)) ~ O(M + N)
     *
     * Time Limit Exceeded (37 / 63 testcases passed)
     */
    public int uniquePathsRecursion(int m, int n) {
        return solveRecursion(m - 1, n - 1);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ (M x N))
     * SC: O((M - 1) + (N - 1)) ~ O(M + N)
     */
    private int solveRecursion(int i, int j) {
        // Base Case
        if (i == 0 && j == 0) {
            // reached destination
            return 1;
        }
        if (i < 0 || j < 0) {
            // out of bounds of the grid
            return 0;
        }
        // Recursive Calls
        int up = solveRecursion(i - 1, j);
        int left = solveRecursion(i, j - 1);
        return up + left;
    }
}
