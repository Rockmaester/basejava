package com.urise.webapp.storage;

import com.urise.webapp.storage.serializationStrategy.ObjectStreamSerializer;

public class ObjectFileStorageTest extends AbstractStorageTest {

    public ObjectFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamSerializer()));
    }
}