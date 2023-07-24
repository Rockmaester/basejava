package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStrorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStorageTest {
    protected final Storage storage;
    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";
    protected static final String UUID_4 = "uuid4";
    protected static final String UUID_NOT_EXIST = "dummy";
    protected final Resume RESUME_1 = new Resume(UUID_1, "Ivan Ivanov");
    protected final Resume RESUME_2 = new Resume(UUID_2, "Petr Petrov");
    protected final Resume RESUME_3 = new Resume(UUID_3, "Alexander Aleksandrov");
    protected final Resume RESUME_4 = new Resume(UUID_4, "Ivan Ivanov");

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp(){
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void getAllSorted(){
        storage.save(RESUME_4);

        List<Resume> testList = new ArrayList<>();
        testList.add(RESUME_3);
        testList.add(RESUME_1);
        testList.add(RESUME_4);
        testList.add(RESUME_2);

        Assert.assertEquals(testList, storage.getAllSorted());
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertGet(RESUME_4);
        assertSize(4);
    }

    @Test(expected = ExistStrorageException.class)
    public void saveExisting() {
        storage.save(RESUME_1);
    }

    @Test
    public void update() {
        Resume upd = new Resume(UUID_1, "Sidor Sidorov");
        storage.update(upd);
        Assert.assertEquals(RESUME_1, upd);
        Assert.assertNotSame(RESUME_1, upd);
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
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    public void assertGet(Resume resume){
        Assert.assertEquals(resume, storage.get(resume.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExisting() {
        storage.get(UUID_NOT_EXIST);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    private void assertSize(int size){
        Assert.assertEquals(size, storage.size());
    }

}