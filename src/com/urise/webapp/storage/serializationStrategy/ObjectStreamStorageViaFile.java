package com.urise.webapp.storage.serializationStrategy;

import com.urise.webapp.storage.FileStorage;

import java.io.File;

public class ObjectStreamStorageViaFile extends FileStorage {
    public ObjectStreamStorageViaFile(File directory) {
        super(directory);
        this.serializationStrategy = new StrategyOOS();
    }
}
