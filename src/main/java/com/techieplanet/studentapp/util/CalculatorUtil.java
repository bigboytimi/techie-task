package com.techieplanet.studentapp.util;

import java.util.*;

/**
 * @author timiolowookere
 * @since 29-11-2025
 */
public class CalculatorUtil {

    public static double calculateMean(List<Integer> scores) {
        return scores.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    public static double calculateMedian(List<Integer> scores) {
        List<Integer> copy = new ArrayList<>(scores);
        Collections.sort(copy);
        int n = copy.size();
        if (n % 2 == 1) return copy.get(n/2);
        return (copy.get(n/2 - 1) + copy.get(n/2)) / 2.0;
    }


    public static Integer calculateMode(List<Integer> scores) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (Integer s : scores) freq.put(s, freq.getOrDefault(s, 0) + 1);
        int maxFreq = 0;
        Integer mode = null;
        boolean multi = false;
        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            if (e.getValue() > maxFreq) {
                maxFreq = e.getValue();
                mode = e.getKey();
                multi = false;
            } else if (e.getValue() == maxFreq) {
                multi = true;
            }
        }
        if (maxFreq <= 1 || multi) return null;
        return mode;
    }
}
