package com.mhurd.euler.helpers;

import org.junit.Test;

import java.util.Iterator;
import java.util.stream.LongStream;

import static org.junit.Assert.assertEquals;

public class CollatzSequenceTest {

    @Test
    public void test() {
        LongStream seq = CollatzSequence.stream(13);
        Iterator<Long> itr = seq.iterator();
        assertEquals(Long.valueOf(13), itr.next());
        assertEquals(Long.valueOf(40), itr.next());
        assertEquals(Long.valueOf(20), itr.next());
        assertEquals(Long.valueOf(10), itr.next());
        assertEquals(Long.valueOf(5), itr.next());
        assertEquals(Long.valueOf(16), itr.next());
        assertEquals(Long.valueOf(8), itr.next());
        assertEquals(Long.valueOf(4), itr.next());
        assertEquals(Long.valueOf(2), itr.next());
        assertEquals(Long.valueOf(1), itr.next());
    }

}
