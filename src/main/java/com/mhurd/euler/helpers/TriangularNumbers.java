package com.mhurd.euler.helpers;

import java.util.stream.LongStream;

public interface TriangularNumbers extends LongStream {

    class TriangularNumberSupplier {
        private long previous = 0L;
        private long count = 0L;

        long next() {
            count++;
            long next = previous + count;
            previous = next;
            return next;
        }
    }

    static LongStream stream() {
        final TriangularNumberSupplier supplier = new TriangularNumberSupplier();
        return LongStream.generate(supplier::next);
    }

}
