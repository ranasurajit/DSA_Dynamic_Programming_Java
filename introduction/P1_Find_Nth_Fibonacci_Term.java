package introduction;

import java.util.Arrays;

/**
 * 
 * CodingNinjas: Nth Fibonacci Number
 * 
 * Link: https://www.codingninjas.com/studio/problems/nth-fibonacci-number_74156
 * 
 */
public class P1_Find_Nth_Fibonacci_Term {
    public static void main(String[] args) {
        int n = 5;
        int fiboTerm1 = nthFibonacciMemo(n);
        System.out.println(n + "th Fibonacci Term (using memoization) is : " + fiboTerm1);

        int fiboTerm2 = nthFibonacciTab(n);
        System.out.println(n + "th Fibonacci Term (using tabulation) is : " + fiboTerm2);

        int fiboTerm3 = nthFibonacciOptimal(n);
        System.out.println(n + "th Fibonacci Term (using space optimization) is : " + fiboTerm3);
    }

    private static int nthFibonacciMemo(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return nthFibonacciMemoize(n, dp);
    }

    /**
     * Using Memoization - Top Down Approach
     * TC: O(N)
     * SC: O(N) for recursion stack + O(N) for dp Array ~ O(N)
     * 
     * @param n
     * @param dp
     * @return
     */
    private static int nthFibonacciMemoize(int n, int[] dp) {
        if (n <= 1) {
            return n;
        }
        if (dp[n] != -1) {
            return dp[n];
        }
        dp[n] = nthFibonacciMemoize(n - 1, dp) + nthFibonacciMemoize(n - 2, dp);
        return dp[n];
    }

    private static int nthFibonacciTab(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return nthFibonacciTabulation(n, dp);
    }

    /**
     * Using Tabulation - Bottom Up Approach
     * 
     * TC: O(N)
     * SC: O(N)
     * 
     * @param n
     * @param dp
     * @return
     */
    private static int nthFibonacciTabulation(int n, int[] dp) {
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * Using Space Optimization
     * 
     * TC: O(N)
     * SC: O(1)
     * 
     */
    private static int nthFibonacciOptimal(int n) {
        long prev = 1, prev2 = 0;
        long mod = 1000000007;
        long current = 0;
        for (int i = 2; i <= n; i++) {
            current = (prev + prev2) % mod;
            prev2 = prev;
            prev = current;
        }
        return (int) prev;
    }

}
