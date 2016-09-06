package com.mhurd.euler.helpers;

import java.util.stream.LongStream;

public interface Primes {

    static boolean isPrime(long number) {
        return number > 1
                && (number == 2
                    || number == 3
                    || LongStream
                           .rangeClosed(2, (long) Math.sqrt((double) number))
                           .noneMatch(n -> number % n == 0));
    }

    static LongStream primes() {
       return LongStream
               .iterate(0, l -> l + 1)
               .filter(Primes::isPrime);
   }

}
