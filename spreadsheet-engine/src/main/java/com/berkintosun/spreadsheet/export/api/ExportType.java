package com.berkintosun.spreadsheet.export.api;

public enum ExportType {
    DASH("-"),
    STAR("*");

    private final String symbol;

    ExportType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
