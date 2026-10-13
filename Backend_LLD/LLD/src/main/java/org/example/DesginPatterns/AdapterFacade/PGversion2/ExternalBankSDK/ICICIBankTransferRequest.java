package org.example.DesginPatterns.AdapterFacade.PGversion2.ExternalBankSDK;

public class ICICIBankTransferRequest {
    int amount;
    String fromUPI;
    String toUPI;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getFromUPI() {
        return fromUPI;
    }

    public void setFromUPI(String fromUPI) {
        this.fromUPI = fromUPI;
    }

    public String getToUPI() {
        return toUPI;
    }

    public void setToUPI(String toUPI) {
        this.toUPI = toUPI;
    }
}
