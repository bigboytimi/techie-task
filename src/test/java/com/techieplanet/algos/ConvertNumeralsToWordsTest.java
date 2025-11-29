package com.techieplanet.algos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author timiolowookere
 * @since 29-11-2025
 */

class ConvertNumeralsToWordsTest {

    @Test
    void testExactHour() {
        assertEquals("Five o’clock", ConvertNumeralsToWords.timeInWords(5, 0));
        assertEquals("Twelve o’clock", ConvertNumeralsToWords.timeInWords(12, 0));
    }

    @Test
    void testQuarterPast() {
        assertEquals("Quarter past five", ConvertNumeralsToWords.timeInWords(5, 15));
        assertEquals("Quarter past twelve", ConvertNumeralsToWords.timeInWords(12, 15));
    }

    @Test
    void testHalfPast() {
        assertEquals("Half past five", ConvertNumeralsToWords.timeInWords(5, 30));
        assertEquals("Half past twelve", ConvertNumeralsToWords.timeInWords(12, 30));
    }

    @Test
    void testQuarterTo() {
        assertEquals("Quarter to six", ConvertNumeralsToWords.timeInWords(5, 45));
        assertEquals("Quarter to one", ConvertNumeralsToWords.timeInWords(12, 45));
    }

    @Test
    void testMinutesPast() {
        assertEquals("One minute past five", ConvertNumeralsToWords.timeInWords(5, 1));
        assertEquals("Twenty nine minutes past five", ConvertNumeralsToWords.timeInWords(5, 29));
    }

    @Test
    void testMinutesTo() {
        assertEquals("One minute to six", ConvertNumeralsToWords.timeInWords(5, 59));
        assertEquals("Twenty eight minutes to six", ConvertNumeralsToWords.timeInWords(5, 32));
    }

    @Test
    void testInvalidTime() {
        assertEquals("Invalid time input", ConvertNumeralsToWords.timeInWords(0, 10));
        assertEquals("Invalid time input", ConvertNumeralsToWords.timeInWords(13, 10));
        assertEquals("Invalid time input", ConvertNumeralsToWords.timeInWords(5, -1));
        assertEquals("Invalid time input", ConvertNumeralsToWords.timeInWords(5, 60));
    }
}