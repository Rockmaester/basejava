package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbstractMapStorageTest extends AbstractStorageTest{

    public AbstractMapStorageTest(Storage storage) {
        super(storage);
    }

    @Override
    public void getAll() {
        Map<String, Resume> testMap = new HashMap<>();
        testMap.put(UUID_1, RESUME_1);
        testMap.put(UUID_2, RESUME_2);
        testMap.put(UUID_3, RESUME_3);
        Assert.assertEquals(testMap, storage.getAll());
    }

    @Override
    public void getAllSorted(){
        Map<String, Resume> testMap = new HashMap<>();
        testMap.put(UUID_1, RESUME_1);
        testMap.put(UUID_2, RESUME_2);
        testMap.put(UUID_3, RESUME_3);

        List<Resume> testList = new ArrayList<>();
        for(Map.Entry<String, Resume> pair : testMap.entrySet()){
            testList.add(pair.getValue());
        }
        testList.sort((o1, o2) -> {
            int result = o1.getFullName().compareTo(o2.getFullName());
            if(result == 0){
                result = o1.getUuid().compareTo(o2.getUuid());
            }
            return result;
        });
        Assert.assertEquals(testList, storage.getAllSorted());
    }



}