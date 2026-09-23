package org.example.DesginPatterns.Singleton;

public class DBConnection {
    String url;
    String passwd;

    private static DBConnection instance = null;

    private DBConnection(String url, String passwd) {
        this.url = url;
        this.passwd = passwd;
    }

    private DBConnection() {}

    //Can create many DBConnection instances...
    public static DBConnection createInstance() {
        if(instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }


}
