package com.mhurd.euler.helpers;

import java.util.stream.LongStream;

public interface FibonacciSequence extends LongStream {

    class FibonacciSupplier {
        private long previous = 0L;
        private long current = 1L;

        long next() {
            long next = previous + current;
            previous = current;
            current = next;
            return next;
        }
    }

    static LongStream stream() {
        final FibonacciSupplier supplier = new FibonacciSupplier();
        return LongStream.generate(supplier::next);
    }

}
