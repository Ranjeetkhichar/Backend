package org.example.DesginPatterns.AdapterAndFacade.PGversion2.ExternalBankSDK;

public class ICICIBankAPI {
    public int fetchBalance(){
        return 0;
    }

    public ICICIBankTransferResponse makeTransfer(ICICIBankTransferRequest request){
        if(fetchBalance() < request.getAmount()){
            throw new RuntimeException("insufficient balance");
        }
        return new ICICIBankTransferResponse();
    }
}
