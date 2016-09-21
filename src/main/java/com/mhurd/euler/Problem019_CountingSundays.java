package com.mhurd.euler;

import com.mhurd.euler.helpers.EulerSolution;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * You are given the following information, but you may prefer to do some research for yourself.
 * - 1 Jan 1900 was a Monday.
 * - Thirty days has September,
 * - April, June and November.
 * - All the rest have thirty-one,
 * - Saving February alone,
 * - Which has twenty-eight, rain or shine.
 * - And on leap years, twenty-nine.
 * - A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.
 * How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
 * <p>
 * https://projecteuler.net/problem=19
 */
class Problem019_CountingSundays implements EulerSolution {

    static class Date {

        private final int dayOfMonth;
        private final DayOfWeek dayOfWeek;
        private final Month month;
        private final int year;

        Date(final int dayOfMonth, final DayOfWeek dayOfWeek, final Month month, final int year) {
            this.dayOfMonth = dayOfMonth;
            this.dayOfWeek = dayOfWeek;
            this.month = month;
            this.year = year;
        }

        Date(final int dayOfMonth, final Month month, final int year) {
            this.dayOfMonth = dayOfMonth;
            this.dayOfWeek = null;
            this.month = month;
            this.year = year;
        }

        int getDayOfMonth() {
            return dayOfMonth;
        }

        DayOfWeek getDayOfWeek() {
            return dayOfWeek;
        }

        Month getMonth() {
            return month;
        }

        int getYear() {
            return year;
        }

        boolean isLeapYear() {
            return ((year % 100 == 0 && year % 400 == 0) || year % 4 == 0);
        }

        DayOfWeek nextDayOfWeek() {
            int length = DayOfWeek.values().length;
            for (int i = 0; i < length; i++) {
                if (DayOfWeek.values()[i] == getDayOfWeek()) {
                    return (i + 1 >=  length) ? DayOfWeek.values()[0] : DayOfWeek.values()[i + 1];
                }
            }
            throw new IllegalStateException("Run out of days!");
        }

        Month nextMonth() {
            int length = Month.values().length;
            for (int i = 0; i < length; i++) {
                if (Month.values()[i] == getMonth()) {
                    return (i + 1 >=  length) ? Month.values()[0] : Month.values()[i + 1];
                }
            }
            throw new IllegalStateException("Run out of months!");
        }

        private Date getNext() {
            if (getMonth() == Month.February
                    && isLeapYear()
                    && getDayOfMonth() == 28) {
                // handle Feb and leap years specially
                return new Date(29, nextDayOfWeek(), Month.February, getYear());
            } else {
                boolean dayRollover = (getDayOfMonth() + 1 > getMonth().getDays());
                int newDayOfMonth = (dayRollover) ? 1 : getDayOfMonth() + 1;
                DayOfWeek newDayOfWeek = nextDayOfWeek();
                Month newMonth = (dayRollover) ? nextMonth() : getMonth();
                int newYear = (dayRollover && getMonth() == Month.December) ? getYear() + 1 : getYear();
                return new Date(newDayOfMonth, newDayOfWeek, newMonth, newYear);
            }
        }

        boolean lessThan(Date date) {
            return getYear() < date.getYear()
                    || getYear() == date.getYear()
                    && (getMonth().compareTo(date.getMonth()) == -1
                    || getMonth().compareTo(date.getMonth()) == 0
                    && getDayOfMonth() < date.getDayOfMonth());
        }

        boolean greaterThan(Date date) {
            return getYear() > date.getYear()
                    || getYear() == date.getYear()
                    && (getMonth().compareTo(date.getMonth()) == 1
                    || getMonth().compareTo(date.getMonth()) == 0
                    && getDayOfMonth() > date.getDayOfMonth());
        }

        @Override
        public String toString() {
            return dayOfWeek + " " + dayOfMonth + "-" + month + "-" + year;
        }
    }

    private enum DayOfWeek {
        Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
    }

    private enum Month {
        January(31), February(28), March(31), April(30), May(31), June(30), July(31), August(31), September(30),
        October(31), November(30), December(31);

        private int days;

        Month(int days) {
            this.days = days;
        }

        int getDays() {
            return this.days;
        }
    }

    interface DateStream extends Stream<Problem019_CountingSundays.Date> {

        class DaySupplier {

            private Date next;

            DaySupplier(Date start) {
                this.next = start;
            }

            Date next() {
                Date result = next;
                this.next = result.getNext();
                return result;
            }

        }

        static Stream<Date> stream(Date start) {
            final DaySupplier supplier = new DaySupplier(start);
            return Stream.generate(supplier::next);
        }

    }

    public void solve() {
        Date init = new Date(1, DayOfWeek.Monday, Month.January, 1900);
        Date start = new Date(1, Month.January, 1900);
        Date end = new Date(1, Month.January, 2001);
        long result = DateStream.stream(init)
                .limit(40000) // enough to cover the period in question - this is an infinite stream remember!
                .filter(d -> d.getDayOfWeek() == DayOfWeek.Sunday
                        && d.getDayOfMonth() == 1
                        && d.greaterThan(start)
                        && d.lessThan(end))
                .peek(System.out::println)
                .count();
        assertEquals(171, result);
    }

}
