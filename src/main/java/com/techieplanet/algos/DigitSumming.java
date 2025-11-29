package com.techieplanet.algos;

/**
 * @author timiolowookere
 * @since 29-11-2025
 */
public class DigitSumming {
    public static int sumDigits(String s) {
        if (s.isEmpty()) return 0;
        return (s.charAt(0) - '0') + sumDigits(s.substring(1));
    }

    public static int digitalRoot(String s) {
        int sum = sumDigits(s);
        if (sum < 10) return sum;
        return digitalRoot(String.valueOf(sum));
    }

    public static void main(String[] args) {
        String input = "1234445";
        int root = digitalRoot(input);
        System.out.println(root);
    }
}
