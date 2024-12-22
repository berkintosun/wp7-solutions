package com.berkintosun.duplicate.api;

public interface DuplicateFinder<T> {
    public T findDuplicates(T list);
}
