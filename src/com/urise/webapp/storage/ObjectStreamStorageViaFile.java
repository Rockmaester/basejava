package com.urise.webapp.storage;

import java.io.File;

public class ObjectStreamStorageViaFile extends AbstractFileStorage {

    public ObjectStreamStorageViaFile(File directory) {
        super(directory);
        this.serializationStrategy = new StrategyOOS();
    }

//    @Override
//    protected void doWrite(Resume resume, OutputStream outputStream) throws IOException {
//        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)){
//            objectOutputStream.writeObject(resume);
//        }
//    }

//    @Override
//    protected Resume doRead(InputStream inputStream) throws IOException {
//        try(ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)){
//            return (Resume) objectInputStream.readObject();
//        } catch (ClassNotFoundException e) {
//            /* Такая ошибка (ClassNotFoundException) может возникнуть, если Resume нет в ClassPath, например, если приложение
//            разделено на 2 инстанса: один для чтения, другой для записи. И один из инстансов не знает, что такое Resume  */
//            throw new StorageException("Error reading resume", null, e);
//        }
//    }
}
