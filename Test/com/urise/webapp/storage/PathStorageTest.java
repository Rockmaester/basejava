package com.urise.webapp.storage;


import com.urise.webapp.storage.serializationStrategy.StrategyOOS;

public class PathStorageTest extends AbstractStorageTest{
    public PathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getName(), new StrategyOOS()));
    }
}