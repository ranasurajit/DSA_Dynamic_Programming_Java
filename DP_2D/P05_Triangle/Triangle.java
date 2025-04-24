package DP_2D.P05_Triangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Triangle {
    public static void main(String[] args) {
        Triangle solution = new Triangle();
        int[][] triangle = { { 2 }, { 3, 4 }, { 6, 5, 7 }, { 4, 1, 8, 3 } };

        List<List<Integer>> triangleList = new ArrayList<>();

        for (int[] row : triangle) {
            List<Integer> listRow = new ArrayList<>();
            for (int num : row) {
                listRow.add(num);
            }
            triangleList.add(listRow);
        }

        int minimumPathSumRecursion = solution.minimumTotalRecursion(triangleList);
        System.out.println(minimumPathSumRecursion);

        int minimumPathSumMemoization = solution.minimumTotalMemoization(triangleList);
        System.out.println(minimumPathSumMemoization);

        int minimumPathSumTabulation = solution.minimumTotalTabulation(triangleList);
        System.out.println(minimumPathSumTabulation);

        int minimumPathSumOptimization = solution.minimumTotalSpaceOptimization(triangleList);
        System.out.println(minimumPathSumOptimization);
    }

    /**
     * Approach IV : Using Space Optimization Approach
     *
     * TC: O(N x N + N) ~ O(N x N)
     * SC: O(2 x N) ~ O(N)
     *
     * Accepted (45 / 45 testcases passed)
     */
    public int minimumTotalSpaceOptimization(List<List<Integer>> triangle) {
        int n = triangle.size();
        // Initialization
        int[] next = new int[n]; // SC: O(N)
        for (int j = 0; j < n; j++) { // TC: O(N)
            next[j] = triangle.get(n - 1).get(j);
        }
        for (int i = n - 2; i >= 0; i--) { // TC: O(N)
            int[] current = new int[n]; // SC: O(N)
            for (int j = i; j >= 0; j--) { // TC: O(N)
                current[j] = triangle.get(i).get(j) + Math.min(next[j], next[j + 1]);
            }
            next = current.clone();
        }
        return next[0];
    }

    /**
     * Approach III : Using Tabulation Approach
     *
     * TC: O(N x N + N) ~ O(N x N)
     * SC: O(N x N)
     *
     * Accepted (45 / 45 testcases passed)
     */
    public int minimumTotalTabulation(List<List<Integer>> triangle) {
        int n = triangle.size();
        // Initialization
        int[][] dp = new int[n][n]; // SC: O(N x N)
        for (int j = 0; j < n; j++) { // TC: O(N)
            dp[n - 1][j] = triangle.get(n - 1).get(j);
        }
        for (int i = n - 2; i >= 0; i--) { // TC: O(N)
            for (int j = i; j >= 0; j--) { // TC: O(N)
                dp[i][j] = triangle.get(i).get(j) + Math.min(dp[i + 1][j], dp[i + 1][j + 1]);
            }
        }
        return dp[0][0];
    }

    /**
     * Approach II : Using Memoization Approach
     *
     * TC: O(N x N)
     * SC: O(N x N)
     *
     * Accepted (45 / 45 testcases passed)
     */
    public int minimumTotalMemoization(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] memo = new int[n + 1][n + 1]; // SC: O(N x N)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        // since we have mutiple paths at the last index so we start recursion from
        // index (0, 0)
        return solveMemoization(0, 0, n, triangle, memo);
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(N x N)
     * SC: O(N)
     */
    private int solveMemoization(int i, int j, int n, List<List<Integer>> triangle,
            int[][] memo) {
        // Base Case
        if (i == n - 1) {
            return triangle.get(i).get(j);
        }
        // Memoization Check
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        /**
         * as per moves to next subsequent indices i.e. (i)(j) to (i + 1)(j) and (i +
         * 1)(j + 1)
         * it can never go out of bounds
         */
        // Recursive Calls
        int downMove = solveMemoization(i + 1, j, n, triangle, memo);
        int diagonalMove = solveMemoization(i + 1, j + 1, n, triangle, memo);
        return memo[i][j] = triangle.get(i).get(j) + Math.min(downMove, diagonalMove);
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ (N x (N + 1)) / 2)
     * SC: O(N)
     *
     * Time Limit Exceeded (42 / 45 testcases passed)
     */
    public int minimumTotalRecursion(List<List<Integer>> triangle) {
        int n = triangle.size();
        // since we have mutiple paths at the last index so we start recursion from
        // index (0, 0)
        return solveRecursion(0, 0, n, triangle);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ (N x (N + 1)) / 2)
     * SC: O(N)
     */
    private int solveRecursion(int i, int j, int n, List<List<Integer>> triangle) {
        // Base Case
        if (i == n - 1) {
            return triangle.get(i).get(j);
        }
        /**
         * as per moves to next subsequent indices i.e. (i)(j) to (i + 1)(j) and (i +
         * 1)(j + 1)
         * it can never go out of bounds
         */
        // Recursive Calls
        int downMove = triangle.get(i).get(j) + solveRecursion(i + 1, j, n, triangle);
        int diagonalMove = triangle.get(i).get(j) + solveRecursion(i + 1, j + 1, n, triangle);
        return Math.min(downMove, diagonalMove);
    }
}
