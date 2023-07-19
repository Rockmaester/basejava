package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListStorageTest extends AbstractStorageTest{

    public ListStorageTest() {
        super(new ListStorage());
    }

    @Test
    public void getAll() {
        List<Resume> testList = new ArrayList<>(Arrays.asList(RESUME_1, RESUME_2, RESUME_3));
        Assert.assertEquals(testList, storage.getAll());
    }
}