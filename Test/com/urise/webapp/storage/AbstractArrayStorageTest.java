package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStrorageException;
import com.urise.webapp.exception.LimitExceedException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.fail;

public abstract class AbstractArrayStorageTest{

    protected final     Storage storage;
    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";
    protected static final String UUID_4 = "uuid4";
    protected static final String UUID_NOT_EXIST = "dummy";
    protected final Resume R1 = new Resume(UUID_1);
    protected final Resume R2 = new Resume(UUID_2);

    protected final Resume R3 = new Resume(UUID_3);
    protected final Resume R4 = new Resume(UUID_4);


    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp(){
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void save() {
        storage.save(R4);
        assertGet(R4);
        assertSize(4);
    }

    @Test(expected = ExistStrorageException.class)
    public void saveExisting() {
        storage.save(R1);
    }

    @Test
    public void update() {
        Resume upd = new Resume(UUID_1);
        storage.update(upd);
        Assert.assertEquals(R1, upd);
        Assert.assertNotSame(R1, upd);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExisting(){
        Resume upd = storage.get(UUID_NOT_EXIST);
        storage.update(upd);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }


    @Test(expected = NotExistStorageException.class)
    public void deleteNotExisting(){
        storage.delete(UUID_NOT_EXIST);
    }

    @Test
    public void get() {
        assertGet(R1);
        assertGet(R2);
        assertGet(R3);
    }

    public void assertGet(Resume resume){
        Assert.assertEquals(resume, storage.get(resume.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExisting() {
        storage.get(UUID_NOT_EXIST);
    }

    @Test
    public void getAll() {
        Resume[] testArray = new Resume[]{R1, R2, R3};
        Assert.assertArrayEquals(testArray, storage.getAll());
    }

    @Test
    public void size() {
        assertSize(3);
    }

    public void assertSize(int size){
        Assert.assertEquals(size, storage.size());
    }

    @Test(expected = LimitExceedException.class)
    public void storageOverFlow(){
        try {
            for(int i = 3; i < storage.getStorage().length; i++){
                storage.save(new Resume(UUID.randomUUID().toString()));
            }
        } catch (LimitExceedException e) {
            fail("Переполнение произошло раньше времени");
        }
        storage.save(new Resume(UUID.randomUUID().toString()));
    }
}