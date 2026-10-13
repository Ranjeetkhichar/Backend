package org.example.DesginPatterns.AdapterFacade.PGversion2;


import org.example.DesginPatterns.AdapterFacade.PGversion2.BankAdaptors.ICICIBankAdaptor;
import org.example.DesginPatterns.AdapterFacade.PGversion2.BankAdaptors.YesBankAdaptor;


public class GetBankAPIFactory {
    static BankAPIInterface getBankAPI(String bank){
        if(bank == "yesbank") {
            return new YesBankAdaptor();
        }
        else if (bank == "icici") {
            return new ICICIBankAdaptor();
        }
        else {
            throw new RuntimeException("Invalid bank selected");
        }
    }
}
