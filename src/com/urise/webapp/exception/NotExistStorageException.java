package com.urise.webapp.exception;

public class NotExistStorageException extends StorageException{

    public NotExistStorageException(String uuid) {
        super("Резюме с uuid \"" + uuid + "\" нет в списке!", uuid);
    }
}
