package org.example.DesginPatterns.AdapterFacade.PGversion1;

public class Loan {
    final int eligibleAmount = 100000;
    boolean checkLoanEligibility(ICICIBankAPI bankAPI){
        if(bankAPI.fetchBalance() > eligibleAmount){
            return true;
        }
        return false;
    }
}
