package com.mhurd.euler.helpers;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.LongBinaryOperator;
import java.util.function.LongPredicate;
import java.util.stream.LongStream;

/**
 * Wraps the LongStream to add a function that allows you to run a reduce on an infinite stream
 * by supplying a predicate to indicate when the reduce should exit.
 */
public class LongStreamAdditions {

    @FunctionalInterface
    public interface LongWindowReducer {
        long reduce(long accumulator, long[] windowContents);
    }

    static class LongReduceWindow {

        private final long[] window;
        private final int size;
        private int count = 0;

        LongReduceWindow(int size, long identity) {
            this.window = new long[size];
            this.size = size;
            Arrays.fill(window, identity);
        }

        void insertInToWindow(long value) {
            if (count < size) {
                count++;
            }
            System.arraycopy(window, 0, window, 1, window.length - 1);
            window[0] = value;
        }

        long[] getContents() {
            if (count == size) {
                return window;
            } else {
                return Arrays.copyOfRange(window, 0, count);
            }
        }

    }

    private final LongStream stream;

    private LongStreamAdditions(final LongStream stream) {
        this.stream = stream;
    }

    public static LongStreamAdditions wrap(final LongStream stream) {
        return new LongStreamAdditions(stream);
    }

    /**
     * Run a reduce on the wrapped LongStream but provide an additional parameter over the usual
     * reduce that specifies a Predicate that allows the reduce to exit early so it can be used
     * on infinite streams.
     *
     * @param start             - the start value for the accumulator
     * @param combiner          - the combiner
     * @param continuePredicate - the predicate indicating whether the reduce should continue
     * @return the reduction
     */
    public long conditionalReduce(final long start, final LongBinaryOperator combiner,
                                  final LongPredicate continuePredicate) {
        final Iterator<Long> itr = stream.iterator();
        long sum = start;
        for (long i = itr.next(); continuePredicate.test(i); i = itr.next()) {
            sum = combiner.applyAsLong(sum, i);
        }
        return sum;
    }

    /**
     * Run a reduce on the wrapped LongStream but provide a window size to operate over and a
     * custom reduction function that operates over that window.
     *
     * @param windowSize        - the size of the window to operate over
     * @param identity          - the identity value for the reduction operation (i.e. 1 for multiplication, 0
     *                          for addition)
     * @param start             - the start value for the accumulator
     * @param longWindowReducer - the reduce operation for the window
     * @return the reduction
     */
    public long windowReduce(final int windowSize, final long identity, final long start,
                             final LongWindowReducer longWindowReducer) {
        final LongReduceWindow window = new LongReduceWindow(windowSize, identity);
        return stream.reduce(start, (a, v) -> {
            window.insertInToWindow(v);
            return longWindowReducer.reduce(a, window.getContents());
        });
    }

}
