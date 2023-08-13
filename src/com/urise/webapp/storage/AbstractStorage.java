package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStrorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage<SK> implements Storage {

    protected static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    protected abstract void clearStorage();

    protected abstract void doSave(Resume resume, SK searchKey);

    protected abstract void doUpdate(Resume resume, SK searchKey);

    protected abstract void doDelete(String uuid, SK searchKey);

    protected abstract Resume doGet(String uuid, SK searchKey);

    protected abstract List<Resume> doGetAllSorted();

    protected abstract SK getSearchKey(String uuid);

    protected abstract boolean isExist(SK searchKey);

    @Override
    public final void clear() {
        clearStorage();
    }

    @Override
    public final void save(Resume resume) {
        SK searchKey = getNotExistingSearchKey(resume.getUuid());
        doSave(resume, searchKey);
    }

    @Override
    public final void update(Resume resume) {
        SK searchKey = getExistingSearchKey(resume.getUuid());
        doUpdate(resume, searchKey);
    }

    @Override
    public final void delete(String uuid) {
        SK searchKey = getExistingSearchKey(uuid);
        doDelete(uuid, searchKey);
    }

    @Override
    public final Resume get(String uuid) {
        SK searchKey = getExistingSearchKey(uuid);
        return doGet(uuid, searchKey);
    }

    @Override
    public final List<Resume> getAllSorted() {
        List<Resume> list = doGetAllSorted();
        list.sort(RESUME_COMPARATOR);
        return list;
    }

    private SK getExistingSearchKey(String uuid){
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)){
            return searchKey;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    private SK getNotExistingSearchKey(String uuid){
        SK searchKey = getSearchKey(uuid);
        if(!isExist(searchKey)){
            return searchKey;
        } else {
            throw new ExistStrorageException(uuid, searchKey);
        }
    }
}