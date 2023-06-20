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
        saveInStorage(resume, searchKey);
    }

    public final void update(Resume resume) {
        Object searchKey = getExistingSearchKey(resume.getUuid());
        updateResume(resume, searchKey);
    }

    public final void delete(String uuid) {
        Object searchKey = getExistingSearchKey(uuid);
        deleteInStorage(uuid, searchKey);
    }

    public final Resume get(String uuid) {
        Object searchKey = getExistingSearchKey(uuid);
        return getInStorage(uuid, searchKey);
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

    protected abstract void saveInStorage(Resume resume, Object searchKey);

    protected abstract void updateResume(Resume resume, Object searchKey);

    protected abstract void deleteInStorage(String uuid, Object searchKey);

    protected abstract Resume getInStorage(String uuid, Object searchKey);

    protected abstract Object getAllInStorage();

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean isExist(Object searchKey);

}
