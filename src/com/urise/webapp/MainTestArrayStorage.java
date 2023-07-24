package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.AbstractArrayStorage;
import com.urise.webapp.storage.ArrayStorage;

/**
 * Test for your com.urise.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final AbstractArrayStorage ARRAY_STORAGE = new ArrayStorage();
//    static final AbstractArrayStorage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
//        Resume r1 = new Resume();
//        r1.setUuid("uuid1");
//        Resume r2 = new Resume();
//        r2.setUuid("uuid2");
//        Resume r3 = new Resume();
//        r3.setUuid("uuid3");

        Resume r1 = new Resume("77", "name 1");
//        r1.setUuid("77");
        Resume r2 = new Resume("99", "name 2");
//        r2.setUuid("99");
        Resume r3 = new Resume("44", "name 3");
//        r3.setUuid("44");
        Resume r4 = new Resume("55", "name 4");
//        r4.setUuid("55");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r4);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        System.out.println("\nUpdate r2: ");
        ARRAY_STORAGE.update(r2);

//        printAll();
        System.out.println("\ndelete r1: ");
        ARRAY_STORAGE.delete(r1.getUuid());
//        printAll();
        ARRAY_STORAGE.clear();
//        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

//    static void printAll() {
//        System.out.println("\nGet All");
//        for (Resume r : (Resume[]) ARRAY_STORAGE.getAll()) {
//            System.out.println(r);
//        }
//    }
}
