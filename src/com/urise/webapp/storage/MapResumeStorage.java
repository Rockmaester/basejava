package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class MapResumeStorage extends AbstractMapStorage{

    @Override
    protected Resume doGet(String uuid, Object searchKey) {
//        return (Resume) getSearchKey(uuid);
        return (Resume) searchKey;
    }
    @Override
    // сюда передается в качестве searchKey само резюме
    protected void doDelete(String uuid, Object searchKey) {
        storage.remove(((Resume) searchKey).getUuid());
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }
}
