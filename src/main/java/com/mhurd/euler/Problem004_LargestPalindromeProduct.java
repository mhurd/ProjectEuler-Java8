package com.mhurd.euler;

import com.mhurd.euler.helpers.EulerSolution;

import java.util.OptionalLong;
import java.util.stream.LongStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * A palindromic number reads the same both ways. The largest palindrome made from the product
 * of two 2-digit numbers is 9009 = 91 × 99. Find the largest palindrome made from the product
 * of two 3-digit numbers.
 * <p>
 * https://projecteuler.net/problem=4
 */
class Problem004_LargestPalindromeProduct implements EulerSolution {

    static boolean isPalindrome(final long number) {
        final char[] chars = String.valueOf(number).toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != chars[chars.length - 1 - i]) {
                return false;
            }
        }
        return true;
    }

    public void solve() {
        final OptionalLong result = LongStream.range(100, 1000)
                .flatMap(i -> LongStream.range(i, 1000)
                        .map(j -> i * j) // get all products of the range
                        .filter(Problem004_LargestPalindromeProduct::isPalindrome)) // only keep the palindromes
                .max();
        assertTrue(result.isPresent());
        assertEquals(906609L, result.getAsLong());
    }

}
