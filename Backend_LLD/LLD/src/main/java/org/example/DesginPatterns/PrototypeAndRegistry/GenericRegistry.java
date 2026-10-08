package org.example.DesginPatterns.PrototypeAndRegistry;

import java.util.HashMap;

public class GenericRegistry<T> {
    HashMap<String, T> students = new HashMap<>();

    public void register(String key, T obj) {
        students.put(key, obj);
    }
    public T get(String key) {
        return students.get(key);
    }
}
