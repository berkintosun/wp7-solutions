package com.berkintosun.spreadsheet.export.string;

import com.berkintosun.spreadsheet.api.Spreadsheet;
import com.berkintosun.spreadsheet.export.api.ExportType;

/**
 * An implementation of StringSpreadsheetExporter that uses a dash ("-")
 * as the delimiter for exporting spreadsheet cell values.
 */
public class DashSpreadsheetExporter extends StringSpreadsheetExporter {

    public DashSpreadsheetExporter(Spreadsheet spreadsheet) {
        super(spreadsheet, ExportType.DASH.getSymbol(), ExportType.DASH);
    }
}
