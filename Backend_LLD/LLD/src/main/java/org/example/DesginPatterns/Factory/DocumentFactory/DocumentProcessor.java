package org.example.DesginPatterns.Factory.DocumentFactory;

public abstract class DocumentProcessor {
    private String documentName;

    public DocumentProcessor(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentName() {
        return documentName;
    }

    public abstract DocumentType supportsType();

    public abstract void processDocument();


}
