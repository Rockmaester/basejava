package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File>{

    private final File directory;

    protected abstract void doWrite(Resume resume, File file) throws IOException;
    protected abstract Resume doRead(File file);

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "Directory mast not be null");
        if (!directory.isDirectory()){ // проверка на то, что это директория, а не файл
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() && !directory.canWrite()){ // проверка на отказ доступа к директории (нпр. системной)
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    protected void clearStorage() {
        File[] files = directory.listFiles();
        if(files == null) {
            throw new StorageException("Storage is null");
        }
        for(File file : files){
            boolean result = file.delete();
            if(!result){
                throw new StorageException("Storage clearing error");
            }
        }
    }

    @Override
    protected void doSave(Resume resume, File file) {
        try {
            boolean result = file.createNewFile();
            if(!result){
                throw new StorageException("File creating error");
            }
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO Error", file.getName(), e);
        }
    }

    @Override
    protected void doUpdate(Resume resume, File file) {
        try {
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO Error", file.getName(), e);
        }
    }

    @Override
    protected void doDelete(String uuid, File file) {
        boolean result = file.delete();
        if(!result){
            throw new StorageException("Storage clearing error");
        }
    }

    @Override
    protected Resume doGet(String uuid, File file) {
        return doRead(file);
    }

    @Override
    protected List<Resume> doGetAllSorted() {
        List<Resume> list = new ArrayList<>();
        File[] files = directory.listFiles();
        for(File file : Objects.requireNonNull(files, "list of files is null")){
            list.add(doRead(file));
        }
        return list;
    }
    
    @Override
    protected File getSearchKey(String uuid) {
        // Файлы, которые будут хранить резюме, будут иметь имя = uuid
        return new File(directory, uuid);
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    public int size() {
        return Objects.requireNonNull(directory.listFiles(), "directory is null").length;
    }
}