package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;

public class StrategyOOS implements SerializationStrategy {
    @Override
    public void doWrite(Resume resume, OutputStream outputStream) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)){
            objectOutputStream.writeObject(resume);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Resume doRead(InputStream inputStream)  {
        try(ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)){
            return (Resume) objectInputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            /* Такая ошибка (ClassNotFoundException) может возникнуть, если Resume нет в ClassPath, например, если приложение
            разделено на 2 инстанса: один для чтения, другой для записи. И один из инстансов не знает, что такое Resume  */
            throw new StorageException("Error reading resume", null, e);
        }
    }
}
