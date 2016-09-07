package com.mhurd.euler;

import com.mhurd.euler.helpers.EulerSolution;
import org.junit.Test;

import java.util.OptionalLong;
import java.util.stream.LongStream;

import static org.junit.Assert.*;

/**
 * A palindromic number reads the same both ways. The largest palindrome made from the product
 * of two 2-digit numbers is 9009 = 91 × 99. Find the largest palindrome made from the product
 * of two 3-digit numbers.
 * <p>
 * https://projecteuler.net/problem=4
 */
public class Problem4_LargestPalindromeProduct implements EulerSolution {

    private boolean isPalindrome(final long number) {
        final char[] chars = String.valueOf(number).toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != chars[chars.length-1-i]) {
                return  false;
            }
        }
        return true;
    }

    @Test
    public void solve() {
        final OptionalLong result = LongStream.range(100, 1000)
                .flatMap(i -> LongStream.range(i, 1000)
                        .map(j -> i*j) // get all products of the range
                        .filter(this::isPalindrome)) // only keep the palindromes
                .max();
        assertTrue(result.isPresent());
        assertEquals(906609L, result.getAsLong());
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(isPalindrome(999));
        assertTrue(isPalindrome(2222));
        assertTrue(isPalindrome(1022201));
        assertTrue(isPalindrome(1001));
        assertTrue(isPalindrome(9034309));
        assertTrue(isPalindrome(1));
        assertFalse(isPalindrome(10));
        assertFalse(isPalindrome(100));
    }

}
