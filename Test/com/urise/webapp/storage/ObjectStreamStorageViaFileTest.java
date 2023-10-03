package com.urise.webapp.storage;

public class ObjectStreamStorageViaFileTest extends AbstractStorageTest{
    public ObjectStreamStorageViaFileTest() {
        super(new ObjectStreamStorageViaFile(STORAGE_DIR));
    }
}