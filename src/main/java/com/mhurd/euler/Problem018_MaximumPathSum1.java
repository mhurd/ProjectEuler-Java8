package com.mhurd.euler;

import com.mhurd.euler.helpers.EulerSolution;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/**
 * By starting at the top of the triangle below and moving to adjacent numbers on the row below,
 * the maximum total from top to bottom is 23.
 * <p>
 *    3
 *   7 4
 *  2 4 6
 * 8 5 9 3
 * <p>
 * That is, 3 + 7 + 4 + 9 = 23. Find the maximum total from top to bottom of the triangle below:
 * <p>
 *               75
 *              95 64
 *             17 47 82
 *            18 35 87 10
 *           20 04 82 47 65
 *          19 01 23 75 03 34
 *         88 02 77 73 07 63 67
 *        99 65 04 28 06 16 70 92
 *       41 41 26 56 83 40 80 70 33
 *      41 48 72 33 47 32 37 16 94 29
 *     53 71 44 65 25 43 91 52 97 51 14
 *    70 11 33 28 77 73 17 78 39 68 17 57
 *   91 71 52 38 17 14 91 43 58 50 27 29 48
 *  63 66 04 68 89 53 67 30 73 16 69 87 40 31
 * 04 62 98 27 23 09 70 98 73 93 38 53 60 04 23
 * <p>
 *  NOTE: As there are only 16384 routes, it is possible to solve this problem by trying every route.
 *  However, Problem 67, is the same challenge with a triangle containing one-hundred rows; it cannot
 *  be solved by brute force, and requires a clever method! ;o)
 * <p>
 * https://projecteuler.net/problem=18
 */
class Problem018_MaximumPathSum1 implements EulerSolution {

    private static final int[][] TRIANGLE = new int[][]
            {new int[] {75},
            new int[] {95,64},
            new int[] {17,47,82},
            new int[] {18,35,87,10},
            new int[] {20,4,82,47,65},
            new int[] {19,1,23,75,3,34},
            new int[] {88,2,77,73,7,63,67},
            new int[] {99,65,4,28,6,16,70,92},
            new int[] {41,41,26,56,83,40,80,70,33},
            new int[] {41,48,72,33,47,32,37,16,94,29},
            new int[] {53,71,44,65,25,43,91,52,97,51,14},
            new int[] {70,11,33,28,77,73,17,78,39,68,17,57},
            new int[] {91,71,52,38,17,14,91,43,58,50,27,29,48},
            new int[] {63,66,4,68,89,53,67,30,73,16,69,87,40,31},
            new int[] {4,62,98,27,23,9,70,98,73,93,38,53,60,4,23},
    };

    private int routeWithMaxSum(int[][] triangle) {
        validateTriangle(triangle);
        for (int i = triangle.length - 1; i >= 0; i--) {
            // start at the bottom
            for (int j = 0; j <= triangle[i].length - 2; j++) {
                int[] thisRow = triangle[i];
                int[] rowAbove = triangle[i-1];
                rowAbove[j] = rowAbove[j] + Math.max(thisRow[j], thisRow[j+1]);
                if (i == 1) {
                    return triangle[0][0]; // return the total from the top
                }
            }
        }
        throw new IllegalArgumentException("Not a valid triangle!");
    }

    private void validateTriangle(int[][] triangle) {
        if (triangle.length < 2
                || triangle[0].length != 1) {
            throw new IllegalArgumentException("Not a valid triangle!");
        }
        IntStream.range(1, triangle.length).forEach(i-> {
            if (triangle[i-1].length != triangle[i].length - 1) {
                throw new IllegalArgumentException("Not a valid triangle!");
            }
        });
    }

    public void solve() {
        int result = routeWithMaxSum(TRIANGLE);
        assertEquals(1074, result);
    }

}
