package com.techieplanet.algos;

/**
 * @author timiolowookere
 * @since 29-11-2025
 */
public class DuplicateRemover {
    public static int[][] removeDuplicates(int[][] arr) {

        for (int i = 0; i < arr.length; i++) {

            // Find maximum value in row to size visited array efficiently
            int maxVal = 0;
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] > maxVal) {
                    maxVal = arr[i][j];
                }
            }

            int[] visited = new int[maxVal + 1];

            for (int j = 0; j < arr[i].length; j++) {
                int val = arr[i][j];
                if (visited[val] == 0) {
                    visited[val] = 1; // first appearance
                } else {
                    arr[i][j] = 0; // duplicate → replace with 0
                }
            }
        }

        return arr;
    }
}
