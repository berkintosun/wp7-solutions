package com.berkintosun.spreadsheet.export.api;

public interface SpreadsheetExporter<T> {

    /**
     * Exports the spreadsheet data in a format represented by the generic type {@code T}.
     *
     * @return the exported data in the type {@code T}, as defined by the implementation.
     */
    T export();

    /**
     * Returns the export type that is supported by this exporter.
     *
     * @return the supported {@link ExportType}
     */
    ExportType supports();
}
