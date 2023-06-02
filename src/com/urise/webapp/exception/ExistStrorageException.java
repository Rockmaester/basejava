package com.urise.webapp.exception;

public class ExistStrorageException extends StorageException{

    public ExistStrorageException(String uuid, int index) {
        super("Резюме с таким uuid (" + uuid + ") уже существует под индексом: " + index + "." , uuid);
    }
}
