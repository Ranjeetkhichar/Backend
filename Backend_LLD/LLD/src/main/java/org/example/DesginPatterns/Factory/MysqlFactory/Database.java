package org.example.DesginPatterns.Factory.MysqlFactory;


public interface Database {
    Query createQuery(String queryParams);
}
