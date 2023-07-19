package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class MapUuidStorage extends AbstractMapStorage{

    @Override
    protected Resume doGet(String uuid, Object searchKey) {
        return storage.get(searchKey);
    }
    @Override
    // сюда передается в качестве searchKey ключ в мапе
    protected void doDelete(String uuid, Object searchKey) {
        storage.remove(searchKey);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return storage.containsKey(searchKey);
    }
}
