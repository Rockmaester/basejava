package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractMapStorage<SK> extends AbstractStorage<SK>{

    protected final Map<String, Resume> storage = new HashMap<>();

    abstract protected SK getSearchKey(String uuid);


    @Override
    protected void clearStorage() {
        storage.clear();
    }

    @Override
    protected void doSave(Resume resume, SK searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doUpdate(Resume resume, SK searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }
}
