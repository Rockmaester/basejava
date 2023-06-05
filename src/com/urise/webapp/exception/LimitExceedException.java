package com.urise.webapp.exception;

public class LimitExceedException extends StorageException{
    public LimitExceedException() {
        super("Невозможно сохранить. Лимит хранилища превышен");
    }
}
