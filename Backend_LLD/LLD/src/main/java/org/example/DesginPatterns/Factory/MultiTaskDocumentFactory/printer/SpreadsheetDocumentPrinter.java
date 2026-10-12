package org.example.DesginPatterns.Factory.MultiTaskDocumentFactory.printer;


import org.example.DesginPatterns.Factory.MultiTaskDocumentFactory.DocumentType;
import org.example.DesginPatterns.Factory.MultiTaskDocumentFactory.processor.DocumentProcessor;

public class SpreadsheetDocumentPrinter extends DocumentPrinter {

    public SpreadsheetDocumentPrinter(DocumentProcessor processor) {

        super(processor);
    }

    public void printDocument() {
        // Print spreadsheet document
    }

    @Override
    public DocumentType supportsType() {

        return DocumentType.SPREAD_SHEET;
    }
}