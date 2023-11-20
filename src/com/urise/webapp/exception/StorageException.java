package com.urise.webapp.exception;

public class StorageException extends RuntimeException{
    private String uuid;

    public StorageException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Exception e) {
        this(message, null, e); // чтобы не дублировать - делаем переадресацию к конструктору с бОльшим количеством аргументов
    }

    public StorageException(String message, String name, Exception e) {
        super(message, e);
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }
}
