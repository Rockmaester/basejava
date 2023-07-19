package com.urise.webapp.suite;


import com.urise.webapp.storage.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AbstractArrayStorageTest.class,
        AbstractStorageTest.class,
        ArrayStorageTest.class,
        ListStorageTest.class,
        MapUuidStorageTest.class,
        SortedArrayStorageTest.class
})
public class allStorageTest {
}

//@Suite.SuiteClasses({
//        AbstractArrayStorageTest.class,
//        AbstractStorageTest.class,
//        ArrayStorageTest.class,
//        ListStorageTest.class,
//        MapUuidStorageTest.class,
//        SortedArrayStorageTest.class
//})
