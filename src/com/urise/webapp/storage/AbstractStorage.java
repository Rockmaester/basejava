package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStrorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public final void clear() {
        clearStorage();
    }

    protected abstract void clearStorage();

    public final void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > -1) {
            throw new ExistStrorageException(resume.getUuid(), index);
        }
        saveInStorage(resume, index);
    }

    protected abstract void saveInStorage(Resume resume, int index);

    protected abstract int getIndex(String uuid);

    public final void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            updateResume(resume, index);
        }

    }

    protected abstract void updateResume(Resume resume, int index);

    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteInStorage(uuid, index);
        }
    }

    protected abstract void deleteInStorage(String uuid, int index);

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            return getInStorage(uuid, index);
        }
    }

    protected abstract Resume getInStorage(String uuid, int index);

    public final Object getAll() {
        return getAllInStorage();
    }

    protected abstract Object getAllInStorage();
}
