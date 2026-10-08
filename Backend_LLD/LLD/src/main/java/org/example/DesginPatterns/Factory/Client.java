package org.example.DesginPatterns.Factory;

public class Client {
    public static void main(String[] args) {
        Database db = new MongoDB();

        Query q = db.createQuery("relationalDB");// select * from table where db='relationalDb';

        q.execute();

    }
}
