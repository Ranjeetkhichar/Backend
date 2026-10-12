package org.example.DesginPatterns.Factory.MultiTaskDocumentFactory.printer;


import org.example.DesginPatterns.Factory.MultiTaskDocumentFactory.DocumentType;
import org.example.DesginPatterns.Factory.MultiTaskDocumentFactory.processor.DocumentProcessor;

public abstract class DocumentPrinter {

    private final DocumentProcessor processor;

    public DocumentPrinter(DocumentProcessor processor) {

        this.processor = processor;
    }

    public DocumentProcessor getProcessor() {

        return processor;
    }

    public abstract void printDocument();

    public abstract DocumentType supportsType();

}