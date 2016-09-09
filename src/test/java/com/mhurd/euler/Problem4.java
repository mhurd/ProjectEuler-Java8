package com.mhurd.euler;

import org.junit.Test;

import static com.mhurd.euler.Problem4_LargestPalindromeProduct.isPalindrome;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Problem4 {

    @Test
    public void solve() {
        new Problem4_LargestPalindromeProduct().solve();
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
