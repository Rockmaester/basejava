package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStrorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public final void clear() {
        clearStorage();
    }

    public final void save(Resume resume) {
        Object searchKey = getNotExistingSearchKey(resume.getUuid());
        doSave(resume, searchKey);
    }

    public final void update(Resume resume) {
        Object searchKey = getExistingSearchKey(resume.getUuid());
        doUpdate(resume, searchKey);
    }

    public final void delete(String uuid) {
        Object searchKey = getExistingSearchKey(uuid);
        doDelete(uuid, searchKey);
    }

    public final Resume get(String uuid) {
        Object searchKey = getExistingSearchKey(uuid);
        return doGet(uuid, searchKey);
    }

    public final Object getAll() {
        return getAllInStorage();
    }

    private Object getExistingSearchKey(String uuid){
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)){
            return searchKey;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    private Object getNotExistingSearchKey(String uuid){
        Object searchKey = getSearchKey(uuid);
        if(!isExist(searchKey)){
            return searchKey;
        } else {
            throw new ExistStrorageException(uuid, searchKey);
        }
    }

    protected abstract void clearStorage();

    protected abstract void doSave(Resume resume, Object searchKey);

    protected abstract void doUpdate(Resume resume, Object searchKey);

    protected abstract void doDelete(String uuid, Object searchKey);

    protected abstract Resume doGet(String uuid, Object searchKey);

    protected abstract Object getAllInStorage();

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean isExist(Object searchKey);

}
