package DP_2D.P07_Chocolate_Pickup;

import java.util.Arrays;

public class Chocolate_Pickup {
    public static void main(String[] args) {
        Chocolate_Pickup solution = new Chocolate_Pickup();

        int r = 3, c = 4;
        int[][] grid = { { 2, 3, 1, 2 }, { 3, 4, 2, 2 }, { 5, 6, 3, 5 } };

        int maxChocolatesRecursion = solution.maximumChocolatesRecursion(r, c, grid);
        System.out.println(maxChocolatesRecursion);

        int maxChocolatesMemoization = solution.maximumChocolatesMemoization(r, c, grid);
        System.out.println(maxChocolatesMemoization);

        int maxChocolatesTabulation = solution.maximumChocolatesTabulation(r, c, grid);
        System.out.println(maxChocolatesTabulation);

        int maxChocolatesOptimization = solution.maximumChocolatesSpaceOptimization(r, c, grid);
        System.out.println(maxChocolatesOptimization);
    }

    /**
     * Approach IV : Using Space Optimization Approach
     * 
     * TC: O(9 x R x C x C)
     * SC: O(2 x C x C) ~ O(C x C)
     * 
     * @param r
     * @param c
     * @param grid
     * @return
     */
    public int maximumChocolatesSpaceOptimization(int r, int c, int[][] grid) {
        // Initialization
        int[][] front = new int[c][c]; // SC: O(C x C)
        int[][] current = new int[c][c]; // SC: O(C x C)
        for (int j1 = 0; j1 < c; j1++) {
            for (int j2 = 0; j2 < c; j2++) {
                if (j1 == j2) {
                    front[j1][j2] = grid[r - 1][j1];
                } else {
                    front[j1][j2] = grid[r - 1][j1] + grid[r - 1][j2];
                }
            }
        }
        // Iterative Calls
        for (int i = r - 2; i >= 0; i--) {
            for (int j1 = 0; j1 < c; j1++) {
                for (int j2 = 0; j2 < c; j2++) {
                    int maxValue = (int) -1e9;
                    for (int dj1 = -1; dj1 <= 1; dj1++) {
                        for (int dj2 = -1; dj2 <= 1; dj2++) {
                            int value = 0;
                            if (j1 == j2) {
                                value = grid[i][j1];
                            } else {
                                value = grid[i][j1] + grid[i][j2];
                            }
                            if (j1 + dj1 >= 0 && j1 + dj1 < c && j2 + dj2 >= 0 && j2 + dj2 < c) {
                                value += front[j1 + dj1][j2 + dj2];
                            } else {
                                value += (int) -1e9;
                            }
                            maxValue = Math.max(maxValue, value);
                        }
                    }
                    current[j1][j2] = maxValue;
                }
            }
            for (int p = 0; p < c; p++) {
                front[p] = current[p].clone();
            }
        }
        return front[0][c - 1];
    }

    /**
     * Approach III : Using Tabulation Approach
     * 
     * TC: O(9 x R x C x C)
     * SC: O(R x C x C)
     * 
     * @param r
     * @param c
     * @param grid
     * @return
     */
    public int maximumChocolatesTabulation(int r, int c, int[][] grid) {
        // Initialization
        int[][][] dp = new int[r][c][c]; // SC: O(R x C x C)
        for (int j1 = 0; j1 < c; j1++) {
            for (int j2 = 0; j2 < c; j2++) {
                if (j1 == j2) {
                    dp[r - 1][j1][j2] = grid[r - 1][j1];
                } else {
                    dp[r - 1][j1][j2] = grid[r - 1][j1] + grid[r - 1][j2];
                }
            }
        }
        // Iterative Calls
        for (int i = r - 2; i >= 0; i--) {
            for (int j1 = 0; j1 < c; j1++) {
                for (int j2 = 0; j2 < c; j2++) {
                    int maxValue = (int) -1e9;
                    for (int dj1 = -1; dj1 <= 1; dj1++) {
                        for (int dj2 = -1; dj2 <= 1; dj2++) {
                            int current = 0;
                            if (j1 == j2) {
                                current = grid[i][j1];
                            } else {
                                current = grid[i][j1] + grid[i][j2];
                            }
                            if (j1 + dj1 >= 0 && j1 + dj1 < c && j2 + dj2 >= 0 && j2 + dj2 < c) {
                                current += dp[i + 1][j1 + dj1][j2 + dj2];
                            } else {
                                current += (int) -1e9;
                            }
                            maxValue = Math.max(maxValue, current);
                        }
                    }
                    dp[i][j1][j2] = maxValue;
                }
            }
        }
        return dp[0][0][c - 1];
    }

