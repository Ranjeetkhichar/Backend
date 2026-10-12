package org.example.DesginPatterns.Factory.MultiTaskDocumentFactory.printer;


import org.example.DesginPatterns.Factory.MultiTaskDocumentFactory.DocumentType;
import org.example.DesginPatterns.Factory.MultiTaskDocumentFactory.processor.DocumentProcessor;

public class TextDocumentPrinter extends DocumentPrinter {

    public TextDocumentPrinter(DocumentProcessor processor) {
        super(processor);
    }

    public void printDocument() {
        // Print text document
    }

    @Override
    public DocumentType supportsType() {
        return DocumentType.TEXT;
    }
}