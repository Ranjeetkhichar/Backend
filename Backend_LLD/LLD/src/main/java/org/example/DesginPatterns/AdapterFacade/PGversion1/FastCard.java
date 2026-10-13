package org.example.DesginPatterns.AdapterFacade.PGversion1;

public class FastCard {
    String fastCardUPI = "fastcard@ybl";

    void rechargeCard(ICICIBankAPI bankAPI, String userUPI, int amount){
        bankAPI.makeTransfer(userUPI, fastCardUPI, amount);
    }
}
