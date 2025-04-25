package DP_2D.P06_Minimum_Falling_Path_Sum;

import java.util.Arrays;

public class Minimum_Falling_Path_Sum {
    public static void main(String[] args) {
        Minimum_Falling_Path_Sum solution = new Minimum_Falling_Path_Sum();

        int[][] matrix = { { 2, 1, 3 }, { 6, 5, 4 }, { 7, 8, 9 } };

        int minPathSumRecursion = solution.minFallingPathSumRecursion(matrix);
        System.out.println(minPathSumRecursion);

        int minPathSumMemoization = solution.minFallingPathSumMemoization(matrix);
        System.out.println(minPathSumMemoization);

        int minPathSumTabulation = solution.minFallingPathSumTabulation(matrix);
        System.out.println(minPathSumTabulation);

        int minPathSumOptimization = solution.minFallingPathSumSpaceOptimization(matrix);
        System.out.println(minPathSumOptimization);
    }

    /**
     * Approach IV : Using Space Optimization Approach
     *
     * TC: O(M x N + 2 x N) ~ O(M x N)
     * SC: O(2 x N) ~ O(N)
     *
     * Accepted (52 / 52 testcases passed)
     * 
     * @param matrix
     * @return
     */
    public int minFallingPathSumSpaceOptimization(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int minSum = (int) 1e9;
        // Initialization
        int[] prev = new int[n]; // SC: O(N)
        int[] current = new int[n]; // SC: O(N)
        for (int j = 0; j < n; j++) { // TC: O(N)
            prev[j] = matrix[0][j];
        }
        // Iterative Calls
        for (int i = 1; i < m; i++) { // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                int up = matrix[i][j] + prev[j];
                int leftDiag = (int) 1e9;
                int rightDiag = (int) 1e9;
                if (j > 0) {
                    leftDiag = matrix[i][j] + prev[j - 1];
                }
                if (j < n - 1) {
                    rightDiag = matrix[i][j] + prev[j + 1];
                }
                current[j] = Math.min(up, Math.min(leftDiag, rightDiag));
            }
            prev = current.clone();
        }
        for (int j = 0; j < n; j++) { // TC: O(N)
            minSum = Math.min(minSum, prev[j]);
        }
        return minSum;
    }

    /**
     * Approach III : Using Tabulation Approach
     *
     * TC: O(M x N + 2 x N) ~ O(M x N)
     * SC: O(M x N)
     *
     * Accepted (52 / 52 testcases passed)
     * 
     * @param matrix
     * @return
     */
    public int minFallingPathSumTabulation(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int minSum = (int) 1e9;
        // Initialization
        int[][] dp = new int[m][n]; // SC: O(M x N)
        for (int j = 0; j < n; j++) { // TC: O(N)
            dp[0][j] = matrix[0][j];
        }
        // Iterative Calls
        for (int i = 1; i < m; i++) { // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                int up = matrix[i][j] + dp[i - 1][j];
                int leftDiag = (int) 1e9;
                int rightDiag = (int) 1e9;
                if (j > 0) {
                    leftDiag = matrix[i][j] + dp[i - 1][j - 1];
                }
                if (j < n - 1) {
                    rightDiag = matrix[i][j] + dp[i - 1][j + 1];
                }
                dp[i][j] = Math.min(up, Math.min(leftDiag, rightDiag));
            }
        }
        for (int j = 0; j < n; j++) { // TC: O(N)
            minSum = Math.min(minSum, dp[m - 1][j]);
        }
        return minSum;
    }

    /**
     * Approach II : Using Memoization Approach
     *
     * TC: O(M x N x N)
     * SC: O(M x N + M)
     *
     * Accepted (52 / 52 testcases passed)
     * 
     * @param matrix
     * @return
     */
    public int minFallingPathSumMemoization(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int minSum = (int) 1e9;
        int[][] memo = new int[m + 1][n + 1]; // SC: O(M x N)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        for (int j = 0; j < n; j++) { // TC: O(N)
            minSum = Math.min(minSum,
                    solveMemoization(m - 1, j, n, matrix, memo)); // TC: O(3 ^ M)
        }
        return minSum;
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(M x N)
     * SC: O(M)
     * 
     * @param i
     * @param j
     * @param n
     * @param matrix
     * @return
     */
    private int solveMemoization(int i, int j, int n, int[][] matrix, int[][] memo) {
        // Base Case
        if (i == 0) {
            // reached at 0th row so return the value of cell where it ends
            return matrix[i][j];
        }
        // If cell goes out of bounds
        if (j < 0 || j >= n) {
            return (int) 1e9;
        }
        // Memoization Check
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        // Recursive Calls
        int up = matrix[i][j] + solveMemoization(i - 1, j, n, matrix, memo);
        int leftDiag = (int) 1e9;
        int rightDiag = (int) 1e9;
        if (j > 0) {
            leftDiag = matrix[i][j] + solveMemoization(i - 1, j - 1, n, matrix, memo);
        }
        if (j < n - 1) {
            rightDiag = matrix[i][j] + solveMemoization(i - 1, j + 1, n, matrix, memo);
        }
        return memo[i][j] = Math.min(up, Math.min(leftDiag, rightDiag));
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(N x 3 ^ M)
     * SC: O(M)
     *
     * Time Limit Exceeded (38 / 52 testcases passed)
     * 
     * @param matrix
     * @return
     */
    public int minFallingPathSumRecursion(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int minSum = (int) 1e9;
        for (int j = 0; j < n; j++) { // TC: O(N)
            minSum = Math.min(minSum, solveRecursion(m - 1, j, n, matrix)); // TC: O(3 ^ M)
        }
        return minSum;
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(3 ^ M)
     * SC: O(M)
     * 
     * @param i
     * @param j
     * @param n
     * @param matrix
     * @return
     */
    private int solveRecursion(int i, int j, int n, int[][] matrix) {
        // Base Case
        if (i == 0) {
            // reached at 0th row so return the value of cell where it ends
            return matrix[i][j];
        }
        // If cell goes out of bounds
        if (j < 0 || j >= n) {
            return (int) 1e9;
        }
        // Recursive Calls
        int up = matrix[i][j] + solveRecursion(i - 1, j, n, matrix);
        int leftDiag = (int) 1e9;
        int rightDiag = (int) 1e9;
        if (j > 0) {
            leftDiag = matrix[i][j] + solveRecursion(i - 1, j - 1, n, matrix);
        }
        if (j < n - 1) {
            rightDiag = matrix[i][j] + solveRecursion(i - 1, j + 1, n, matrix);
        }
        return Math.min(up, Math.min(leftDiag, rightDiag));
    }
}
