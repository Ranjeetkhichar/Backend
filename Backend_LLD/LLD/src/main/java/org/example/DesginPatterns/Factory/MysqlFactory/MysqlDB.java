package org.example.DesginPatterns.Factory.MysqlFactory;

public class MysqlDB implements Database {
    @Override
    public Query createQuery(String queryParams) {
        return new SQLQuery();
    }
}
