package org.example.DesginPatterns.AdapterAndFacade.PGversion2;

public interface BankAPIInterface {
    int getBalanceInfo();
    boolean sendMoney(int amount, String fromUPI, String toUPI);
}
