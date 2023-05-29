package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    public void save(Resume resume) {
        int index = binaryInsert(Integer.parseInt(resume.getUuid()));
        shift(resume, index);

    }

    @Override
    public void update(Resume resume) {

    }

    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume();
        resume.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, resume);
    }

    public int binaryInsert(int inValue) {
        int leftIndex = 0;
        int rightIndex = size - 1;
        int index;

        while (true) {
            index = (rightIndex + leftIndex) / 2;
            if (size == 0) {
                return index = 0;
            }
            if (leftIndex == index) {
                if (Integer.parseInt(storage[index].getUuid()) > inValue) {
                    return index;
                }
            }
            if (Integer.parseInt(storage[index].getUuid()) < inValue) {
                leftIndex = index + 1;
                if (leftIndex > rightIndex) {
                    return index += 1;
                }
            } else if (leftIndex > rightIndex) {
                return index;
            } else {
                rightIndex = index - 1;
            }
        }
    }

    public void shift(Resume value, int index) {
        for (int i = size; i > index; i--) {
            storage[i] = storage[i - 1];
        }
        storage[index] = value;
        size++;
    }
}
