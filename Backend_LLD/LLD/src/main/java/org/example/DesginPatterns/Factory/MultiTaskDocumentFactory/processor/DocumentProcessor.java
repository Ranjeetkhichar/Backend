package org.example.DesginPatterns.Factory.MultiTaskDocumentFactory.processor;


import org.example.DesginPatterns.Factory.MultiTaskDocumentFactory.DocumentType;

public abstract class DocumentProcessor {
    private String documentName;

    public DocumentProcessor(String documentName) {

        this.documentName = documentName;
    }

    public String getDocumentName() {

        return documentName;
    }

    public abstract void processDocument();

    public abstract DocumentType supportsType();

}