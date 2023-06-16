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
    protected void saveInStorage(Resume resume, int index) {

        storage.add(resume);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume(uuid);
        return storage.indexOf(resume);
    }

    @Override
    protected void updateResume(Resume resume, int index) {
        storage.set(index,resume);
    }

    @Override
    protected void deleteInStorage(String uuid, int index) {
        storage.remove(index);
    }

    @Override
    protected Resume getInStorage(String uuid, int index) {
        return storage.get(index);
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
