package com.berkintosun.spreadsheet.engine.processor;

import com.berkintosun.spreadsheet.api.Spreadsheet;
import com.berkintosun.spreadsheet.api.ValueType;

import java.util.Map;
import java.util.function.Function;

/**
 * The {@code ValueProcessor} class provides functionality for processing and classifying
 * cell values in a spreadsheet. It supports identifying value types ( integer, string, formula)
 * and allows preprocessing of values using predefined transformations with condition checks.
 */
public class ValueProcessor {
    Spreadsheet spreadsheet;

    Map<Character, ValueType> specialCharacters = Map.of(
            '=', ValueType.FORMULA
    );

    Map<Function<String, Boolean>, Function<String, String>> preProcessMap = Map.of(
            (s -> getValueType(s.trim()) == ValueType.INTEGER), (String::trim)
    );

    public ValueProcessor(Spreadsheet spreadsheet) {
        this.spreadsheet = spreadsheet;
    }

    /**
     * Determines the {@link ValueType} of a given string value.
     * The value type is identified based on the content of the string.
     *
     * @param value the value to determine the type for
     * @return the {@link ValueType} of the value
     * @throws IllegalArgumentException if the value is null
     */
    public ValueType getValueType(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Expected String value in ValueProcessor but received null");
        }

        if (value.isEmpty()) {
            return ValueType.STRING;
        }

        if (specialCharacters.containsKey(value.charAt(0))) {
            return specialCharacters.get(value.charAt(0));
        }

        boolean isInteger = true;

        for (int i = 0; i < value.length(); i++) {
            isInteger = isInteger && Character.isDigit(value.charAt(i));
        }

        return isInteger ? ValueType.INTEGER : ValueType.STRING;
    }

    /**
     * Preprocesses a given value based on a set of predefined transformations.
     * {@link #preProcessMap} contains the key value pair functions and key value contains the condition function
     * and the value function is transform function.
     * The method applies transformations defined in the {@link #preProcessMap} if their conditions are met.
     *
     * @param value the value to preprocess
     * @return the preprocessed value, possibly modified based on the transformations
     */
    public String preProcess(String value) {
        for (Map.Entry<Function<String, Boolean>, Function<String, String>> entry : preProcessMap.entrySet()) {
            if (entry.getKey().apply(value)) {
                value = entry.getValue().apply(value);
            }
        }
        return value;
    }
}
