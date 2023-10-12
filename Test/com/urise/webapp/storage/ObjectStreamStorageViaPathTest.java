package com.urise.webapp.storage;

import com.urise.webapp.storage.serializationStrategy.ObjectStreamStorageViaPath;

public class ObjectStreamStorageViaPathTest extends AbstractStorageTest{
    public ObjectStreamStorageViaPathTest() {
        super(new ObjectStreamStorageViaPath(STORAGE_DIR.getAbsolutePath()));
    }
}