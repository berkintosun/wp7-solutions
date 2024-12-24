package com.berkintosun.spreadsheet.export.string;

import com.berkintosun.spreadsheet.api.Spreadsheet;
import com.berkintosun.spreadsheet.export.api.ExportType;
import com.berkintosun.spreadsheet.export.api.SpreadsheetExporter;

/**
 * Abstract class for exporting a spreadsheet as a delimited string.
 * Each cell value is appended with a specified delimiter during export.
 */
abstract class StringSpreadsheetExporter implements SpreadsheetExporter<String> {
    String delimiter;
    Spreadsheet spreadsheet;
    ExportType exportType;

    StringSpreadsheetExporter(Spreadsheet spreadsheet, String delimiter, ExportType exportType) {
        this.spreadsheet = spreadsheet;
        this.delimiter = delimiter;
        this.exportType = exportType;
    }

    @Override
    public ExportType supports() {
        return exportType;
    }

    /**
     * Exports the spreadsheet as a delimited string.
     * Each cell value is followed by the specified delimiter.
     * Please note that, the method is not handling the usage of delimiter character inside the cell values.
     * Please note that, every cell value follows by the delimiter therefore if you split it with delimiter,
     * there will be an extra empty result at the end.
     * @return the exported spreadsheet as a delimited string
     */
    @Override
    public String export() {
        StringBuilder stringBuilder = new StringBuilder(
                String.format("%d,%d#", spreadsheet.getRowLimit(), spreadsheet.getColLimit())
        );
        for (int row = 0; row < spreadsheet.getRowLimit(); row++) {
            for (int col = 0; col < spreadsheet.getColLimit(); col++) {
                if (!spreadsheet.get(row, col).isEmpty()) {
                    stringBuilder.append(spreadsheet.get(row, col));
                }
                stringBuilder.append(delimiter);
            }
        }
        return stringBuilder.toString();
    }
}
