package two_dimention_grids;

/**
 * 
 * CodingNinjas: Ninja’s Training
 * 
 * Link: https://www.codingninjas.com/studio/problems/ninja%E2%80%99s-training_3621003
 * 
 */
public class P1_Ninjas_Training {
    public static void main(String[] args) {
        int n = 3;
        int points[][] = {
                { 10, 40, 70 },
                { 20, 50, 80 },
                { 30, 60, 90 }
        };
        int maxPointsRecursion = ninjaTrainingRecursion(n, points);
        System.out.println("Maximum points that can be earned by Ninja (Using Recursion): " + maxPointsRecursion);

        int maxPointsMemoization = ninjaTrainingMemoization(n, points);
        System.out.println("Maximum points that can be earned by Ninja (Using Memoization): " + maxPointsMemoization);

        int maxPointsTabulation = ninjaTrainingTabulation(n, points);
        System.out.println("Maximum points that can be earned by Ninja (Using Tabulation): " + maxPointsTabulation);

        int maxPointsOptimized = ninjaTrainingSpaceOptimized(n, points);
        System.out.println(
                "Maximum points that can be earned by Ninja (Using Space Optimization): " + maxPointsOptimized);
    }

    /**
     * Using Recursion
     * 
     * @param n
     * @param points
     * @return
     */
    private static int ninjaTrainingRecursion(int n, int[][] points) {
        return getMaxPointsRecursion(n - 1, 3, points);
    }

    /**
     * Using Recursion
     * 
     * @param day
     * @param last
     * @param points
     * @return
     */
    private static int getMaxPointsRecursion(int day, int last, int[][] points) {
        // base case
        if (day == 0) {
            int max = 0;
            for (int i = 0; i < 3; i++) {
                if (i != last) {
                    max = Math.max(max, points[0][i]);
                }
            }
            return max;
        }
        int max = 0;
        for (int i = 0; i < 3; i++) {
            if (i != last) {
                int earned = points[day][i] + getMaxPointsRecursion(day - 1, i, points);
                max = Math.max(max, earned);
            }
        }
        return max;
    }

    /**
     * Using Memoization
     * 
     * TC: O(N x 4) x 3
     * SC: O(N) + O(N x 4) x 3
     * 
     * @param n
     * @param points
     * @return
     */
    private static int ninjaTrainingMemoization(int n, int[][] points) {
        int[][] dp = new int[n][4];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        return getMaxPointsMemoization(n - 1, 3, points, dp);
    }

    /**
     * Using Memoization
     * 
     * @param day
     * @param last
     * @param points
     * @return
     */
    private static int getMaxPointsMemoization(int day, int last, int[][] points, int[][] dp) {
        if (dp[day][last] != -1) {
            return dp[day][last];
        }
        // base case
        if (day == 0) {
            int max = 0;
            for (int i = 0; i < 3; i++) {
                if (i != last) {
                    max = Math.max(max, points[0][i]);
                }
            }
            dp[day][last] = max;
            return dp[day][last];
        }
        int max = 0;
        for (int i = 0; i < 3; i++) {
            if (i != last) {
                int earned = points[day][i] + getMaxPointsMemoization(day - 1, i, points, dp);
                max = Math.max(max, earned);
            }
        }
        dp[day][last] = max;
        return dp[day][last];
    }

    /**
     * Using Tabulation
     * 
     * TC: O(N x 4) x 3
     * SC: O(N x 4)
     * 
     * @param n
     * @param points
     * @return
     */
    private static int ninjaTrainingTabulation(int n, int[][] points) {
        int[][] dp = new int[n][4];

        // calculate day 0 points
        dp[0][0] = Math.max(points[0][1], points[0][2]);
        dp[0][1] = Math.max(points[0][0], points[0][2]);
        dp[0][2] = Math.max(points[0][0], points[0][1]);
        dp[0][3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

        for (int day = 1; day < n; day++) {
            for (int last = 0; last < 4; last++) {
                dp[day][last] = 0;
                for (int task = 0; task < 3; task++) {
                    if (task != last) {
                        int earned = points[day][task] + dp[day - 1][task];
                        dp[day][last] = Math.max(dp[day][last], earned);
                    }
                }
            }
        }
        return dp[n - 1][3];
    }

    /**
     *
     * Using Space Optimization
     * 
     * TC: O(N x 4) x 3
     * SC: O(4)
     * 
     * @param n
     * @param points
     * @return
     */
    private static int ninjaTrainingSpaceOptimized(int n, int[][] points) {
        int[] prev = new int[4];
        // calculate day 0 points
        prev[0] = Math.max(points[0][1], points[0][2]);
        prev[1] = Math.max(points[0][0], points[0][2]);
        prev[2] = Math.max(points[0][0], points[0][1]);
        prev[3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

        for (int day = 1; day < n; day++) {
            int[] temp = new int[4];
            for (int last = 0; last < 4; last++) {
                temp[last] = 0;
                for (int task = 0; task < 3; task++) {
                    if (task != last) {
                        int earned = points[day][task] + prev[task];
                        temp[last] = Math.max(temp[last], earned);
                    }
                }
            }
            prev = temp;
        }
        return prev[3];
    }
}
