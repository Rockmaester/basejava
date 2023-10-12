package com.urise.webapp.storage;

import com.urise.webapp.storage.serializationStrategy.StrategyOOS;

public class FileStorageTest extends AbstractStorageTest {
    public FileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new StrategyOOS()));
    }
}