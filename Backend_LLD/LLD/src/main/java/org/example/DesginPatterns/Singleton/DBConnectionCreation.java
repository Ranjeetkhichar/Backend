package org.example.DesginPatterns.Singleton;

public class DBConnectionCreation implements Runnable {
    @Override
    public void run() {
//        DBConnectionThreadSafe db = DBConnectionThreadSafe.createInstance();
        DBConnection db2 = DBConnection.createInstance();
//        System.out.println("Creating DB Connection with ThreadSafe " + db);
        System.out.println("Creating DB Connection " + db2);
    }

}
