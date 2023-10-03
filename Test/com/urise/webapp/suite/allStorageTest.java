package com.urise.webapp.suite;


import com.urise.webapp.storage.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayStorageTest.class,
        ListStorageTest.class,
        MapUuidStorageTest.class,
        MapResumeStorageTest.class,
        SortedArrayStorageTest.class,
        ObjectStreamStorageViaFileTest.class,
        ObjectStreamStorageViaPathTest.class
})
public class allStorageTest {
}