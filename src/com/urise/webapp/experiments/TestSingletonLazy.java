package com.urise.webapp.experiments;

public class TestSingletonLazy {
    private static TestSingletonLazy ourInstance;

    private TestSingletonLazy() {
    }

    public static TestSingletonLazy getInstance() {
        if(ourInstance == null){
            ourInstance = new TestSingletonLazy();
        }
        return ourInstance;
    }
}
