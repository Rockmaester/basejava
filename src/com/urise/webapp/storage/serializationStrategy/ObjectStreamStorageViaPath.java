package com.urise.webapp.storage.serializationStrategy;

import com.urise.webapp.storage.PathStorage;

public class ObjectStreamStorageViaPath extends PathStorage {
    public ObjectStreamStorageViaPath(String directory) {
        super(directory, new StrategyOOS());
    }
}
