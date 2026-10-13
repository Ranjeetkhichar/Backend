package org.example.DesginPatterns.AdapterAndFacade.PGversion2;

import org.example.DesginPatterns.AdapterAndFacade.PGversion2.ExternalBankSDK.ICICIBankAPI;
import org.example.DesginPatterns.AdapterAndFacade.PGversion2.ExternalBankSDK.ICICIBankTransferRequest;
import org.example.DesginPatterns.AdapterAndFacade.PGversion2.ExternalBankSDK.ICICIBankTransferResponse;

public class FastCard {
    String fastCardUPI = "fastcard@ybl";

    void rechargeCard(ICICIBankAPI bankAPI, String userUPI, int amount){
        ICICIBankTransferRequest request = new ICICIBankTransferRequest();
        request.setAmount(amount);
        request.setFromUPI(fastCardUPI);
        request.setToUPI(userUPI);
        ICICIBankTransferResponse iciciBankTransferResponse = bankAPI.makeTransfer(request);
    }
}
