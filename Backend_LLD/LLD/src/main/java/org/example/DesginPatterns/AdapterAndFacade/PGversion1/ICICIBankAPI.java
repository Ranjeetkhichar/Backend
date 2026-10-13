package org.example.DesginPatterns.AdapterAndFacade.PGversion1;

public class ICICIBankAPI {
    int fetchBalance(){
        return 0;
    }

    void makeTransfer(String fromUPI, String toUPI, int amount){
        if(fetchBalance() < amount){
            throw new RuntimeException("insufficient balance");
        }
    }
}
