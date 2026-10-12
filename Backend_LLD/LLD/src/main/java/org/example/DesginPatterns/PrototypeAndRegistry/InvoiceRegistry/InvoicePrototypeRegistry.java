package org.example.DesginPatterns.PrototypeAndRegistry.InvoiceRegistry;

public interface InvoicePrototypeRegistry {
    void addPrototype(Invoice user);

    Invoice getPrototype(InvoiceType type);

    Invoice clone(InvoiceType type);
}
