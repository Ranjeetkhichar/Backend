package org.example.DesginPatterns.Factory.MultiTaskDocumentFactory.parser;


import org.example.DesginPatterns.Factory.MultiTaskDocumentFactory.DocumentType;

public abstract class DocumentParser {

    private final String path;

    public DocumentParser(String path) {
        this.path = path;
    }

    public abstract void parseDocument();

    public String getPath() {
        return path;
    }

    public abstract DocumentType supportsType();

}