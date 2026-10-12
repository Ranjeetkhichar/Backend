package org.example.DesginPatterns.Factory.MultiTaskDocumentFactory;


import org.example.DesginPatterns.Factory.MultiTaskDocumentFactory.parser.DocumentParser;
import org.example.DesginPatterns.Factory.MultiTaskDocumentFactory.printer.DocumentPrinter;
import org.example.DesginPatterns.Factory.MultiTaskDocumentFactory.processor.DocumentProcessor;

public abstract class DocumentFactory {
    public abstract DocumentType supportsType();
    public abstract DocumentParser createDocumentParser(String documentPath);
    public abstract DocumentPrinter createDocumentPrinter(DocumentProcessor documentProcessor);
    public abstract DocumentProcessor createDocumentProcessor(String documentName);


}