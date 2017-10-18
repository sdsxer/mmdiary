package com.sdsxer.mmdiary.storage;

import java.nio.file.Path;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    void init() throws StorageException;

    Path store(MultipartFile file) throws StorageException;

    Stream<Path> loadAll() throws StorageException;

    Path load(String filename) throws StorageException;

    Resource loadAsResource(String filename) throws StorageException;

    void deleteAll();

    long available();
}
