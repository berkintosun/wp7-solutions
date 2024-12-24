package com.berkintosun.spreadsheet.api;

public interface Spreadsheet {

    String get(int row, int col);

    void put(int row, int col, String val);

    ValueType getValueType(int row, int col);
}
