package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer>{

    protected final List<Resume> storage = new ArrayList<>();

    @Override
    protected void clearStorage() {
        storage.clear();
    }

    @Override
    protected void doSave(Resume resume, Integer searchKey) {
        storage.add(resume);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        int result = -1;
        for (int i = 0; i < storage.size(); i++){
            if(storage.get(i).getUuid().equals(uuid)){
                result = i;
            }
        }
        return result;
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return (int)searchKey > -1;
    }

    @Override
    protected void doUpdate(Resume resume, Integer searchKey) {
        storage.set(searchKey,resume);
    }

    @Override
    protected void doDelete(Integer searchKey) {
        storage.remove((int)searchKey);
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return storage.get((int)searchKey);
    }

    @Override
    protected List<Resume> doCopyAll() {
        return storage;
    }

    @Override
    public int size() {
        return storage.size();
    }
}
