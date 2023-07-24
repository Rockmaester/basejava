package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractMapStorage extends AbstractStorage{

    protected final Map<String, Resume> storage = new HashMap<>();

    abstract protected Object getSearchKey(String uuid);

    @Override
    protected void clearStorage() {
        storage.clear();
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected List<Resume> doGetAllSorted() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }
}
