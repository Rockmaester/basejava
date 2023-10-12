package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.serializationStrategy.SerializationStrategy;
import com.urise.webapp.storage.serializationStrategy.StrategyOOS;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class FileStorage extends AbstractStorage<File> implements SerializationStrategy {

    private final File directory;

    protected SerializationStrategy serializationStrategy = new StrategyOOS();

    public void doWrite(Resume resume, OutputStream outputStream) {
        serializationStrategy.doWrite(resume, outputStream);
    }

    public Resume doRead(InputStream inputStream) {
        return serializationStrategy.doRead(inputStream);
    }


    protected FileStorage(File directory) {
        Objects.requireNonNull(directory, "Directory must not be null");
        if (!directory.isDirectory()) { // проверка на то, что это директория, а не файл
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() && !directory.canWrite()) { // проверка на отказ доступа к директории (нпр. системной)
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    protected void clearStorage() {
        File[] files = directory.listFiles();
        checkDirectoryIsNull(files);
        for (File file : files) {
            doDelete(file);
        }
    }

    @Override
    protected void doSave(Resume resume, File file) {
        try {
            if (!file.createNewFile()) {
                throw new StorageException("File creating error");
            }
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + file.getAbsolutePath(), file.getName(), e);
        }
        doUpdate(resume, file);
    }

    @Override
    protected void doUpdate(Resume resume, File file) {
        try {
            doWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File writing error", file.getName(), e);
        }
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("Storage clearing error", file.getName());
        }
    }

    @Override
    protected Resume doGet(String uuid, File file) {
        try {
            return doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File reading error", file.getName(), e);
        }
    }

    @Override
    protected List<Resume> doGetAllSorted() {
        List<Resume> list = new ArrayList<>();
        File[] files = directory.listFiles();
        checkDirectoryIsNull(files);
        for (File file : files) {
            list.add(doGet(file.getName(), file));
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
        File[] listOfFiles = directory.listFiles();
        checkDirectoryIsNull(listOfFiles);
        return listOfFiles.length;
    }

    private void checkDirectoryIsNull(File[] listOfFiles) {
        if (listOfFiles == null) {
            throw new StorageException("List of files returns null");
        }
    }
}