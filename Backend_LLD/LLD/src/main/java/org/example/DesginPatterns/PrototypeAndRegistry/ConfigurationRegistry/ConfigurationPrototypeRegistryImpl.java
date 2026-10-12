package org.example.DesginPatterns.PrototypeAndRegistry.ConfigurationRegistry;

import java.util.HashMap;
import java.util.Map;

public class ConfigurationPrototypeRegistryImpl implements ConfigurationPrototypeRegistry{
    private Map<ConfigurationType, Configuration> configurationsRegistry = new HashMap<ConfigurationType, Configuration>();

    @Override
    public void addPrototype(Configuration user) {
        configurationsRegistry.put(user.getType(), user);
    }

    @Override
    public Configuration getPrototype(ConfigurationType type) {
        return configurationsRegistry.get(type);
    }

    @Override
    public Configuration clone(ConfigurationType type) {
        return new Configuration(configurationsRegistry.get(type));
    }
}
