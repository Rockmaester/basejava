package com.urise.webapp.storage;

import com.urise.webapp.exception.LimitExceedException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer>{

    protected int size;
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];

    protected abstract void saveResume(Resume resume, int index);

    protected abstract void deleteResume(int index);

    @Override
    protected void clearStorage() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected final void doSave(Resume resume, Integer searchKey) {
        if(size == storage.length){
            throw new LimitExceedException();
        } else {
            saveResume(resume, (int)searchKey);
            size++;
        }
    }

    @Override
    protected final void doUpdate(Resume resume, Integer searchKey){
        storage[(int)searchKey] = resume;
        System.out.println("Резюме \"" + resume.getUuid() + "\" обновлено.");
    }

    @Override
    public final void doDelete(String uuid, Integer searchKey) {
        deleteResume((int)searchKey);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected final Resume doGet(String uuid, Integer searchKey) {
        return storage[(int)searchKey];
    }

    @Override
    protected List<Resume> doGetAllSorted() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    public final int size() {
        return size;
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return (int)searchKey > -1;
    }
}
