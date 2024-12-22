package com.berkintosun.duplicate.api;

public interface DuplicateFinder<T> {
    T findDuplicates(T list);
}
