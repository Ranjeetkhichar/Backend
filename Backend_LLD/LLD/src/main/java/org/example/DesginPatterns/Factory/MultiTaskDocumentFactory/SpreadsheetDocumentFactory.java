package org.example.DesginPatterns.Factory.MultiTaskDocumentFactory;

import org.example.DesginPatterns.Factory.MultiTaskDocumentFactory.parser.DocumentParser;
import org.example.DesginPatterns.Factory.MultiTaskDocumentFactory.parser.SpreadsheetDocumentParser;
import org.example.DesginPatterns.Factory.MultiTaskDocumentFactory.printer.DocumentPrinter;
import org.example.DesginPatterns.Factory.MultiTaskDocumentFactory.printer.SpreadsheetDocumentPrinter;
import org.example.DesginPatterns.Factory.MultiTaskDocumentFactory.processor.DocumentProcessor;
import org.example.DesginPatterns.Factory.MultiTaskDocumentFactory.processor.SpreadsheetDocumentProcessor;

public class SpreadsheetDocumentFactory extends DocumentFactory {

    @Override
    public DocumentType supportsType() {
        return DocumentType.SPREAD_SHEET;
    }

    @Override
    public DocumentParser createDocumentParser(String documentPath) {
        return new SpreadsheetDocumentParser(documentPath);
    }

    @Override
    public DocumentPrinter createDocumentPrinter(DocumentProcessor documentProcessor) {
        return new SpreadsheetDocumentPrinter(documentProcessor);
    }

    @Override
    public DocumentProcessor createDocumentProcessor(String documentName) {
        return new SpreadsheetDocumentProcessor(documentName);
    }
}