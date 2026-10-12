package org.example.DesginPatterns.Factory.MultiTaskDocumentFactory.parser;


import org.example.DesginPatterns.Factory.MultiTaskDocumentFactory.DocumentType;

public class SpreadsheetDocumentParser extends DocumentParser {

    public SpreadsheetDocumentParser(String path) {
        super(path);
    }

    public void parseDocument() {
        // Parse spreadsheet document
    }

    @Override
    public DocumentType supportsType() {

        return DocumentType.SPREAD_SHEET;
    }
}