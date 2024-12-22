package com.berkintosun.duplicate.list;

import com.berkintosun.duplicate.api.DuplicateFinder;
import com.berkintosun.duplicate.list.impl.DuplicateFinderImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DuplicateFinderTest {

    static Stream<DuplicateFinder<List<Object>>> provideDuplicateFinders() {
        return Stream.of(
                new DuplicateFinderImpl<>()
        );
    }

    @ParameterizedTest
    @MethodSource("provideDuplicateFinders")
    @DisplayName("Test with example case from problem statement")
    void testExampleCase(DuplicateFinder<List<String>> finder) {
        List<String> input = Arrays.asList("b", "a", "c", "c", "e", "a", "c", "d", "c", "d");
        List<String> expected = Arrays.asList("a", "c", "d");
        assertEquals(expected, finder.findDuplicates(input));
    }

    @ParameterizedTest
    @MethodSource("provideDuplicateFinders")
    @DisplayName("Test with no duplicates should return empty")
    void testNoDuplicates(DuplicateFinder<List<String>> finder) {
        List<String> input = Arrays.asList("1", "2", "3", "4");
        assertTrue(finder.findDuplicates(input).isEmpty());
    }

    @ParameterizedTest
    @MethodSource("provideDuplicateFinders")
    @DisplayName("Test with all same elements should return single element")
    void testAllSameElements(DuplicateFinder<List<String>> finder) {
        List<String> input = Arrays.asList("1", "1", "1", "1");
        assertEquals(Collections.singletonList("1"), finder.findDuplicates(input));
    }

    @ParameterizedTest
    @MethodSource("provideDuplicateFinders")
    @DisplayName("Test with multiple duplicates should return all duplicates with respect to input order")
    void testMultipleDuplicates(DuplicateFinder<List<String>> finder) {
        List<String> input = Arrays.asList("z", "x", "z", "x", "y", "y");
        List<String> expected = Arrays.asList("z", "x", "y");
        assertEquals(expected, finder.findDuplicates(input));
    }

    @ParameterizedTest
    @MethodSource("provideDuplicateFinders")
    @DisplayName("Test with empty array should return empty")
    void testEmptyArray(DuplicateFinder<List<String>> finder) {
        List<String> input = List.of();
        assertTrue(finder.findDuplicates(input).isEmpty());
    }

    @ParameterizedTest
    @MethodSource("provideDuplicateFinders")
    @DisplayName("Test with null array should throw exception")
    void testNullArray(DuplicateFinder<List<String>> finder) {
        assertThrows(IllegalArgumentException.class, () -> finder.findDuplicates(null));
    }

    @ParameterizedTest
    @MethodSource("provideDuplicateFinders")
    @DisplayName("Test null values should be preserved")
    void testDataWithNullValues(DuplicateFinder<List<String>> finder) {
        List<String> input = Arrays.asList("1", null, "3", null, "3", "1", "2");
        List<String> expected = Arrays.asList("1", null, "3");
        assertEquals(expected, finder.findDuplicates(input));
    }
}