    /**
     * Approach II : Using Memoization Approach
     * 
     * TC: O(R x C x C)
     * SC: O(R x C x C + R)
     * 
     * @param r
     * @param c
     * @param grid
     * @return
     */
    public int maximumChocolatesMemoization(int r, int c, int[][] grid) {
        // we have fixed starting points (0, 0) and (0, c - 1)
        int[][][] memo = new int[r + 1][c + 1][c + 1]; // SC: O(R x C x C)
        for (int[][] memset : memo) {
            for (int[] mem : memset) {
                Arrays.fill(mem, -1);
            }
        }
        return solveMemoization(0, 0, c - 1, r, c, grid, memo);
    }

    /**
     * Using Memoization Approach
     * 
     * TC: O(R x C x C)
     * SC: O(R)
     * 
     * @param i
     * @param j1
     * @param j2
     * @param r
     * @param c
     * @param grid
     * @return
     */
    private int solveMemoization(int i, int j1, int j2, int r, int c, int[][] grid, int[][][] memo) {
        // Base Case
        if (j1 < 0 || j1 >= c || j2 < 0 || j2 >= c) {
            return (int) -1e9;
        }
        if (i == r - 1) {
            if (j1 == j2) {
                return grid[i][j1];
            } else {
                return grid[i][j1] + grid[i][j2];
            }
        }
        // Memoization Check
        if (memo[i][j1][j2] != -1) {
            return memo[i][j1][j2];
        }
        // Recursive Calls
        int maxValue = (int) -1e9;
        for (int di = -1; di <= 1; di++) {
            for (int dj = -1; dj <= 1; dj++) {
                int current = 0;
                if (j1 == j2) {
                    current = grid[i][j1];
                } else {
                    current = grid[i][j1] + grid[i][j2];
                }
                current += solveMemoization(i + 1, j1 + di, j2 + dj, r, c, grid, memo);
                maxValue = Math.max(maxValue, current);
            }
        }
        return memo[i][j1][j2] = maxValue;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(2 x 3 ^ R)
     * SC: O(R)
     * 
     * @param r
     * @param c
     * @param grid
     * @return
     */
    public int maximumChocolatesRecursion(int r, int c, int[][] grid) {
        // we have fixed starting points (0, 0) and (0, c - 1)
        return solveRecursion(0, 0, c - 1, r, c, grid);
    }

    /**
     * Using Recursion Approach
     * 
     * TC: O(2 x 3 ^ R)
     * SC: O(R)
     * 
     * @param i
     * @param j1
     * @param j2
     * @param r
     * @param c
     * @param grid
     * @return
     */
    private int solveRecursion(int i, int j1, int j2, int r, int c, int[][] grid) {
        // Base Case
        if (j1 < 0 || j1 >= c || j2 < 0 || j2 >= c) {
            return (int) -1e9;
        }
        if (i == r - 1) {
            if (j1 == j2) {
                return grid[i][j1];
            } else {
                return grid[i][j1] + grid[i][j2];
            }
        }
        // Recursive Calls
        int maxValue = (int) -1e9;
        for (int di = -1; di <= 1; di++) {
            for (int dj = -1; dj <= 1; dj++) {
                int current = 0;
                if (j1 == j2) {
                    current = grid[i][j1];
                } else {
                    current = grid[i][j1] + grid[i][j2];
                }
                current += solveRecursion(i + 1, j1 + di, j2 + dj, r, c, grid);
                maxValue = Math.max(maxValue, current);
            }
        }
        return maxValue;
    }
}
