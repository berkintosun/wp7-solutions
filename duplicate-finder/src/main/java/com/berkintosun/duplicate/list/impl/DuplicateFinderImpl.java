package com.berkintosun.duplicate.list.impl;

import com.berkintosun.duplicate.api.DuplicateFinder;

import java.util.*;

/**
 * A generic implementation of the {@link DuplicateFinder} interface for detecting duplicate elements in a list.
 *
 * <p>This class provides a method to find all duplicate elements in a given list of T. The duplicates are
 * returned in the order they first appear in the input list. It uses a {@link Map} to count occurrences of each
 * element and a {@link Set} to ensure each duplicate is added only once to the result.</p>
 *
 * @param <T> the type of elements in the list
 */
public class DuplicateFinderImpl<T> implements DuplicateFinder<List<T>> {
    /**
     * Finds and returns a list of duplicate elements from the input list, maintaining the order in which they
     * first appeared in the original list.
     *
     * <p>If the input list is {@code null}, an {@link IllegalArgumentException} is thrown.</p>
     *
     * <p>The returned list contains each duplicate element exactly once, even if it appears multiple times
     * in the input list.</p>
     * <p>Example usage:</p>
     *  <pre>
     *   DuplicateFinder<List<String>> duplicateFinder = new DuplicateFinderImpl<>();
     *   List<String> input = Arrays.asList("b", "a", "c", "c", "e", "a", "c", "d", "c", "d");
     *   List<String> duplicates = duplicateFinder.findDuplicates(input);
     *   // duplicates: ["a", "c", "d"]
     *  </pre>
     *
     * @param inputList the list of elements to search for duplicates
     * @return a list of duplicate elements in the order they first appeared in the input list
     * @throws IllegalArgumentException if {@code inputList} is {@code null}
     */
    @Override
    public List<T> findDuplicates(List<T> inputList) {
        if (inputList == null) {
            throw new IllegalArgumentException();
        }

        Map<T, Integer> countMap = new HashMap<>();
        Set<T> added = new HashSet<>();
        List<T> result = new ArrayList<>();

        for (T item : inputList) {
            countMap.merge(item, 1, Integer::sum);
        }

        for (T item : inputList) {
            if (countMap.get(item) > 1 && !added.contains(item)) {
                result.add(item);
                added.add(item);
            }
        }

        return result;
    }
}