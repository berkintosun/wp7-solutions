package com.berkintosun.spreadsheet.engine;

import com.berkintosun.spreadsheet.api.Spreadsheet;

public class SpreadsheetImpl implements Spreadsheet {
    private final int row;
    private final int col;

    SpreadsheetImpl(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public String get(int row, int col) {
        return "";
    }

    @Override
    public void put(int row, int col, String val) {
    }
}
