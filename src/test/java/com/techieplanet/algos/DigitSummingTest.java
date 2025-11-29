package com.techieplanet.algos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DigitSummingTest {
    @Test
    void testSumDigitsNormalInput() {
        assertEquals(15, DigitSumming.sumDigits("12345"));
        assertEquals(10, DigitSumming.sumDigits("1234"));
        assertEquals(0, DigitSumming.sumDigits("0"));
    }

    @Test
    void testSumDigitsEmptyInput() {
        assertEquals(0, DigitSumming.sumDigits(""));
    }

    @Test
    void testDigitalRootSingleDigit() {
        assertEquals(7, DigitSumming.digitalRoot("7"));
    }

    @Test
    void testDigitalRootLargeNumber() {
        assertEquals(9, DigitSumming.digitalRoot("999999"));
    }

    @Test
    void testDigitalRootEmptyString() {
        assertEquals(0, DigitSumming.digitalRoot(""));
    }
}