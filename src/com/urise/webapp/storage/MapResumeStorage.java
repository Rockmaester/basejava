package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Iterator;
import java.util.Map;

public class MapResumeStorage extends AbstractMapStorage{

    @Override
    protected Resume doGet(String uuid, Object searchKey) {
        return (Resume) getSearchKey(uuid);
    }
    @Override
    // сюда передается в качестве searchKey само резюме
    protected void doDelete(String uuid, Object searchKey) {
        Iterator<Map.Entry<String, Resume>> iterator = storage.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, Resume> pair = iterator.next();
            if(pair.getValue() == searchKey){
                iterator.remove();
            }
        }
    }


    @Override
    protected Object getSearchKey(String uuid) {
        for(Map.Entry<String, Resume> pair : storage.entrySet()){
            if(pair.getKey().equals(uuid)){
                return pair.getValue();
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        for(Map.Entry<String, Resume> pair : storage.entrySet()){
            if(pair.getValue() == searchKey){
                return true;
            }
        }
        return false;
    }
}
