package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage{

    protected final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected void clearStorage() {
        storage.clear();
    }

    @Override
    protected void saveInStorage(Resume resume, Object searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void updateResume(Resume resume, Object searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void deleteInStorage(String uuid, Object searchKey) {
        storage.remove(searchKey);
    }

    @Override
    protected Resume getInStorage(String uuid, Object searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected Map<String, Resume> getAllInStorage() {
        return storage;
    }

    @Override
    protected Object getSearchKey(String uuid) {
        Object key = null;
        for(Map.Entry<String, Resume> pair : storage.entrySet()){
            if(pair.getKey().equals(uuid)){
                key = uuid;
            }
        }
        return key;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    public int size() {
        return storage.size();
    }
}
