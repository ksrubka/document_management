package pl.com.bottega.documentmanagement.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;

import static org.junit.Assert.assertTrue;

public class Assert {

    private static Long EPS = 2L * 1000L;

    public static void assertDatesEqual(Date expected, Date actual) {
        assertTrue(String.format("Expected %1tc, got %2tc", expected, actual), Math.abs(actual.getTime() - expected.getTime()) < EPS);
    }

}
