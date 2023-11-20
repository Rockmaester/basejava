package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.serializationStrategy.StreamSerializer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.*;


public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;

    private StreamSerializer streamSerializer;

    protected PathStorage(String dir, StreamSerializer streamSerializer) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "Directory mast not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
        this.streamSerializer = streamSerializer;
    }

    @Override
    protected void clearStorage() {
        getFilesList().forEach(this::doDelete);
    }


    @Override
    protected void doSave(Resume resume, Path file) {
        try {
            Files.createFile(file);
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + file.toAbsolutePath(), getFileName(file), e);
        }
        doUpdate(resume, file);
    }

    @Override
    protected void doUpdate(Resume resume, Path file) {
        try {
            streamSerializer.doWrite(resume, new BufferedOutputStream(Files.newOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File writing error", getFileName(file), e);
        }
    }

    @Override
    protected void doDelete(Path file) {
        try {
            Files.deleteIfExists(file);
        } catch (IOException e) {
            throw new StorageException("Storage clearing error", getFileName(file), e);
        }
    }

    @Override
    protected Resume doGet(Path file) {
        try {
            return streamSerializer.doRead(new BufferedInputStream(Files.newInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File reading error", getFileName(file), e);
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        return getFilesList().map(path -> doGet(path)).collect(Collectors.toList());
    }

    @Override
    protected Path getSearchKey(String uuid) {
        // Файлы, которые будут хранить резюме, будут иметь имя = uuid
        return directory.resolve(uuid);
    }

    @Override
    protected boolean isExist(Path file) {
        return Files.isRegularFile(file);
    }

    @Override
    public int size() {
        return (int) getFilesList().count();
    }

    private String getFileName(Path path){
        return path.getFileName().toString();
    }

    // Вынос чтения каталога в отдельный метод - избавление от дублирования try-catch в методах
    private Stream<Path> getFilesList()  {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Directory reading error", e);
        }
    }
}