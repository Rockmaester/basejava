package com.urise.webapp.experiments;

public class TestSingletonEager {
    private static TestSingletonEager ourInstance = new TestSingletonEager();

    private TestSingletonEager() {
    }
    public static TestSingletonEager getInstance() {
        return ourInstance;
    }
}
