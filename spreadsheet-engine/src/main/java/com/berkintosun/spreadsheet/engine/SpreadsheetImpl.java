package com.berkintosun.spreadsheet.engine;

import com.berkintosun.spreadsheet.api.Spreadsheet;
import com.berkintosun.spreadsheet.api.ValueType;
import com.berkintosun.spreadsheet.engine.processor.ValueProcessor;

public class SpreadsheetImpl implements Spreadsheet {
    private final int rowLimit;
    private final int colLimit;

    private final String[][] cells;

    private final ValueProcessor valueProcessor;

    SpreadsheetImpl(int row, int col, boolean initializeEmpty) {
        rowLimit = row;
        colLimit = col;
        cells = new String[rowLimit][colLimit];
        valueProcessor = new ValueProcessor(this);

        if (initializeEmpty) {
            for (int i = 0; i < rowLimit; i++) {
                for (int j = 0; j < colLimit; j++) {
                    cells[i][j] = "";
                }
            }
        }
    }

    @Override
    public String get(int row, int col) {
        isOutbound(row, col);
        return cells[row][col] != null ? cells[row][col] : "";
    }

    @Override
    public void put(int row, int col, String val) {
        isOutbound(row, col);
        cells[row][col] = val;
    }

    @Override
    public ValueType getValueType(int row, int col) {
        return valueProcessor.getValueType(get(row, col));
    }

    private void isOutbound(int row, int col) {
        if (row < 0 || row >= rowLimit || col < 0 || col >= colLimit) {
            throw new IndexOutOfBoundsException(
                    String.format("Provided indexes (%s,%s) should be in range of %s,%s",
                            row, col, rowLimit, colLimit)
            );
        }
    }
}
