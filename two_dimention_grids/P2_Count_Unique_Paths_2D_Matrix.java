package two_dimention_grids;

import java.util.Arrays;

/**
 * 
 * CodingNinjas: Unique Paths
 * 
 * Link: https://www.codingninjas.com/studio/problems/total-unique-paths_1081470
 * 
 */
public class P2_Count_Unique_Paths_2D_Matrix {

    public static void main(String[] args) {
        int m = 3;
        int n = 2;
        int countUniquePathsRecursive = uniquePathsRecursion(m, n);
        System.out.println(
                "Count of unique paths for grid of size " + m + " x " + n + " is (using Recursion) : "
                        + countUniquePathsRecursive);

        int countUniquePathsMemoization = uniquePathsMemoization(m, n);
        System.out.println(
                "Count of unique paths for grid of size " + m + " x " + n + " is (using Memoization) : "
                        + countUniquePathsMemoization);

        int countUniquePathsTabulation = uniquePathsTabulation(m, n);
        System.out.println(
                "Count of unique paths for grid of size " + m + " x " + n + " is (using Tabulation) : "
                        + countUniquePathsTabulation);

        int countUniquePathsOptimized = uniquePathsSpaceOptimized(m, n);
        System.out.println(
                "Count of unique paths for grid of size " + m + " x " + n + " is (using Space Optimization) : "
                        + countUniquePathsOptimized);
    }

    /**
     * Using Recursion
     * 
     * TC: O(2^(m x n))
     * SC: O((m - 1) + (n - 1)), i.e. O(path length)
     * 
     * @param m
     * @param n
     * @return
     */
    private static int uniquePathsRecursion(int m, int n) {
        return getUniquePathsRecursive(m - 1, n - 1);
    }

    private static int getUniquePathsRecursive(int row, int col) {
        // Base case
        if (row == 0 && col == 0) {
            return 1;
        }
        if (row < 0 || col < 0) {
            return 0;
        }
        int countLeft = getUniquePathsRecursive(row, col - 1);
        int countUp = getUniquePathsRecursive(row - 1, col);
        return countLeft + countUp;
    }

    /**
     * Using Memoization
     * 
     * TC: O(n x m)
     * SC: O(n x m) + O((m - 1) + (n - 1)) , i.e. O(path length)
     * 
     * @param m
     * @param n
     * @return
     */
    private static int uniquePathsMemoization(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        return getUniquePathsMemoization(m - 1, n - 1, dp);
    }

    private static int getUniquePathsMemoization(int row, int col, int[][] dp) {
        // Base case
        if (row < 0 || col < 0) {
            return 0;
        }
        if (row == 0 && col == 0) {
            dp[0][0] = 1;
        }
        if (dp[row][col] != -1) {
            return dp[row][col];
        }
        int countLeft = getUniquePathsMemoization(row, col - 1, dp);
        int countUp = getUniquePathsMemoization(row - 1, col, dp);
        dp[row][col] = countLeft + countUp;
        return dp[row][col];
    }

    /**
     * Using Tabulation
     * 
     * TC: O(n x m)
     * SC: O(n x m)
     * 
     * @param m
     * @param n
     * @return
     */
    private static int uniquePathsTabulation(int m, int n) {
        int[][] dp = new int[m][n];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        return getUniquePathsTabulation(m, n, dp);
    }

    private static int getUniquePathsTabulation(int row, int col, int[][] dp) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                    continue;
                }
                int countLeft = 0;
                int countUp = 0;
                if (j > 0)
                    countLeft = dp[i][j - 1];
                if (i > 0)
                    countUp = dp[i - 1][j];
                dp[i][j] = countLeft + countUp;
            }
        }
        return dp[row - 1][col - 1];
    }

    /**
     * Using Space Optimization
     * 
     * TC: O(n x m)
     * SC: O(n)
     * 
     * @param m
     * @param n
     * @return
     */
    private static int uniquePathsSpaceOptimized(int m, int n) {
        int[] prev = new int[n];
        for (int i = 0; i < m; i++) {
            int[] temp = new int[n];
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    temp[j] = 1;
                    continue;
                }
                int left = 0;
                int up = 0;
                if (i > 0) {
                    up = prev[j];
                }
                if (j > 0) {
                    left = temp[j - 1];
                }
                temp[j] = up + left;
            }
            prev = temp;
        }
        return prev[n - 1];
    }
}
