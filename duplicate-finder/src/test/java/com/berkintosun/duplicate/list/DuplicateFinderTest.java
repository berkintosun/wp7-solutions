package com.berkintosun.duplicate.list;

import com.berkintosun.duplicate.api.DuplicateFinder;
import com.berkintosun.duplicate.list.impl.DuplicateFinderImpl;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("Test with no duplicates")
    void testNoDuplicates() {
        List<String> input = Arrays.asList("1", "2", "3", "4");
        DuplicateFinder<List<String>> finder = new DuplicateFinderImpl<>();
        assertTrue(finder.findDuplicates(input).isEmpty());
    }

    @Test
    @DisplayName("Test with all same elements")
    void testAllSameElements() {
        List<String> input = Arrays.asList("1", "1", "1", "1");
        DuplicateFinder<List<String>> finder = new DuplicateFinderImpl<>();
        assertEquals(Collections.singletonList("1"), finder.findDuplicates(input));
    }

    @Test
    @DisplayName("Test with multiple duplicates")
    void testMultipleDuplicates() {
        List<String> input = Arrays.asList("z", "x", "z", "x", "y", "y");
        DuplicateFinder<List<String>> finder = new DuplicateFinderImpl<>();
        List<String> expected = Arrays.asList("z", "x", "y");
        assertEquals(expected, finder.findDuplicates(input));
    }
}