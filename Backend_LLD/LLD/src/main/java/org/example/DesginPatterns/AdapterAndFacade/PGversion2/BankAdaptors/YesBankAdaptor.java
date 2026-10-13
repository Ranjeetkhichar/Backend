package org.example.DesginPatterns.AdapterAndFacade.PGversion2.BankAdaptors;

import org.example.DesginPatterns.AdapterAndFacade.PGversion1.YesBankAPI;
import org.example.DesginPatterns.AdapterAndFacade.PGversion2.BankAPIInterface;

public class YesBankAdaptor implements BankAPIInterface {
    YesBankAPI yb = new YesBankAPI();
    @Override
    public int getBalanceInfo() {
        return yb.getBalance();
    }

    @Override
    public boolean sendMoney(int amount, String fromUPI, String toUPI) {
        return yb.transferAmount(fromUPI, toUPI, amount);
    }
}
