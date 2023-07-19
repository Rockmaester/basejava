package com.urise.webapp.storage;

import com.urise.webapp.exception.LimitExceedException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.UUID;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest{

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = LimitExceedException.class)
    public void storageOverFlow(){
        storage.clear();
        try {
            for(int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++){
                String name = "Mr. Ivan Ivanov № " + (i + 1);
                storage.save(new Resume(UUID.randomUUID().toString(), name));
            }
        } catch (LimitExceedException e) {
            Assert.fail("Переполнение произошло раньше времени");
        }
        storage.save(new Resume(UUID.randomUUID().toString(), "Petr Petrov"));
    }

    @Test
    public void getAll() {
        Resume[] testArray = new Resume[]{RESUME_1, RESUME_2, RESUME_3};
//        Assert.assertArrayEquals(testArray, (Resume[])storage.getAll());
        Assert.assertEquals(Arrays.asList(testArray), storage.getAll());
    }

}