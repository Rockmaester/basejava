package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class AbstractMapStorageTest extends AbstractStorageTest{

    public AbstractMapStorageTest(Storage storage) {
        super(storage);
    }

    @Override
    public void getAll() {
        Map<String, Resume> testMap = new HashMap<>();
        testMap.put(UUID_1, RESUME_1);
        testMap.put(UUID_2, RESUME_2);
        testMap.put(UUID_3, RESUME_3);
        Assert.assertEquals(testMap, storage.getAll());
    }
}