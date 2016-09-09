package com.mhurd.euler.helpers;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class LongStreamAdditionsTest {

    @Test
    public void testWindow() {
        LongStreamAdditions.LongReduceWindow window = new LongStreamAdditions.LongReduceWindow(3, -1);
        window.insertInToWindow(1);
        // note that the arrays are padded with the unit definition
        assertArrayEquals(new long[] {1}, window.getContents());
        window.insertInToWindow(2);
        assertArrayEquals(new long[] {2, 1}, window.getContents());
        window.insertInToWindow(3);
        assertArrayEquals(new long[] {3, 2, 1}, window.getContents());
        window.insertInToWindow(4);
        assertArrayEquals(new long[] {4, 3, 2}, window.getContents());
        window.insertInToWindow(5);
        assertArrayEquals(new long[] {5, 4, 3}, window.getContents());
    }

}
