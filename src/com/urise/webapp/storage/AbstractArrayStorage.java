package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStrorageException;
import com.urise.webapp.exception.LimitExceedException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage{

    protected int size;
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];

    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public final void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if(size == storage.length){
            throw new LimitExceedException();
//            System.out.println("\nНевозможно сохранить. Лимит хранилища превышен!");
        } else if(index > -1){
            throw new ExistStrorageException(resume.getUuid(), index);
//            System.out.println("\nРезюме с таким uuid (\"" + resume.getUuid() + "\") уже существует под индексом: " + index);
        } else {
            saveResume(resume, index);
            size++;
        }
    }

    protected abstract void saveResume(Resume resume, int index);

    public final void update(Resume resume){
        int index = getIndex(resume.getUuid());
        if(index < 0){
            throw new NotExistStorageException(resume.getUuid());
//            System.out.println("Резюме с uuid \"" + resume.getUuid() + "\" нет в списке!");
//            return;
        } else {
            storage[index] = resume;
            System.out.println("Резюме \"" + resume.getUuid() + "\" обновлено.");
        }
    }

    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if(index < 0){
            throw new NotExistStorageException(uuid);
//            System.out.println("\nВы пытаетесь удалить резюме по uuid (\"" + uuid + "\"), которого нет в списке резюме!");
        } else {
            deleteResume(index);
            storage[size - 1] = null;
            size--;
        }
    }
    protected abstract void deleteResume(int index);

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if(index > -1){
            return storage[index];
        }
        throw new NotExistStorageException(uuid);
//        System.out.println("Резюме с uuid \"" + uuid + "\" нет в списке!");
//        return null;
    }

    protected abstract int getIndex(String uuid);

    public final Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public final int size() {
        return size;
    }
}
