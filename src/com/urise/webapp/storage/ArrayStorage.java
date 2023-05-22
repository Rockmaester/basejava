package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int size;
    private Resume[] storage = new Resume[3];

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if(size == storage.length){
            System.out.println("\nНевозможно сохранить. Лимит хранилища превышен!");
            return;
        }
        int index;
        if((index = resumeIsExist(resume.getUuid())) == -1) {
            storage[size] = resume;
            size++;
        } else {
            System.out.println("\nРезюме с таким uuid (" + resume.getUuid() + ") уже существует под индексом: " + index);
        }
    }

    public void update(Resume resume){
        int index = resumeIsExist(resume.getUuid());
        Resume currentResume = get(resume.getUuid());
        // что-то делаем с currentResume (update)
        storage[index] = currentResume;
        System.out.println("Резюме " + resume.getUuid() + " обновлено.");
    }

    public Resume get(String uuid) {
        int index;
        if((index = resumeIsExist(uuid)) != -1){
            return storage[index];
        } else {
            System.out.println("Резюме с uuid "+ uuid + " нет в списке!");
        }
        return null;
    }

    public void delete(String uuid) {
        int index;
        if((index = resumeIsExist(uuid)) != -1){
            storage[index] = storage[size-1];
            storage[size-1] = null;
            size--;
        } else {
            System.out.println("\nВы пытаетесь удалить резюме по uuid (" + uuid + "), которого нет в списке резюме!");
        }
    }

    public int resumeIsExist(String uuid){
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
