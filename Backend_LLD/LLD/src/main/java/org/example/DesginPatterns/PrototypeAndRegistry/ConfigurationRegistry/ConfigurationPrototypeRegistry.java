package org.example.DesginPatterns.PrototypeAndRegistry.ConfigurationRegistry;

public interface ConfigurationPrototypeRegistry {
    void addPrototype(Configuration user);

    Configuration getPrototype(ConfigurationType type);

    Configuration clone(ConfigurationType type);
}
