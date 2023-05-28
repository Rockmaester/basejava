package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int size;
    private final int STORAGE_LIMIT = 10000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if(size == storage.length){
            System.out.println("\nНевозможно сохранить. Лимит хранилища превышен!");
        } else if(index != -1){
            System.out.println("\nРезюме с таким uuid (\"" + resume.getUuid() + "\") уже существует под индексом: " + index);
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public void update(Resume resume){
        int index = getIndex(resume.getUuid());
        if(index == -1){
            System.out.println("Резюме с uuid \"" + resume.getUuid() + "\" нет в списке!");
            return;
        }
        storage[index] = resume;
        System.out.println("Резюме \"" + resume.getUuid() + "\" обновлено.");
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if(index != -1){
            return storage[index];
        }
        System.out.println("Резюме с uuid \"" + uuid + "\" нет в списке!");
        return null;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if(index != -1){
            storage[index] = storage[size-1];
            storage[size-1] = null;
            size--;
        } else {
            System.out.println("\nВы пытаетесь удалить резюме по uuid (\"" + uuid + "\"), которого нет в списке резюме!");
        }
    }

    private int getIndex(String uuid){
        for(int i = 0; i < size; i++){
            if(storage[i].getUuid().equals(uuid)){
                return i;
            }
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }
}
