package one_dimention;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * CodingNinjas: House Robber II
 * 
 * Link: https://www.codingninjas.com/studio/problems/house-robber-ii_839733
 * 
 * TC: O(2N) ~ O(N)
 * SC: O(1)
 */
public class P4_House_Robber_II {
    public static void main(String[] args) {
        int[] valueInHouse = { 1, 5, 1, 2, 6 };
        long maxPropertyLooted = houseRobber(valueInHouse);
        System.out.println("Maximum amount of money robbed in houses with property " + Arrays.toString(valueInHouse)
                + " is : " + maxPropertyLooted);
    }

    private static long houseRobber(int[] valueInHouse) {
        int n = valueInHouse.length;
        if (n == 1) {
            return valueInHouse[0];
        }
        ArrayList<Integer> temp1 = new ArrayList<>();
        ArrayList<Integer> temp2 = new ArrayList<>();
        for (int i = 0; i < valueInHouse.length; i++) {
            if (i != 0) {
                temp1.add(valueInHouse[i]);
            }
            if (i != n - 1) {
                temp2.add(valueInHouse[i]);
            }
        }
        return Math.max(maximumNonAdjacentSumSpaceOptimization(temp1, n - 1),
                maximumNonAdjacentSumSpaceOptimization(temp2, n - 1));
    }

    /**
     *
     * Using Space Optimization
     * 
     * TC: O(N)
     * SC: O(1)
     * 
     * @param nums
     * @param n
     * @return
     */
    private static long maximumNonAdjacentSumSpaceOptimization(ArrayList<Integer> nums, int n) {
        long prev2 = 0L;
        long prev1 = 0L;
        long current = 0L;
        for (int i = 0; i < n; i++) {
            // pick
            long pick = nums.get(i) + prev2;
            // not pick
            long notpick = prev1;
            current = Math.max(pick, notpick);
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }

}
