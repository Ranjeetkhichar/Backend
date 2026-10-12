package org.example.DesginPatterns.PrototypeAndRegistry.UserRegistry;

import java.util.HashMap;
import java.util.Map;

public class UserPrototypeRegistryImpl implements UserPrototypeRegistry<User>{

    private Map<UserType, User> registry = new HashMap<>();

    @Override
    public void addPrototype(User user) {
        registry.put(user.getType(), user);
    }

    @Override
    public User getPrototype(UserType type) {
        return registry.get(type);
    }

    @Override
    public User clone(UserType type) {
        return new User(registry.get(type));
    }
}
