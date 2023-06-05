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

    protected Storage storage;
    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";
    protected Resume r1;
    protected Resume r2;
    protected Resume r3;


    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp(){
        storage.clear();
        r1 = new Resume(UUID_1);
        r2 = new Resume(UUID_2);
        r3 = new Resume(UUID_3);
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);

//        storage.save(new Resume(UUID_1));
//        storage.save(new Resume(UUID_2));
//        storage.save(new Resume(UUID_3));
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertArrayEquals(new Resume[0], storage.getAll());
    }

    @Test
    public void save() {
        storage.clear();
        Resume[] testArray = new Resume[]{r1};
        storage.save(r1);
        Assert.assertArrayEquals(testArray, storage.getAll());
    }

    @Test(expected = ExistStrorageException.class)
    public void saveExisting() {
        storage.save(r1);
    }

    @Test
    public void update() {
        Resume upd = new Resume(UUID_1);
        storage.update(upd);
        Assert.assertEquals(r1, upd);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExisting(){
        Resume upd = storage.get("dummy");
        storage.update(upd);
        Assert.assertEquals(r1, upd);
    }

    @Test
    public void delete() {
        storage.delete(UUID_1);
        storage.delete(UUID_2);
        Resume[] testArray = new Resume[]{r3};
        Assert.assertArrayEquals(testArray, storage.getAll());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExisting(){
        storage.delete("dummy");
    }

    @Test
    public void get() {
        Assert.assertEquals(storage.get(UUID_1), r1);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExisting() {
        storage.get("dummy");
    }

    @Test
    public void getAll() {
        Resume[] testArray = new Resume[]{r1, r2, r3};
        Assert.assertArrayEquals(testArray, storage.getAll());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
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