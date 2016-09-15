package com.mhurd.euler.helpers;

import java.util.stream.LongStream;

public interface CollatzSequence extends LongStream {

    class CollatzSupplier {
        private final long start;
        private long previous = 0L;

        CollatzSupplier(long start) {
            this.start = start;
        }

        long next() {
            if (previous == 0L) {
                previous = start;
                return start;
            } else if (previous % 2 == 0) {
                // even
                previous = previous / 2;
                return previous;
            } else {
                // odd
                previous = (3 * previous) + 1;
                return previous;
            }
        }
    }

    static LongStream stream(long start) {
        final CollatzSupplier supplier = new CollatzSupplier(start);
        return LongStream.generate(supplier::next);
    }

}
