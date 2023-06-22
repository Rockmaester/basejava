package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.LinkedList;
import java.util.List;

public class ListStorage extends AbstractStorage{

    protected final List<Resume> storage = new LinkedList<>();

    @Override
    protected void clearStorage() {
        storage.clear();
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        storage.add(resume);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        int result = -1;
        for (int i = 0; i < storage.size(); i++){
            if(storage.get(i).getUuid().equals(uuid)){
                result = i;
            }
        }
        return result;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (int)searchKey > -1;
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        storage.set((int)searchKey,resume);
    }

    @Override
    protected void doDelete(String uuid, Object searchKey) {
        storage.remove((int)searchKey);
    }

    @Override
    protected Resume doGet(String uuid, Object searchKey) {
        return storage.get((int)searchKey);
    }

    @Override
    protected List<Resume> getAllInStorage() {
        return storage;
    }

    @Override
    public int size() {
        return storage.size();
    }
}
