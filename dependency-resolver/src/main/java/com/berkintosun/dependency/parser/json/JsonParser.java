package com.berkintosun.dependency.parser.json;

import com.berkintosun.dependency.api.exception.JsonParseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code JsonParser} class is designed to parse JSON strings where each key represents
 * a package, and the corresponding value is an array of strings representing the dependencies
 * of that package.
 * <p>
 * The parser supports JSON objects with string arrays as values and does not support
 * JSON arrays or other JSON types at the top level.
 * </p>
 */
public class JsonParser {
    private final StringBuilder input;
    private int position;

    public JsonParser(StringBuilder input) {
        this.input = input;
        this.position = 0;
    }

    /**
     * Parses the JSON input and returns a {@link Map} where the keys are package names
     * and the values are lists of dependencies for each package.
     *
     * @return A {@link Map} of package dependencies.
     * @throws JsonParseException if the JSON input is invalid or contains unsupported structures.
     */
    public Map<String, List<String>> parse() throws JsonParseException {
        skipWhitespace();
        if (!consume('{')) {
            throw new JsonParseException("Expected '{' at the start of JSON");
        }
        boolean isNewElement = peek() != '}';
        Map<String, List<String>> result = new HashMap<>();
        while (position < input.length() && isNewElement) {
            skipWhitespace();
            String key = parseString();
            skipWhitespace();
            if (!consume(':')) {
                throw new JsonParseException("Expected ':' after key");
            }
            skipWhitespace();
            List<String> value = parseArray();
            result.put(key, value);
            skipWhitespace();
            isNewElement = consume(',');
        }
        if (!consume('}')) {
            throw new JsonParseException("Expected '}' at the end of JSON");
        }
        return result;
    }

    private List<String> parseArray() throws JsonParseException {
        if (!consume('[')) {
            throw new JsonParseException("Expected '[' at start of array");
        }

        List<String> array = new ArrayList<>();
        while (position < input.length()) {
            skipWhitespace();
            if (peek() == ']') {
                position++;
                break;
            }

            if (!array.isEmpty()) {
                if (!consume(',')) {
                    throw new JsonParseException("Expected ',' between array elements");
                }
                skipWhitespace();
            }

            if (peek() == ']') {
                position++;
                break;
            }

            array.add(parseString());
        }
        return array;
    }

    private String parseString() throws JsonParseException {
        if (!consume('"')) {
            throw new JsonParseException("Expected '\"' at start of string");
        }

        StringBuilder sb = new StringBuilder();
        while (position < input.length()) {
            char c = input.charAt(position++);
            if (c == '"') {
                break;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    private void skipWhitespace() {
        while (position < input.length() && Character.isWhitespace(input.charAt(position))) {
            position++;
        }
    }

    private boolean consume(char expected) {
        if (position < input.length() && input.charAt(position) == expected) {
            position++;
            return true;
        }
        return false;
    }

    private char peek() {
        return position < input.length() ? input.charAt(position) : '\0';
    }
}