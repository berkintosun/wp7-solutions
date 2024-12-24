package com.berkintosun.spreadsheet.engine;

/**
 * The {@code Office} class provides a static method to create a new instance of a {@link SpreadsheetImpl}.
 * It allows configuration of the spreadsheet initialization behavior via the system property.
 */
public class Office {
    /**
     * The name of the system property that determines whether a spreadsheet is initialized with empty strings.
     * If the property is set to "true", the spreadsheet cells will be initialized to empty strings.
     * Default value is "false". Disabling it will improve efficiency and current implementation of
     * {@link SpreadsheetImpl} handles the null cases as it is filled with an empty string.
     */
    public final static String EMPTY_INITIALIZER_FLAG = "INITIALIZE_EMPTY";

    /**
     * Creates a new {@link SpreadsheetImpl} instance with the specified number of rows and columns.
     * The initialization behavior of the spreadsheet is controlled by the system property
     * {@link #EMPTY_INITIALIZER_FLAG}. If the property is set to "true", all cells in the spreadsheet
     * will be initialized with empty strings. If the property is not set or invalid, initialization defaults to "false".
     *
     * @param row the number of rows in the spreadsheet
     * @param col the number of columns in the spreadsheet
     * @return a new instance of {@link SpreadsheetImpl} with the specified dimensions
     * @throws IllegalArgumentException if the row or column values are invalid (negative or zero)
     */
    public static SpreadsheetImpl newSpreadsheet(int row, int col) {
        boolean initializeEmpty = false;
        try {
            initializeEmpty = Boolean.parseBoolean(System.getProperty(EMPTY_INITIALIZER_FLAG));
        } catch (Exception e) {
            System.out.printf("Could not get system property '%s'. Defaulting to false.%n", EMPTY_INITIALIZER_FLAG);
        }
        return new SpreadsheetImpl(row, col, initializeEmpty);
    }
}
