package com.berkintosun.spreadsheet.engine;

public class Office {
    public final static String EMPTY_INITIALIZER_FLAG = "INITIALIZE_EMPTY";

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
