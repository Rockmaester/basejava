package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage{

    protected int size;
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public final void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if(size == storage.length){
            System.out.println("\nНевозможно сохранить. Лимит хранилища превышен!");
        } else if(index > -1){
            System.out.println("\nРезюме с таким uuid (\"" + resume.getUuid() + "\") уже существует под индексом: " + index);
        } else {
            saveInStorage(resume, index);
        }
    }

    protected abstract void saveInStorage(Resume resume, int index);

    public void update(Resume resume){
        int index = getIndex(resume.getUuid());
        if(index < 0){
            System.out.println("Резюме с uuid \"" + resume.getUuid() + "\" нет в списке!");
            return;
        }
        storage[index] = resume;
        System.out.println("Резюме \"" + resume.getUuid() + "\" обновлено.");
    }

    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if(index < 0){
            System.out.println("\nВы пытаетесь удалить резюме по uuid (\"" + uuid + "\"), которого нет в списке резюме!");
        } else {
            deleteInStorage(uuid, index);
        }
    }
    protected abstract void deleteInStorage(String uuid, int index);

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if(index > -1){
            return storage[index];
        }
        System.out.println("Резюме с uuid \"" + uuid + "\" нет в списке!");
        return null;
    }

    protected abstract int getIndex(String uuid);

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }


}
