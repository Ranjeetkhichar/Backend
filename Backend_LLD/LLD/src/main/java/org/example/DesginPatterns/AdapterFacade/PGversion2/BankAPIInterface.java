package org.example.DesginPatterns.AdapterFacade.PGversion2;

public interface BankAPIInterface {
    int getBalanceInfo();
    boolean sendMoney(int amount, String fromUPI, String toUPI);
}
