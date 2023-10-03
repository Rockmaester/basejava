package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public abstract class AbstractPathStorage extends AbstractStorage<Path>{
    private final Path directory;

//    protected abstract void doWrite(Resume resume, OutputStream outputStream) throws IOException;
//    protected abstract Resume doRead(InputStream inputStream) throws IOException;

    // Внедрение паттерна "стратегия".
    protected SerializationStrategy serializationStrategy;

    public void doWrite(Resume resume, OutputStream outputStream){
        serializationStrategy.doWrite(resume, outputStream);
    }

    public Resume doRead(InputStream inputStream){
        return serializationStrategy.doRead(inputStream);
    }

    protected AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "Directory mast not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)){
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }

    @Override
    protected void clearStorage() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error");
        }
    }

    @Override
    protected void doSave(Resume resume, Path file) {
        try {
            boolean result = file.toFile().createNewFile();
            if(!result){
                throw new StorageException("File creating error");
            }
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + file.toAbsolutePath(), String.valueOf(file.getFileName()), e);
        }
        doUpdate(resume, file);
    }

    @Override
    protected void doUpdate(Resume resume, Path file) {
        try {
            doWrite(resume, new BufferedOutputStream(Files.newOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File writing error", String.valueOf(file.getFileName()), e);
        }
    }

    @Override
    protected void doDelete(Path file) {
        try {
            Files.deleteIfExists(file);
        } catch (IOException e) {
            throw new StorageException("Storage clearing error", String.valueOf(file.getFileName()));
        }
    }

    @Override
    protected Resume doGet(String uuid, Path file) {
        try {
            return doRead(new BufferedInputStream(Files.newInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File reading error", String.valueOf(file.getFileName()), e);
        }
    }

    @Override
    protected List<Resume> doGetAllSorted() {
        List<Resume> list = new ArrayList<>();
        File[] files = directory.toFile().listFiles();
        if (files == null) {
            throw new StorageException("List of files is null");
        }
        for(File file : files){
            list.add(doGet(file.getName(), file.toPath()));
        }
        return list;
    }

    @Override
    protected Path getSearchKey(String uuid) {
        // Файлы, которые будут хранить резюме, будут иметь имя = uuid
        return new File(String.valueOf(directory), uuid).toPath();
    }

    @Override
    protected boolean isExist(Path file) {
        return Files.exists(file);
    }

    @Override
    public int size() {
        try {
            return (int) Files.list(directory).count();
        } catch (IOException e) {
            throw new StorageException("Directory access error");
        }
    }
}