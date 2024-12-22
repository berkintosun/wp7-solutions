package com.berkintosun.duplicate.list;

import com.berkintosun.duplicate.api.DuplicateFinder;
import com.berkintosun.duplicate.list.impl.DuplicateFinderImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DuplicateFinderTest {

    @Test
    @DisplayName("Test with example case from problem statement")
    void testExampleCase() {
        List<String> input = Arrays.asList("b", "a", "c", "c", "e", "a", "c", "d", "c", "d");
        DuplicateFinder<List<String>> finder = new DuplicateFinderImpl<>();
        List<String> expected = Arrays.asList("a", "c", "d");
        assertEquals(expected, finder.findDuplicates(input));
    }

    @Test
    @DisplayName("Test with no duplicates should return empty")
    void testNoDuplicates() {
        List<String> input = Arrays.asList("1", "2", "3", "4");
        DuplicateFinder<List<String>> finder = new DuplicateFinderImpl<>();
        assertTrue(finder.findDuplicates(input).isEmpty());
    }

    @Test
    @DisplayName("Test with all same elements should return single element")
    void testAllSameElements() {
        List<String> input = Arrays.asList("1", "1", "1", "1");
        DuplicateFinder<List<String>> finder = new DuplicateFinderImpl<>();
        assertEquals(Collections.singletonList("1"), finder.findDuplicates(input));
    }

    @Test
    @DisplayName("Test with multiple duplicates should return all duplicates with respect to input order")
    void testMultipleDuplicates() {
        List<String> input = Arrays.asList("z", "x", "z", "x", "y", "y");
        DuplicateFinder<List<String>> finder = new DuplicateFinderImpl<>();
        List<String> expected = Arrays.asList("z", "x", "y");
        assertEquals(expected, finder.findDuplicates(input));
    }

    @Test
    @DisplayName("Test with empty array should return empty")
    void testEmptyArray() {
        List<String> input = List.of();
        DuplicateFinder<List<String>> finder = new DuplicateFinderImpl<>();
        assertTrue(finder.findDuplicates(input).isEmpty());
    }

    @Test
    @DisplayName("Test with null array should throw exception")
    void testNullArray() {
        assertThrows(IllegalArgumentException.class, () -> new DuplicateFinderImpl<>().findDuplicates(null));
    }

    @Test
    @DisplayName("Test null values should be preserved")
    void testDataWithNullValues() {
        List<String> input = Arrays.asList("1", null, "3", null, "3", "1","2");
        DuplicateFinder<List<String>> finder = new DuplicateFinderImpl<>();
        List<String> expected = Arrays.asList("1", null, "3");
        assertEquals(expected, finder.findDuplicates(input));
    }
}