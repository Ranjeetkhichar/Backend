package org.example.DesginPatterns.Factory.MysqlFactory;

public class MongoDB implements Database {
    @Override
    public Query createQuery(String queryParams) {
        return new NoSQLQuery();
    }
}
