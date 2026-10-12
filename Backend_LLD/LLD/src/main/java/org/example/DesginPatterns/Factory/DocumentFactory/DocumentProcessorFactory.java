package org.example.DesginPatterns.Factory.DocumentFactory;

public class DocumentProcessorFactory {

    public static DocumentProcessor createDocumentProcessor(String documentName, DocumentType documentType) {
        switch (documentType) {
            case TEXT:
                return new TextDocumentProcessor(documentName);
            case PRESENTATION:
                return new PresentationDocumentProcessor(documentName);
            case SPREAD_SHEET:
                return new SpreadsheetDocumentProcessor(documentName);
        }
        return null;
    }


}
