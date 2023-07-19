package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage{

    protected final Map<String, Resume> storage = new HashMap<>();

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
    protected void doDelete(String uuid, Object searchKey) {
        storage.remove(searchKey);
    }

    @Override
    protected Resume doGet(String uuid, Object searchKey) {
        return storage.get(searchKey);
    }

//    @Override
//    protected Map<String, Resume> getAllInStorage() {
//        return storage;
//    }

    @Override
    protected Object getAllSorted() {
        List<Resume> list = new ArrayList<>();
        for(Map.Entry<String, Resume> pair : storage.entrySet()){
            list.add(pair.getValue());
        }
        list.sort(RESUME_COMPARATOR);
        return list;
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return storage.containsKey(searchKey);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
