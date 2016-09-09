package com.mhurd.euler.helpers;

import com.mhurd.euler.helpers.Primes;
import org.junit.Test;

import java.util.Iterator;
import java.util.stream.LongStream;

import static org.junit.Assert.*;

public class PrimesTest {

    @Test
    public void testPrimes() {
        LongStream primes = Primes.primes();
        Iterator<Long> itr = primes.iterator();
        assertEquals(Long.valueOf(2), itr.next());
        assertEquals(Long.valueOf(3), itr.next());
        assertEquals(Long.valueOf(5), itr.next());
        assertEquals(Long.valueOf(7), itr.next());
        assertEquals(Long.valueOf(11), itr.next());
        assertEquals(Long.valueOf(13), itr.next());
        assertEquals(Long.valueOf(17), itr.next());
        assertEquals(Long.valueOf(19), itr.next());
        assertEquals(Long.valueOf(23), itr.next());
    }

    @Test
    public void testIsPrime() {
        assertFalse(Primes.isPrime(1));
        assertTrue(Primes.isPrime(2));
        assertTrue(Primes.isPrime(3));
        assertFalse(Primes.isPrime(4));
        assertTrue(Primes.isPrime(5));
        assertFalse(Primes.isPrime(6));
        assertTrue(Primes.isPrime(7));
        assertFalse(Primes.isPrime(8));
        assertFalse(Primes.isPrime(9));
        assertFalse(Primes.isPrime(10));
        assertTrue(Primes.isPrime(11));
        assertFalse(Primes.isPrime(12));
        assertTrue(Primes.isPrime(13));
        assertFalse(Primes.isPrime(14));
        assertFalse(Primes.isPrime(15));
        assertFalse(Primes.isPrime(16));
        assertTrue(Primes.isPrime(17));
        assertFalse(Primes.isPrime(18));
        assertTrue(Primes.isPrime(19));
        assertFalse(Primes.isPrime(20));
        assertFalse(Primes.isPrime(21));
        assertFalse(Primes.isPrime(22));
        assertTrue(Primes.isPrime(23));
    }

}
