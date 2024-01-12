package one_dimention;

/**
 * 
 * CodingNinjas: Count Ways To Reach The N-th Stairs
 * 
 * Link:
 * https://www.codingninjas.com/studio/problems/count-ways-to-reach-the-n-th-stairs_798650
 * 
 */
public class P1_Count_Ways_To_Reach_N_Stairs {
    public static void main(String[] args) {
        long n = 5L;
        int ways = countDistinctWayToClimbStair(n);
        System.out.println("Number of ways to climb steps with " + n + " steps (using tabulation) : " + ways);

        int waysOptimum = countWaysOptimization(n);
        System.out.println(
                "Number of ways to climb steps with " + n + " steps (using space optimization) : " + waysOptimum);
    }

    /**
     * Using Tabulation (Bottom Up Approach)
     * 
     * TC: O(N)
     * SC: O(N)
     * 
     * @param nStairs
     * @return
     */
    private static int countDistinctWayToClimbStair(long nStairs) {
        int[] dp = new int[(int) nStairs + 1];
        return countWaysTabulation(nStairs, dp);
    }

    private static int countWaysTabulation(long nStairs, int[] dp) {
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= nStairs; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[(int) nStairs];
    }

    /**
     * Using Space Optimization
     * 
     * TC: O(N)
     * SC: O(1)
     * 
     */
    private static int countWaysOptimization(long nStairs) {
        long prev2 = 1, prev = 1, current = 0;
        long mod = 1000000007;
        for (int i = 2; i <= (int) nStairs; i++) {
            current = (prev + prev2) % mod;
            prev2 = prev;
            prev = current;
        }
        return (int) prev;
    }
}
