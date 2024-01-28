package subsequences;

import java.util.Arrays;

/**
 * 
 * CodingNinjas: Subset Sum Equal To K
 * 
 * Link:
 * https://www.codingninjas.com/studio/problems/subset-sum-equal-to-k_1550954
 * 
 */
public class P1_Array_Subset_Sum_Equals_K {
    public static void main(String[] args) {
        int n = 4;
        int k = 5;
        int[] arr = { 4, 3, 2, 1 };
        boolean isPresentRecursive = subsetSumToKRecursion(n, k, arr);
        System.out.println("The subset with sum " + k + " is present in array " + Arrays.toString(arr)
                + " (using Recursion) : " + isPresentRecursive);

        boolean isPresentMemoization = subsetSumToKMemoization(n, k, arr);
        System.out.println("The subset with sum " + k + " is present in array " + Arrays.toString(arr)
                + " (using Memoization) : " + isPresentMemoization);

        boolean isPresentTabulation = subsetSumToKTabulation(n, k, arr);
        System.out.println("The subset with sum " + k + " is present in array " + Arrays.toString(arr)
                + " (using Tabulation) : " + isPresentTabulation);

        boolean isPresentOptimized = subsetSumToKSpaceOptimization(n, k, arr);
        System.out.println("The subset with sum " + k + " is present in array " + Arrays.toString(arr)
                + " (using Space Optimization) : " + isPresentOptimized);
    }

    /**
     * Using Recursion
     * 
     * TC: O(2^n)
     * SC: O(n)
     * 
     * @param n
     * @param k
     * @param arr
     * @return
     */
    private static boolean subsetSumToKRecursion(int n, int k, int arr[]) {
        return isSubsetPresentRecursion(n - 1, n, k, arr);
    }

    private static boolean isSubsetPresentRecursion(int index, int n, int k, int arr[]) {
        // Base case
        if (k == 0) {
            return true;
        }
        if (index == 0) {
            return arr[index] == k;
        }
        // pick
        boolean pick = false;
        if (k >= arr[index]) {
            pick = isSubsetPresentRecursion(index - 1, n, k - arr[index], arr);
        }
        // not pick
        boolean notpick = isSubsetPresentRecursion(index - 1, n, k, arr);
        return pick || notpick;
    }

    /**
     * Using Memoization
     * 
     * TC: O(n x (k + 1)) ~ O(n x k)
     * SC: O(n x (k + 1)) + O(n) ~ O(n x k) + O(n)
     * 
     * @param n
     * @param k
     * @param arr
     * @return
     */
    private static boolean subsetSumToKMemoization(int n, int k, int arr[]) {
        int[][] dp = new int[n][k + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return isSubsetPresentMemoization(n - 1, n, k, arr, dp);
    }

    private static boolean isSubsetPresentMemoization(int index, int n, int k, int arr[], int[][] dp) {
        // Base case
        if (k == 0) {
            return true;
        }
        if (index == 0) {
            return arr[0] == k;
        }
        if (dp[index][k] != -1) {
            return dp[index][k] == 0 ? false : true;
        }
        // pick
        boolean pick = false;
        if (k >= arr[index]) {
            pick = isSubsetPresentMemoization(index - 1, n, k - arr[index], arr, dp);
        }
        // not pick
        boolean notpick = isSubsetPresentMemoization(index - 1, n, k, arr, dp);
        dp[index][k] = pick || notpick ? 1 : 0;
        return dp[index][k] == 1;
    }

    /**
     * Using Tabulation
     * 
     * TC: O(n x (k + 1)) ~ O(n x k)
     * SC: O(n x (k + 1)) ~ O(n x k)
     * 
     * @param n
     * @param k
     * @param arr
     * @return
     */
    private static boolean subsetSumToKTabulation(int n, int k, int arr[]) {
        boolean[][] dp = new boolean[n][k + 1];
        for (boolean[] row : dp) {
            Arrays.fill(row, false);
        }
        return isSubsetPresentTabulation(n, k, arr, dp);
    }

    private static boolean isSubsetPresentTabulation(int n, int k, int arr[], boolean[][] dp) {
        // Base case
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        if (arr[0] <= k) {
            dp[0][arr[0]] = true;
        }
        for (int index = 1; index < n; index++) {
            for (int target = 1; target <= k; target++) {
                // pick
                boolean pick = false;
                if (target >= arr[index]) {
                    pick = dp[index - 1][target - arr[index]];
                }
                // not pick
                boolean notpick = dp[index - 1][target];
                dp[index][target] = pick || notpick;
            }
        }
        return dp[n - 1][k];
    }

    /**
     * Using Space Optimization
     * 
     * TC: O(n x k)
     * SC: O(k + 1) ~ O(k)
     * 
     * @param n
     * @param k
     * @param arr
     * @return
     */
    private static boolean subsetSumToKSpaceOptimization(int n, int k, int arr[]) {
        boolean[] prev = new boolean[k + 1];
        prev[0] = true;
        if (arr[0] <= k) {
            prev[arr[0]] = true;
        }
        for (int index = 1; index < n; index++) {
            boolean[] temp = new boolean[k + 1];
            temp[0] = true;
            for (int target = 1; target <= k; target++) {
                // pick
                boolean pick = false;
                if (target >= arr[index]) {
                    pick = prev[target - arr[index]];
                }
                // not pick
                boolean notpick = prev[target];
                temp[target] = pick || notpick;
            }
            prev = temp;
        }
        return prev[k];
    }
}
