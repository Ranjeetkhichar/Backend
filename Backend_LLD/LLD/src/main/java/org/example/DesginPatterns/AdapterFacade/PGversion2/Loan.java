package org.example.DesginPatterns.AdapterFacade.PGversion2;

import org.example.DesginPatterns.AdapterFacade.PGversion2.ExternalBankSDK.ICICIBankAPI;

public class Loan {
    final int eligibleAmount = 100000;
    boolean checkLoanEligibility(ICICIBankAPI bankAPI){
        if(bankAPI.fetchBalance() > eligibleAmount){
            return true;
        }
        return false;
    }
}
