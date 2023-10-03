package com.urise.webapp.storage;

public class ObjectStreamStorageViaPath extends AbstractPathStorage {
    public ObjectStreamStorageViaPath(String directory) {
        super(directory);
        this.serializationStrategy = new StrategyOOS();
    }
}
