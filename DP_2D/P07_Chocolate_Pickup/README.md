
# Ninja and His Friends (Chocolate Pickup)

## Problem Statement

Ninja has a grid of size `R x C` where each cell contains some chocolates. Two of his friends, Alice and Bob, are going to help him collect the chocolates.

- Alice starts at the top-left corner `(0, 0)`.
- Bob starts at the top-right corner `(0, C - 1)`.

Both friends move simultaneously to the next row in each step. From any cell, they can move to the cell directly below, diagonally left, or diagonally right in the next row.

At each step:
- If both friends land on the same cell, they collect chocolates only once.
- Otherwise, they collect chocolates from their respective cells.

Your task is to find the maximum number of chocolates Ninja can collect with the help of his friends by following the above rules.

## Input Format

- An integer `R` representing the number of rows.
- An integer `C` representing the number of columns.
- A 2D array `grid` of size `R x C` where `grid[i][j]` denotes the number of chocolates in cell `(i, j)`.

## Output Format

- Return an integer representing the maximum number of chocolates that can be collected.

## Constraints

- `1 <= R, C <= 70`
- `0 <= grid[i][j] <= 100`

## Example

**Input:**
```
R = 3
C = 4
grid = [
  [2, 3, 1, 2],
  [3, 4, 2, 2],
  [5, 6, 3, 5]
]
```

**Output:**
```
21
```

**Explanation:**

One optimal path is:
- Alice: (0,0) → (1,1) → (2,2)
- Bob:   (0,3) → (1,2) → (2,1)

Chocolates collected:
- Step 1: 2 (Alice) + 2 (Bob) = 4
- Step 2: 4 (Alice) + 2 (Bob) = 6
- Step 3: 3 (Alice) + 6 (Bob) = 9

Total = 4 + 6 + 9 = 19

However, there might be a better path yielding 21 chocolates.
