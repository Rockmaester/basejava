package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;


/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage{

    @Override
    protected void saveResume(Resume resume, int index){
        // второй параметр не используется
        storage[size] = resume;
    }

    @Override
    protected void deleteResume(int index) {
        storage[index] = storage[size-1];
    }

    protected Integer getSearchKey(String uuid){
        for(int i = 0; i < size; i++){
            if(storage[i].getUuid().equals(uuid)){
                return i;
            }
        }
        return -1;
    }
}
