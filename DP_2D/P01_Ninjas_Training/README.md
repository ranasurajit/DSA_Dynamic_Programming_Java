
# Ninja’s Training

## Problem Statement

Ninja has a training schedule spanning **N** days. Each day, he can perform one of three activities:

1. **Running**
2. **Fighting Practice**
3. **Learning New Moves**

Each activity yields certain merit points, which vary daily. The constraint is that **Ninja cannot perform the same activity on two consecutive days**.

Given a 2D array `POINTS` of size `N x 3`, where `POINTS[i][j]` represents the merit points for activity `j` on day `i`, determine the **maximum total merit points** Ninja can accumulate over `N` days.

## Input Format

- The first line contains an integer `T`, the number of test cases.
- For each test case:
  - The first line contains an integer `N`, the number of days.
  - The next `N` lines each contain three integers, representing the merit points for the three activities on that day.

## Output Format

For each test case, output a single integer: the maximum merit points Ninja can earn.

## Constraints

- 1 ≤ T ≤ 10
- 1 ≤ N ≤ 10⁵
- 1 ≤ POINTS[i][j] ≤ 100

## Sample Input

```
2
3
1 2 5
3 1 1
3 3 3
3
10 40 70
20 50 80
30 60 90
```

## Sample Output

```
11
210
```

## Explanation

**Test Case 1:**

- Day 1: Choose activity 2 (5 points)
- Day 2: Choose activity 0 (3 points)
- Day 3: Choose activity 1 or 2 (3 points)

Total = 5 + 3 + 3 = **11**

**Test Case 2:**

- Day 1: Choose activity 2 (70 points)
- Day 2: Choose activity 1 (50 points)
- Day 3: Choose activity 2 (90 points)

Total = 70 + 50 + 90 = **210**

## Approach

This problem is a classic example of **Dynamic Programming** with the following characteristics:

- **State Definition**: Let `dp[i][j]` represent the maximum merit points up to day `i` if Ninja performs activity `j` on day `i`.
- **Transition**: For each day `i` and activity `j`, `dp[i][j] = POINTS[i][j] + max(dp[i-1][k])` for all `k ≠ j`.
- **Base Case**: For day 0, `dp[0][j] = POINTS[0][j]`.
- **Optimization**: Since only the previous day's values are needed, space can be optimized to O(1) by using two arrays.

Implementing this approach ensures an efficient solution with a time complexity of O(N) and space complexity of O(1).

