package com.techieplanet;

/**
 * @author timiolowookere
 * @since 28-11-2025
 */
public class ConvertNumeralsToWords {

    private static final String[] numbers = {
            "", "one", "two", "three", "four", "five", "six",
            "seven", "eight", "nine", "ten", "eleven", "twelve",
            "thirteen", "fourteen", "quarter", "sixteen", "seventeen",
            "eighteen", "nineteen", "twenty", "twenty one", "twenty two",
            "twenty three", "twenty four", "twenty five", "twenty six",
            "twenty seven", "twenty eight", "twenty nine", "half"
    };

    public static String timeInWords(int H, int M) {
        if (H < 1 || H > 12 || M < 0 || M > 59) {
            return "Invalid time input";
        }

        if (M == 0) {
            return capitalize(numbers[H] + " o’clock");
        }
        if (M == 15) {
            return capitalize("quarter past " + numbers[H]);
        }
        if (M == 30) {
            return capitalize("half past " + numbers[H]);
        }
        if (M == 45) {
            int nextHour = (H == 12) ? 1 : H + 1;
            return capitalize("quarter to " + numbers[nextHour]);
        }

        if (M < 30) {
            String minuteWord = (M == 1) ? "minute" : "minutes";
            return capitalize(numbers[M] + " " + minuteWord + " past " + numbers[H]);
        } else {
            int minutesTo = 60 - M;
            int nextHour = (H == 12) ? 1 : H + 1;
            String minuteWord = (minutesTo == 1) ? "minute" : "minutes";
            return capitalize(numbers[minutesTo] + " " + minuteWord + " to " + numbers[nextHour]);
        }
    }

    private static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
