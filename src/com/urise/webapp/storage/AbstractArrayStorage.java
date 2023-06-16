package com.urise.webapp.storage;

import com.urise.webapp.exception.LimitExceedException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage{

    protected int size;
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];

    @Override
    protected void clearStorage() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected final void saveInStorage(Resume resume, int index) {
        if(size == storage.length){
            throw new LimitExceedException();
        } else {
            saveResume(resume, index);
            size++;
        }
    }

    protected abstract void saveResume(Resume resume, int index);

    @Override
    protected final void updateResume(Resume resume, int index){
        storage[index] = resume;
        System.out.println("Резюме \"" + resume.getUuid() + "\" обновлено.");
    }

    @Override
    public final void deleteInStorage(String uuid, int index) {
            deleteResume(index);
            storage[size - 1] = null;
            size--;
    }
    protected abstract void deleteResume(int index);

    @Override
    protected final Resume getInStorage(String uuid, int index) {
            return storage[index];
    }

    protected abstract int getIndex(String uuid);

    @Override
    protected final Resume[] getAllInStorage() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public final int size() {
        return size;
    }
}
