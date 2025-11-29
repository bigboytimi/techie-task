package com.techieplanet.algos;

/**
 * @author timiolowookere
 * @since 29-11-2025
 */
public class DigitSumming {
    // Part A logic reused
    public static int sumDigits(String s) {
        if (s.length() == 0) return 0;
        return (s.charAt(0) - '0') + sumDigits(s.substring(1));
    }

    // Part B - Recursive digital root
    public static int digitalRoot(String s) {
        int sum = sumDigits(s);
        if (sum < 10) return sum;
        return digitalRoot(String.valueOf(sum));
    }
}
