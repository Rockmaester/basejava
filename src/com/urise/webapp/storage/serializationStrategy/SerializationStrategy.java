package com.urise.webapp.storage.serializationStrategy;

import com.urise.webapp.model.Resume;

import java.io.InputStream;
import java.io.OutputStream;

public interface SerializationStrategy {
    void doWrite(Resume resume, OutputStream outputStream);

    Resume doRead(InputStream inputStream);
}
