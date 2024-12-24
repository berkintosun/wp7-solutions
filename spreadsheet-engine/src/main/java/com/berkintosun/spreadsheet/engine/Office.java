package com.berkintosun.spreadsheet.engine;

public class Office {

    public static SpreadsheetImpl newSpreadsheet(int row, int col) {
        return new SpreadsheetImpl(row, col);
    }
}
