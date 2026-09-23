package org.example.DesginPatterns.Singleton;

public class DBConnectionThreadSafe {
    String url;
    String passwd;

    private static DBConnectionThreadSafe instance = null;

    private DBConnectionThreadSafe() {}

    //Can create many DBConnection instances...
    public static DBConnectionThreadSafe createInstance() {
        if(instance == null) {
            synchronized (DBConnectionThreadSafe.class) {
                if(instance == null) {
                    instance = new DBConnectionThreadSafe();
                }
            }
        }
        return instance;
    }

}
