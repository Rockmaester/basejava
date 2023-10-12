package com.urise.webapp.storage;

import static org.junit.Assert.*;

public class PathStorageTest extends AbstractStorageTest{
    public PathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getName()));
    }
}