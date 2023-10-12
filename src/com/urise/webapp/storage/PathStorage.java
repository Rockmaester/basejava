package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.serializationStrategy.SerializationStrategy;
import com.urise.webapp.storage.serializationStrategy.StrategyOOS;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;

    protected SerializationStrategy serializationStrategy = new StrategyOOS();

    public void doWrite(Resume resume, OutputStream outputStream) {
        serializationStrategy.doWrite(resume, outputStream);
    }

    public Resume doRead(InputStream inputStream) {
        return serializationStrategy.doRead(inputStream);
    }

    protected PathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "Directory mast not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
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
            if (!file.toFile().createNewFile()) {
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
        try (DirectoryStream<Path> files = Files.newDirectoryStream(directory)) {
            for (Path file : files) {
                if (!Files.isDirectory(file)) {
                    list.add(doGet(String.valueOf(file.getFileName()), file));
                }
            }
        } catch (IOException e) {
            throw new StorageException("Directory reading error");
        }
        return list;
    }

    @Override
    protected Path getSearchKey(String uuid) {
        // Файлы, которые будут хранить резюме, будут иметь имя = uuid
        return directory.resolve(uuid);
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