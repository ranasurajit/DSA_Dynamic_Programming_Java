package DP_2D.P03_Unique_Paths_II;

import java.util.Arrays;

public class Unique_Paths_II {
    public static void main(String[] args) {
        Unique_Paths_II solution = new Unique_Paths_II();

        int[][] obstacleGrid = { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } };

        int countUniquePathsRecursion = solution.uniquePathsWithObstaclesRecursion(obstacleGrid);
        System.out.println(countUniquePathsRecursion);

        int countUniquePathsMemoization = solution.uniquePathsWithObstaclesMemoization(obstacleGrid);
        System.out.println(countUniquePathsMemoization);

        int countUniquePathsTabulation = solution.uniquePathsWithObstaclesTabulation(obstacleGrid);
        System.out.println(countUniquePathsTabulation);
    }

    /**
     * Approach III : Using Tabulation Approach
     *
     * TC: O(M x N)
     * SC: O(M x N)
     *
     * Time Limit Exceeded (42 / 42 testcases passed)
     */
    public int uniquePathsWithObstaclesTabulation(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        // Initialization
        int[][] dp = new int[m][n]; // SC: O(M x N)
        for (int i = 0; i < m; i++) { // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                // Base Case
                if ((i >= 0 || j >= 0) && obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                    continue;
                }
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                    continue;
                }
                // Iterative Calls
                int down = 0;
                int right = 0;
                if (i > 0) {
                    down = dp[i - 1][j];
                }
                if (j > 0) {
                    right = dp[i][j - 1];
                }
                dp[i][j] = down + right;
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
     * Time Limit Exceeded (42 / 42 testcases passed)
     */
    public int uniquePathsWithObstaclesMemoization(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        int[][] memo = new int[m + 1][n + 1]; // SC: O(M x N)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(m - 1, n - 1, obstacleGrid, memo);
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
            // Reached destination
            return 1;
        }
        if (i < 0 || j < 0) {
            // Out of bounds of the grid
            return 0;
        }
        if (grid[i][j] == 1) {
            return 0;
        }
        // Memoization Check
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        // Recursive Calls
        int up = solveMemoization(i - 1, j, grid, memo);
        int left = solveMemoization(i, j - 1, grid, memo);
        return memo[i][j] = up + left;
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ (M x N))
     * SC: O((M - 1) + (N - 1)) ~ O(M + N)
     *
     * Time Limit Exceeded (31 / 42 testcases passed)
     */
    public int uniquePathsWithObstaclesRecursion(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        return solveRecursion(m - 1, n - 1, obstacleGrid);
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
            // Reached destination
            return 1;
        }
        if (i < 0 || j < 0) {
            // Out of bounds of the grid
            return 0;
        }
        if (grid[i][j] == 1) {
            return 0;
        }
        // Recursive Calls
        int up = solveRecursion(i - 1, j, grid);
        int left = solveRecursion(i, j - 1, grid);
        return up + left;
    }
}
