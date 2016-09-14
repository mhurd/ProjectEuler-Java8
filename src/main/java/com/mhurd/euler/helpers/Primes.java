package com.mhurd.euler.helpers;

import java.util.stream.LongStream;

public interface Primes {

    static boolean isPrime(final long number) {
        return number > 1
                && (number == 2
                || number == 3
                || (number % 2 != 0 // gets rid of half the options!
                && LongStream
                .rangeClosed(2, (long) Math.sqrt((double) number))
                .noneMatch(n -> number % n == 0)));
    }

    static LongStream stream() {
        return LongStream
                .iterate(0, l -> l + 1)
                .filter(Primes::isPrime);
    }

}
