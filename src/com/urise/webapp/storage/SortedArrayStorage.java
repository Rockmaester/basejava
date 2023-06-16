package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    protected void saveResume(Resume resume, int index) {
        int insertionPoint = - 1 - index;
        if(size > 0){
            System.arraycopy(storage, insertionPoint, storage, insertionPoint + 1, size - insertionPoint);
        }
        storage[insertionPoint] = resume;
    }

    @Override
    protected void deleteResume(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - (index + 1));
    }

    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, resume);
    }
}
