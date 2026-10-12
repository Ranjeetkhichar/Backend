package org.example.DesginPatterns.Factory.MultiTaskDocumentFactory.parser;


import org.example.DesginPatterns.Factory.MultiTaskDocumentFactory.DocumentType;

public class TextDocumentParser extends DocumentParser {

    public TextDocumentParser(String path) {
        super(path);
    }

    public void parseDocument() {
        // Parse text document
    }

    @Override
    public DocumentType supportsType() {
        return DocumentType.TEXT;
    }
}