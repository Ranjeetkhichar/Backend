package org.example.DesginPatterns.AdapterFacade.PGversion1;

public class YesBankAPI {
    public int getBalance(){
        return 100;
    }

    public boolean transferAmount(String fromUPI, String toUPI, int amount){
        return false;
    }
}
