package com.urise.webapp.storage;

import com.urise.webapp.exception.LimitExceedException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage{

    protected int size;
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];



    @Override
    protected void clearStorage() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected final void doSave(Resume resume, Object searchKey) {
        if(size == storage.length){
            throw new LimitExceedException();
        } else {
            saveResume(resume, (int)searchKey);
            size++;
        }
    }

    @Override
    protected final void doUpdate(Resume resume, Object searchKey){
        storage[(int)searchKey] = resume;
        System.out.println("Резюме \"" + resume.getUuid() + "\" обновлено.");
    }

    @Override
    public final void doDelete(String uuid, Object searchKey) {
            deleteResume((int)searchKey);
            storage[size - 1] = null;
            size--;
    }

    @Override
    protected final Resume doGet(String uuid, Object searchKey) {
            return storage[(int)searchKey];
    }
//    @Override
//    protected final Resume[] getAllInStorage() {
//        return Arrays.copyOfRange(storage, 0, size);
//    }

    protected final List<Resume> getAllSorted(){
//        List<Resume> list = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(storage, 0, size)));
        List<Resume> list = Arrays.asList(Arrays.copyOfRange(storage, 0, size));
        list.sort(RESUME_COMPARATOR);
        return list;
    }

    public final int size() {
        return size;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (int)searchKey > -1;
    }

    protected abstract void saveResume(Resume resume, int index);

    protected abstract void deleteResume(int index);

}
