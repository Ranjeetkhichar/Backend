package org.example.DesginPatterns.Factory.MultiTaskDocumentFactory;

import org.example.DesginPatterns.Factory.MultiTaskDocumentFactory.parser.DocumentParser;
import org.example.DesginPatterns.Factory.MultiTaskDocumentFactory.parser.TextDocumentParser;
import org.example.DesginPatterns.Factory.MultiTaskDocumentFactory.printer.DocumentPrinter;
import org.example.DesginPatterns.Factory.MultiTaskDocumentFactory.printer.TextDocumentPrinter;
import org.example.DesginPatterns.Factory.MultiTaskDocumentFactory.processor.DocumentProcessor;
import org.example.DesginPatterns.Factory.MultiTaskDocumentFactory.processor.TextDocumentProcessor;

public class TextDocumentFactory extends DocumentFactory {

    @Override
    public DocumentType supportsType() {
        return DocumentType.TEXT;
    }

    @Override
    public DocumentParser createDocumentParser(String documentPath) {
        return new TextDocumentParser(documentPath);
    }

    @Override
    public DocumentPrinter createDocumentPrinter(DocumentProcessor documentProcessor) {
        return new TextDocumentPrinter(documentProcessor);
    }

    @Override
    public DocumentProcessor createDocumentProcessor(String documentName) {
        return new TextDocumentProcessor(documentName);
    }
}