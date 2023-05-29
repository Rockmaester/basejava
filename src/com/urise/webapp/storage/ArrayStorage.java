package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage{

    @Override
    protected void saveInStorage(Resume resume, int index){
        // второй параметр не используется
        storage[size] = resume;
        size++;
    }

    @Override
    public void deleteInStorage(String uuid, int index) {
        storage[index] = storage[size-1];
        storage[size-1] = null;
        size--;
    }

    protected int getIndex(String uuid){
        for(int i = 0; i < size; i++){
            if(storage[i].getUuid().equals(uuid)){
                return i;
            }
        }
        return -1;
    }
}
