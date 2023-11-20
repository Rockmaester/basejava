package com.urise.webapp.storage.serializationStrategy;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;

public class ObjectStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume resume, OutputStream outputStream) throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)){
            objectOutputStream.writeObject(resume);
        }
    }

    @Override
    public Resume doRead(InputStream inputStream) throws IOException {
        try(ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)){
            return (Resume) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            /* Такая ошибка (ClassNotFoundException) может возникнуть, если Resume нет в ClassPath, например, если приложение
            разделено на 2 инстанса: один для чтения, другой для записи. И один из инстансов не знает, что такое Resume  */
            throw new StorageException("Error reading resume", null, e);
        }
    }
}
