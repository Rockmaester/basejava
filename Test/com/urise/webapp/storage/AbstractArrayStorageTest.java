package com.urise.webapp.storage;

import com.urise.webapp.exception.LimitExceedException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest{


    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = LimitExceedException.class)
    public void storageOverFlow(){
        storage.clear();
        try {
            for(int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++){
                storage.save(new Resume(UUID.randomUUID().toString()));
            }
        } catch (LimitExceedException e) {
            Assert.fail("Переполнение произошло раньше времени");
        }
        storage.save(new Resume(UUID.randomUUID().toString()));
    }

    @Test
    public void getAll() {
        Resume[] testArray = new Resume[]{RESUME_1, RESUME_2, RESUME_3};
        Assert.assertArrayEquals(testArray, (Resume[])storage.getAll());
    }

}