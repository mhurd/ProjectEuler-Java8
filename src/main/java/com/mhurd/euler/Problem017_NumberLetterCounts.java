package com.mhurd.euler;

import com.mhurd.euler.helpers.EulerSolution;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/**
 * If the numbers 1 to 5 are written out in words: one, two, three, four, five, then there are
 * 3 + 3 + 5 + 4 + 4 = 19 letters used in total. If all the numbers from 1 to 1000 (one thousand)
 * inclusive were written out in words, how many letters would be used?
 * NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two) contains 23
 * letters and 115 (one hundred and fifteen) contains 20 letters. The use of "and" when writing out
 * numbers is in compliance with British usage.
 * <p>
 * https://projecteuler.net/problem=17
 */
class Problem017_NumberLetterCounts implements EulerSolution {

    private static final Map<Integer, String> WORDS = new HashMap<>();

    static {
        WORDS.put(1, "one");
        WORDS.put(2, "two");
        WORDS.put(3, "three");
        WORDS.put(4, "four");
        WORDS.put(5, "five");
        WORDS.put(6, "six");
        WORDS.put(7, "seven");
        WORDS.put(8, "eight");
        WORDS.put(9, "nine");
        WORDS.put(10, "ten");
        WORDS.put(11, "eleven");
        WORDS.put(12, "twelve");
        WORDS.put(13, "thirteen");
        WORDS.put(14, "fourteen");
        WORDS.put(15, "fifteen");
        WORDS.put(16, "sixteen");
        WORDS.put(17, "seventeen");
        WORDS.put(18, "eighteen");
        WORDS.put(19, "nineteen");
        WORDS.put(20, "twenty");
        WORDS.put(30, "thirty");
        WORDS.put(40, "forty");
        WORDS.put(50, "fifty");
        WORDS.put(60, "sixty");
        WORDS.put(70, "seventy");
        WORDS.put(80, "eighty");
        WORDS.put(90, "ninety");
        WORDS.put(100, "hundred");
        WORDS.put(1000, "thousand");
    }

    private String asSentence(int number) {
        if (number >= 10000) {
            throw new IllegalArgumentException("Only supports numbers below 10,000");
        }
        StringBuilder result = new StringBuilder();
        int thousandsDigit = number % 10000 / 1000;
        if (thousandsDigit > 0) {
            result.append(WORDS.get(thousandsDigit)).append(" thousand ");
        }
        int remainingHundreds = number % 1000;
        int hundredsDigit = remainingHundreds / 100;
        if (hundredsDigit > 0) {
            result.append(WORDS.get(hundredsDigit)).append(" hundred");
        }
        int remainingTens = number % 100;
        if (remainingTens != 0) {
            if (result.length() > 0) {
                result.append(" and ");
            }
            String last = WORDS.get(remainingTens);
            if (last == null) {
                int tensDigit = remainingTens / 10;
                result.append(WORDS.get(tensDigit * 10)).append("-");
                int remainingUnits = number % 10;
                result.append(WORDS.get(remainingUnits));
            } else {
                result.append(last);
            }
        }
        return result.toString();
    }

    public void solve() {
        int total = IntStream.rangeClosed(1, 1000)
                .mapToObj(Integer::valueOf)
                .map(this::asSentence)
                //.peek(System.out::println)
                .map(s -> s.replaceAll("[\\s-]", "").length())
                .reduce(0, (a,l)  -> a + l);
        assertEquals(21124, total);
    }

}
