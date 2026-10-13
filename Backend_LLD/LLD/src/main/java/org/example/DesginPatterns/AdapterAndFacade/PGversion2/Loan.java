package org.example.DesginPatterns.AdapterAndFacade.PGversion2;

import org.example.DesginPatterns.AdapterAndFacade.PGversion2.ExternalBankSDK.ICICIBankAPI;

public class Loan {
    final int eligibleAmount = 100000;
    boolean checkLoanEligibility(ICICIBankAPI bankAPI){
        if(bankAPI.fetchBalance() > eligibleAmount){
            return true;
        }
        return false;
    }
}
