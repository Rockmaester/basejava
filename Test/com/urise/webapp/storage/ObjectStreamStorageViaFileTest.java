package com.urise.webapp.storage;

import com.urise.webapp.storage.serializationStrategy.ObjectStreamStorageViaFile;

public class ObjectStreamStorageViaFileTest extends AbstractStorageTest{
    public ObjectStreamStorageViaFileTest() {
        super(new ObjectStreamStorageViaFile(STORAGE_DIR));
    }
}