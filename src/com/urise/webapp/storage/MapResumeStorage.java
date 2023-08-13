package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class MapResumeStorage extends AbstractMapStorage<Resume>{

    @Override
    protected Resume doGet(String uuid, Resume searchKey) {
        return searchKey;
    }
    @Override
    // сюда передается в качестве searchKey само резюме
    protected void doDelete(String uuid, Resume searchKey) {
        storage.remove(searchKey.getUuid());
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean isExist(Resume searchKey) {
        return searchKey != null;
    }
}
