package org.example.DesginPatterns.Factory;


public interface Database {
    Query createQuery(String queryParams);
}
