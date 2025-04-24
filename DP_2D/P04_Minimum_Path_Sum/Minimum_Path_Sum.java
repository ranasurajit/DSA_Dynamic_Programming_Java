package DP_2D.P04_Minimum_Path_Sum;

import java.util.Arrays;

public class Minimum_Path_Sum {
    public static void main(String[] args) {
        Minimum_Path_Sum solution = new Minimum_Path_Sum();

        int[][] grid = { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };

        int minimumPathSumRecursion = solution.minPathSumRecursion(grid);
        System.out.println(minimumPathSumRecursion);

        int minimumPathSumMemoization = solution.minPathSumMemoization(grid);
        System.out.println(minimumPathSumMemoization);

        int minimumPathSumTabulation = solution.minPathSumTabulation(grid);
        System.out.println(minimumPathSumTabulation);

        int minimumPathSumOptimization = solution.minPathSumSpaceOptimization(grid);
        System.out.println(minimumPathSumOptimization);
    }

    /**
     * Approach IV : Using Space Optimization Approach
     *
     * TC: O((M x N) + N) ~ O(M x N)
     * SC: O(2 x N) ~ O(N)
     *
     * Accepted (66 / 66 testcases passed)
     */
    public int minPathSumSpaceOptimization(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // Initialization
        int[] prev = new int[n]; // SC: O(N)
        prev[0] = grid[0][0];
        int[] current = new int[n]; // SC: O(N)
        current[0] = grid[0][0];
        for (int j = 1; j < n; j++) { // TC: O(N)
            prev[j] += grid[0][j] + prev[j - 1];
        }
        // Iterative Calls
        for (int i = 1; i < m; i++) { // TC: O(M)
            current[0] += grid[i][0];
            for (int j = 1; j < n; j++) { // TC: O(N)
                current[j] = grid[i][j] + Math.min(prev[j], current[j - 1]);
            }
            prev = current.clone();
        }
        return prev[n - 1];
    }

    /**
     * Approach III : Using Tabulation Approach
     *
     * TC: O((M x N) + (M + N)) ~ O(M x N)
     * SC: O(M x N)
     *
     * Accepted (66 / 66 testcases passed)
     */
    public int minPathSumTabulation(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // Initialization
        int[][] dp = new int[m][n]; // SC: O(M x N)
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) { // TC: O(M)
            dp[i][0] += grid[i][0] + dp[i - 1][0];
        }
        for (int j = 1; j < n; j++) { // TC: O(N)
            dp[0][j] += grid[0][j] + dp[0][j - 1];
        }
        // Iterative Calls
        for (int i = 1; i < m; i++) { // TC: O(M)
            for (int j = 1; j < n; j++) { // TC: O(N)
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
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
     * Accepted (66 / 66 testcases passed)
     */
    public int minPathSumMemoization(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] memo = new int[m + 1][n + 1]; // SC: O(M x N)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(m - 1, n - 1, grid, memo);
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(M x N)
     * SC: O((M - 1) + (N - 1)) ~ O(M + N)
     */
    private int solveMemoization(int i, int j, int[][] grid, int[][] memo) {
        // Base Case
        if (i == 0 && j == 0) {
            return grid[i][j];
        }
        if (i < 0 || j < 0) {
            return (int) 1e9 + 7;
        }
        // Memoization Check
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        // Recursive Calls
        int up = grid[i][j] + solveMemoization(i - 1, j, grid, memo);
        int down = grid[i][j] + solveMemoization(i, j - 1, grid, memo);
        return memo[i][j] = Math.min(up, down);
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ (M x N))
     * SC: O((M - 1) + (N - 1)) ~ O(M + N)
     *
     * Time Limit Exceeded (25 / 66 testcases passed)
     */
    public int minPathSumRecursion(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        return solveRecursion(m - 1, n - 1, grid);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ (M x N))
     * SC: O((M - 1) + (N - 1)) ~ O(M + N)
     */
    private int solveRecursion(int i, int j, int[][] grid) {
        // Base Case
        if (i == 0 && j == 0) {
            return grid[i][j];
        }
        if (i < 0 || j < 0) {
            return (int) 1e9 + 7;
        }
        // Recursive Calls
        int up = grid[i][j] + solveRecursion(i - 1, j, grid);
        int down = grid[i][j] + solveRecursion(i, j - 1, grid);
        return Math.min(up, down);
    }
}
