package com.berkintosun.spreadsheet.export.string;

import com.berkintosun.spreadsheet.api.Spreadsheet;
import com.berkintosun.spreadsheet.export.api.ExportType;

/**
 * An implementation of StringSpreadsheetExporter that uses an asterisk ("*")
 * as the delimiter for exporting spreadsheet cell values.
 */
public class StarSpreadsheetExporter extends StringSpreadsheetExporter {

    public StarSpreadsheetExporter(Spreadsheet spreadsheet) {
        super(spreadsheet, ExportType.STAR.getSymbol(), ExportType.STAR);
    }
}
