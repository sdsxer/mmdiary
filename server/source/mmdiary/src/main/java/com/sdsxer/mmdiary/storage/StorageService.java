package com.sdsxer.mmdiary.storage;

import java.nio.file.Path;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    void init() throws StorageException;

    Path getRootLocation();

    Path store(Path subPath, MultipartFile file) throws StorageException;

    List<Path> loadAll() throws StorageException;

    void deleteAll();

    long available();
}
