package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    public void saveInStorage(Resume resume, int index) {
        int insertionPoint = - 1 - index;
        shiftExtention(resume, insertionPoint);
    }

    @Override
    protected void deleteInStorage(String uuid, int index) {
        storage[index] = null;
        shiftConstriction(index);
        size--;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume();
        resume.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, resume);
    }

    private void shiftExtention(Resume value, int index) {
        for (int i = size; i > index; i--) {
            storage[i] = storage[i - 1];
        }
        storage[index] = value;
        size++;
    }

    private void shiftConstriction(int index){
        for(int i = index + 1; i < size; i++){
            storage[i-1] = storage[i];
        }
        storage[size-1] = null;
    }
}
