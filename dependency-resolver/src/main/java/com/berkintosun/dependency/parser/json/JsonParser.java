package com.berkintosun.dependency.parser.json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonParser {
    private final StringBuilder input;
    private int position;

    public JsonParser(StringBuilder input) {
        this.input = input;
        this.position = 0;
    }

    public Map<String, List<String>> parse(){
        return new HashMap<>();
    }
}