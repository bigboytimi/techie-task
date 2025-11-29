package com.techieplanet.algos;

/**
 * @author timiolowookere
 * @since 29-11-2025
 */
public class DuplicateRemover {
    public static int[][] removeDuplicates(int[][] arr) {

        for (int i = 0; i < arr.length; i++) {

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
                    visited[val] = 1;
                } else {
                    arr[i][j] = 0;
                }
            }
        }

        return arr;
    }

    public static void main(String[] args) {
        int[][] input = {
                {1, 3, 1, 2, 3, 4, 4, 3, 5},
                {1, 1, 1, 1, 1, 1, 1}
        };

        int[][] output = removeDuplicates(input);

        for (int[] row : output) {
            System.out.print("{");
            for (int v : row) {
                System.out.print(v + " ");
            }
            System.out.println("}");
        }
    }
}
