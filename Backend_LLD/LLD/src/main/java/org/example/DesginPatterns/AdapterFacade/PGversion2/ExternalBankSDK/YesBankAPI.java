package org.example.DesginPatterns.AdapterFacade.PGversion2.ExternalBankSDK;

public class YesBankAPI {
    int getBalance(){
        return 100;
    }

    boolean transferAmount(String fromUPI, String toUPI, int amount){
        return false;
    }
}
