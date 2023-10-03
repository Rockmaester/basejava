package com.urise.webapp.storage;

import static org.junit.Assert.*;

public class ObjectStreamStorageViaPathTest extends AbstractStorageTest{
    public ObjectStreamStorageViaPathTest() {
        super(new ObjectStreamStorageViaPath(STORAGE_DIR.getAbsolutePath()));
    }
}