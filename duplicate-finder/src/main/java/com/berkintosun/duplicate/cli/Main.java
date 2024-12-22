package com.berkintosun.duplicate.cli;

import com.berkintosun.duplicate.api.DuplicateFinder;
import com.berkintosun.duplicate.list.impl.DuplicateFinderImpl;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DuplicateFinder<List<String>> duplicateFinder = new DuplicateFinderImpl<>();
        List<String> input = Arrays.stream(args).toList();
        System.out.println(duplicateFinder.findDuplicates(input));
    }
}