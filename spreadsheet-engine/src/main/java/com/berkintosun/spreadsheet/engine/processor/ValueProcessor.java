package com.berkintosun.spreadsheet.engine.processor;

import com.berkintosun.spreadsheet.api.Spreadsheet;
import com.berkintosun.spreadsheet.api.ValueType;

import java.util.Map;
import java.util.function.Function;

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

    public String preProcess(String value) {
        for (Map.Entry<Function<String, Boolean>, Function<String, String>> entry : preProcessMap.entrySet()) {
            if (entry.getKey().apply(value)) {
                value = entry.getValue().apply(value);
            }
        }
        return value;
    }
}
