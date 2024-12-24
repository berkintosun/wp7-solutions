package com.berkintosun.spreadsheet.engine.processor;

import com.berkintosun.spreadsheet.api.Spreadsheet;
import com.berkintosun.spreadsheet.api.ValueType;

import java.util.Map;

public class ValueProcessor {
    Spreadsheet spreadsheet;

    Map<Character, ValueType> specialCharacters = Map.of(
            '=', ValueType.FORMULA
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

    public String preProcess(String value){
        String trimmedStr = value.trim();
        
        if(getValueType(trimmedStr) == ValueType.INTEGER){
            return trimmedStr;
        }
        return value;
    }
}
