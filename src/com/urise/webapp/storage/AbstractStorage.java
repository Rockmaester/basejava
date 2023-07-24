package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStrorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    protected static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    protected abstract void clearStorage();

    protected abstract void doSave(Resume resume, Object searchKey);

    protected abstract void doUpdate(Resume resume, Object searchKey);

    protected abstract void doDelete(String uuid, Object searchKey);

    protected abstract Resume doGet(String uuid, Object searchKey);

    protected abstract Object getAllInStorage();

    protected abstract List<Resume> getAllInList();

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean isExist(Object searchKey);

    @Override
    public final void clear() {
        clearStorage();
    }

    @Override
    public final void save(Resume resume) {
        Object searchKey = getNotExistingSearchKey(resume.getUuid());
        doSave(resume, searchKey);
    }

    @Override
    public final void update(Resume resume) {
        Object searchKey = getExistingSearchKey(resume.getUuid());
        doUpdate(resume, searchKey);
    }

    @Override
    public final void delete(String uuid) {
        Object searchKey = getExistingSearchKey(uuid);
        doDelete(uuid, searchKey);
    }

    @Override
    public final Resume get(String uuid) {
        Object searchKey = getExistingSearchKey(uuid);
        return doGet(uuid, searchKey);
    }

    @Override
    public final Object getAll() {
        return getAllInStorage();
    }

    @Override
    public final List<Resume> getAllSorted() {
        List<Resume> list = getAllInList();
        list.sort(RESUME_COMPARATOR);
        return list;
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
}