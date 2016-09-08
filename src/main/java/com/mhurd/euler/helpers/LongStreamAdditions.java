package com.mhurd.euler.helpers;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.LongBinaryOperator;
import java.util.function.LongPredicate;
import java.util.stream.LongStream;

/**
 * Wraps the LongStream to add a function that allows you to run a reduce on an infinite stream
 * by supplying a predicate to indicate when the reduce should exit.
 */
public class LongStreamAdditions {

    @FunctionalInterface
    public interface LongWindowReduce {
        long reduce(long accumulator, long[] windowContents);
    }

    static class LongReduceWindow {

        private final long[] window;

        LongReduceWindow(int size, long identity) {
            this.window = new long[size];
            Arrays.fill(window, identity);
        }

        void insertInToWindow(long value) {
            System.arraycopy(window, 0, window, 1, window.length - 1);
            window[0] = value;
        }

        long[] getContents() {
            return window;
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
        for (long i = sum; continuePredicate.test(i); i = itr.next()) {
            sum = combiner.applyAsLong(sum, i);
        }
        return sum;
    }

    /**
     * Run a reduce on the wrapped LongStream but provide a window size to operate over and a
     * custom reduction function that operates over that window.
     *
     * @param windowSize       - the size of the window to operate over
     * @param identity         - the identity value for the reduction operation (i.e. 1 for multiplication, 0
     *                         for addition)
     * @param start            - the start value for the accumulator
     * @param longWindowReduce - the reduce operation for the window
     * @return the reduction
     */
    public long windowReduce(final int windowSize, final long identity, final long start,
                             final LongWindowReduce longWindowReduce) {
        final LongReduceWindow window = new LongReduceWindow(windowSize, identity);
        return stream.reduce(start, (a, v) -> {
            window.insertInToWindow(v);
            return longWindowReduce.reduce(a, window.getContents());
        });
    }

    /**
     * Finds the first item that matches the supplied predicate from this stream.
     *
     * @param predicate - the predicate to use
     * @return the first number
     */
    public Optional<Long> findFirst(final LongPredicate predicate) {
        final Iterator<Long> itr = stream.iterator();
        while (itr.hasNext()) {
            long next = itr.next();
            if (predicate.test(next)) {
                return Optional.of(next);
            }
        }
        return Optional.empty();
    }

}
