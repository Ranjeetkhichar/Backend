package org.example.DesginPatterns.PrototypeAndRegistry.UserRegistry;

public interface UserPrototypeRegistry<T> {
    void addPrototype(User user);

    User getPrototype(UserType type);

    User clone(UserType type);
}
