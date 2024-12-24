package com.berkintosun.spreadsheet.api;

/**
 * The {@code Spreadsheet} interface defines the basic operations for a spreadsheet.
 * It provides methods for retrieving and storing values, determining the value type,
 * and querying the dimensions of the spreadsheet (row and column limits).
 */
public interface Spreadsheet {

    /**
     * Retrieves the value at the specified row and column in the spreadsheet.
     *
     * @param row the row index (0-based)
     * @param col the column index (0-based)
     * @return the value at the specified row and column, or an empty string if no value is set
     * @throws IndexOutOfBoundsException if the row or column is out of bounds
     */
    String get(int row, int col);

    /**
     * Stores a value at the specified row and column in the spreadsheet.
     *
     * @param row the row index (0-based)
     * @param col the column index (0-based)
     * @param val the value to store in the specified cell
     * @throws IndexOutOfBoundsException if the row or column is out of bounds
     */
    void put(int row, int col, String val);

    /**
     * Retrieves the type of the value stored in the specified cell.
     * The value type can be one of {@link ValueType} such as STRING, INTEGER, or FORMULA.
     *
     * @param row the row index (0-based)
     * @param col the column index (0-based)
     * @return the {@link ValueType} of the value in the specified cell
     * @throws IndexOutOfBoundsException if the row or column is out of bounds
     */
    ValueType getValueType(int row, int col);

    int getRowLimit();

    int getColLimit();
}
