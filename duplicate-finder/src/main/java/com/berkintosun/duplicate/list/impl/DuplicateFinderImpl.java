package com.berkintosun.duplicate.list.impl;

import com.berkintosun.duplicate.api.DuplicateFinder;

import java.util.*;

public class DuplicateFinderImpl<T> implements DuplicateFinder<List<T>> {
    public List<T> findDuplicates(List<T> inputList) {
        if (inputList == null) {
            throw new IllegalArgumentException();
        }

        Map<T, Integer> countMap = new HashMap<>();
        Set<T> added = new HashSet<>();
        List<T> result = new ArrayList<>();

        for (T item : inputList) {
            if (countMap.containsKey(item)) {
                countMap.put(item, 1 + countMap.get(item));
            } else {
                countMap.put(item, 1);
            }
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