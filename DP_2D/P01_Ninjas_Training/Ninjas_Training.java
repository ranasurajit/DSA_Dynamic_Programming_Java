package DP_2D.P01_Ninjas_Training;

import java.util.Arrays;

public class Ninjas_Training {
    public static void main(String[] args) {
        int n = 3;
        int[][] points = {
                { 10, 40, 70 },
                { 20, 50, 80 },
                { 30, 60, 90 }
        };
        int maxScoresRecursion = ninjaTrainingRecursion(n, points);
        System.out.println(maxScoresRecursion);

        int maxScoresMemoization = ninjaTrainingMemoization(n, points);
        System.out.println(maxScoresMemoization);

        int maxScoresTabulation = ninjaTrainingTabulation(n, points);
        System.out.println(maxScoresTabulation);
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(N ^ 3)
     * SC: O(N)
     * 
     * @param n
     * @param points
     * @return
     */
    public static int ninjaTrainingRecursion(int n, int points[][]) {
        /**
         * task 0 - 0
         * task 1 - 1
         * task 2 - 2
         * notask - 3
         */
        // Recurrence calculation from (n - 1) to 0
        return solveRecursion(n - 1, 3, points);
    }

    /**
     * Using Recursion Approach
     * 
     * TC: O(N ^ 3)
     * SC: O(N)
     * 
     * @param dayIndex
     * @param prevDayTask
     * @param points
     * @return
     */
    private static int solveRecursion(int dayIndex, int prevDayTask, int[][] points) {
        // Base Case
        if (dayIndex == 0) {
            int maxPoints = 0;
            for (int task = 0; task < 3; task++) {
                if (task != prevDayTask) {
                    maxPoints = Math.max(maxPoints, points[0][task]);
                }
            }
            return maxPoints;
        }
        // Recursion Calls
        int scores = 0;
        int maxPoints = 0;
        for (int task = 0; task < 3; task++) {
            if (task != prevDayTask) {
                scores = points[dayIndex][task] + solveRecursion(dayIndex - 1, task, points);
                maxPoints = Math.max(maxPoints, scores);
            }
        }
        return maxPoints;
    }

    /**
     * Approach II : Using Memoization Approach
     * 
     * TC: O(N ^ 3)
     * SC: O(N)
     * 
     * @param n
     * @param points
     * @return
     */
    public static int ninjaTrainingMemoization(int n, int points[][]) {
        /**
         * task 0 - 0
         * task 1 - 1
         * task 2 - 2
         * notask - 3
         */
        // Recurrence calculation from (n - 1) to 0
        int[][] memo = new int[n][4]; // SC: O(4 x N)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(n - 1, 3, points, memo);
    }

    /**
     * Using Memoization Approach
     * 
     * TC: O(3 x 4 x N) ~ O(N)
     * SC: O(4 x N + N) ~ O(N)
     * 
     * @param dayIndex
     * @param prevDayTask
     * @param points
     * @return
     */
    private static int solveMemoization(int dayIndex, int prevDayTask, int[][] points, int[][] memo) {
        // Base Case
        if (dayIndex == 0) {
            int maxPoints = 0;
            for (int task = 0; task < 3; task++) {
                if (task != prevDayTask) {
                    maxPoints = Math.max(maxPoints, points[0][task]);
                }
            }
            return memo[dayIndex][prevDayTask] = maxPoints;
        }
        // Memoization Check
        if (memo[dayIndex][prevDayTask] != -1) {
            return memo[dayIndex][prevDayTask];
        }
        // Recursion Calls
        int scores = 0;
        int maxPoints = 0;
        for (int task = 0; task < 3; task++) {
            if (task != prevDayTask) {
                scores = points[dayIndex][task] + solveMemoization(dayIndex - 1, task, points, memo);
                maxPoints = Math.max(maxPoints, scores);
            }
        }
        return memo[dayIndex][prevDayTask] = maxPoints;
    }

    /**
     * Approach III : Using Tabulation Approach
     * 
     * TC: O(3 x 4 x N) ~ O(N)
     * SC: O(4 x N) ~ O(N)
     * 
     * @param n
     * @param points
     * @return
     */
    public static int ninjaTrainingTabulation(int n, int points[][]) {
        /**
         * task 0 - 0
         * task 1 - 1
         * task 2 - 2
         * notask - 3
         */
        // Recurrence calculation from 0 to (n - 1)
        // Initialization
        int[][] dp = new int[n][4];
        dp[0][0] = Math.max(points[0][1], points[0][2]);
        dp[0][1] = Math.max(points[0][0], points[0][2]);
        dp[0][2] = Math.max(points[0][0], points[0][1]);
        dp[0][3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));
        // Iterative Calls
        for (int day = 1; day < n; day++) { // TC: O(N)
            for (int last = 0; last < 4; last++) { // TC: O(4)
                for (int task = 0; task < 3; task++) { // TC: O(3)
                    if (task != last) {
                        int scores = points[day][task] + dp[day - 1][task];
                        dp[day][last] = Math.max(dp[day][last], scores);
                    }
                }
            }
        }
        return dp[n - 1][3];
    }

    /**
     * Approach IV : Using Space Optimization Approach
     * 
     * TC: O(3 x 4 x N) ~ O(N)
     * SC: O(8) ~ O(1)
     * 
     * @param n
     * @param points
     * @return
     */
    public static int ninjaTraining(int n, int points[][]) {
        /**
         * task 0 - 0
         * task 1 - 1
         * task 2 - 2
         * notask - 3
         */
        // Recurrence calculation from 0 to (n - 1)
        // Initialization
        int[] prev = new int[4]; // SC: O(4)
        prev[0] = Math.max(points[0][1], points[0][2]);
        prev[1] = Math.max(points[0][0], points[0][2]);
        prev[2] = Math.max(points[0][0], points[0][1]);
        prev[3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

        int[] current = new int[4]; // SC: O(4)
        // Iterative Calls
        for (int day = 1; day < n; day++) { // TC: O(N)
            for (int last = 0; last < 4; last++) { // TC: O(4)
                for (int task = 0; task < 3; task++) { // TC: O(3)
                    if (task != last) {
                        int scores = points[day][task] + prev[task];
                        current[last] = Math.max(current[last], scores);
                    }
                }
            }
            prev = current.clone();
        }
        return prev[3];
    }
}
