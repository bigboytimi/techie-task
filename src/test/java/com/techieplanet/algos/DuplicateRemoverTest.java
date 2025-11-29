package com.techieplanet.algos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DuplicateRemoverTest {

    @Test
    void testRemoveDuplicatesNormalCase() {
        int[][] input = {
                {1, 3, 1, 2, 3, 4, 4, 3, 5},
                {1, 1, 1, 1, 1, 1, 1}
        };

        int[][] expected = {
                {1, 3, 0, 2, 0, 4, 0, 0, 5},
                {1, 0, 0, 0, 0, 0, 0}
        };

        assertArrayEquals(expected, DuplicateRemover.removeDuplicates(input));
    }

    @Test
    void testRemoveDuplicatesEmptyArray() {
        int[][] input = {};
        int[][] expected = {};
        assertArrayEquals(expected, DuplicateRemover.removeDuplicates(input));
    }

    @Test
    void testRemoveDuplicatesSingleRowNoDuplicates() {
        int[][] input = {
                {1, 2, 3, 4, 5}
        };
        int[][] expected = {
                {1, 2, 3, 4, 5}
        };
        assertArrayEquals(expected, DuplicateRemover.removeDuplicates(input));
    }

    @Test
    void testRemoveDuplicatesSingleRowAllDuplicates() {
        int[][] input = {
                {7, 7, 7, 7}
        };
        int[][] expected = {
                {7, 0, 0, 0}
        };
        assertArrayEquals(expected, DuplicateRemover.removeDuplicates(input));
    }

    @Test
    void testRemoveDuplicatesMultipleRowsMixed() {
        int[][] input = {
                {1, 2, 2, 3},
                {4, 4, 5, 5, 5},
                {6, 7, 8, 9}
        };

        int[][] expected = {
                {1, 2, 0, 3},
                {4, 0, 5, 0, 0},
                {6, 7, 8, 9}
        };

        assertArrayEquals(expected, DuplicateRemover.removeDuplicates(input));
    }
}
