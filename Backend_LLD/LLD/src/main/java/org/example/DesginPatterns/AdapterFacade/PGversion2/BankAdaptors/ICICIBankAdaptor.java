package org.example.DesginPatterns.AdapterFacade.PGversion2.BankAdaptors;

import org.example.DesginPatterns.AdapterFacade.PGversion2.BankAPIInterface;
import org.example.DesginPatterns.AdapterFacade.PGversion2.ExternalBankSDK.ICICIBankAPI;
import org.example.DesginPatterns.AdapterFacade.PGversion2.ExternalBankSDK.ICICIBankTransferRequest;
import org.example.DesginPatterns.AdapterFacade.PGversion2.ExternalBankSDK.ICICIBankTransferResponse;

public class ICICIBankAdaptor implements BankAPIInterface {

    ICICIBankAPI api = new ICICIBankAPI();

    @Override
    public int getBalanceInfo() {
        return api.fetchBalance();
    }

    @Override
    public boolean sendMoney(int amount, String fromUPI, String toUPI) {
        ICICIBankTransferRequest request = new ICICIBankTransferRequest();
        request.setAmount(amount);
        request.setFromUPI(fromUPI);
        request.setToUPI(toUPI);
        ICICIBankTransferResponse response = api.makeTransfer(request);
        // ICICIBankTranferResponse -> PhoneBankTransferResponse
        return response.status;
    }
}
