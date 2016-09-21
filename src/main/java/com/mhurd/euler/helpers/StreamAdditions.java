package com.mhurd.euler.helpers;

import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Wraps a stream to add a function that allows you to run a reduce on an infinite stream
 * by supplying a predicate to indicate when the reduce should exit.
 *
 * A is the accumulator type
 * T is the stream type
 */
public class StreamAdditions<A, T> {

    private final Stream<T> stream;

    public StreamAdditions(final Stream<T> stream) {
        this.stream = stream;
    }

    /**
     * Run a reduce on the wrapped Stream but provide an additional parameter over the usual
     * reduce that specifies a Predicate that allows the reduce to exit early so it can be used
     * on infinite streams.
     *
     * @param start             - the start value for the accumulator
     * @param combiner          - the combiner
     * @param continuePredicate - the predicate indicating whether the reduce should continue
     * @return the reduction
     */
    public A conditionalReduce(final A start, final BiFunction<A, T, A> combiner,
                                  final Predicate<T> continuePredicate) {
        final Iterator<T> itr = stream.iterator();
        A sum = start;
        for (T i = itr.next(); continuePredicate.test(i); i = itr.next()) {
            sum = combiner.apply(sum, i);
        }
        return sum;
    }

}
