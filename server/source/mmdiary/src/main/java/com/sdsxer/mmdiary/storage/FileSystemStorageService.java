package com.sdsxer.mmdiary.storage;

import com.sdsxer.mmdiary.config.StorageProperties;
import com.sdsxer.mmdiary.utils.FileUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSystemStorageService implements StorageService {

    private static final Logger logger = LoggerFactory.getLogger(FileSystemStorageService.class);

    private final Path rootLocation;
    private final StorageProperties properties;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        rootLocation = getRootLocation();
        this.properties = properties;
        logger.info("Storage config: root location={}, capacity={}",
            rootLocation.toAbsolutePath(), properties.getCapacity());
        try {
            init();
        }
        catch (StorageException e) {
            logger.error("Could not initialize storage", e);
        }
    }

    @Override
    public void init() throws StorageException {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    @Override
    public Path getRootLocation() {
        return Paths.get(properties.getRootLocation());
    }

    @Override
    public Path store(Path subPath, MultipartFile file) throws StorageException {
        // check param's legality
        if(file == null) {
            throw new IllegalArgumentException();
        }
        // check space limit
        long available = available();
        long fileSize = file.getSize();
        if(available < fileSize) {
            throw new OverCapacityLimitException("Space available: " + available
                + ", target file size: " + fileSize);
        }
        // create sub dir
        Path path = Paths.get(rootLocation.toString());
        if(subPath != null) {
            path = Paths.get(path.toString()
                + (subPath.startsWith(File.separator) ? "" : File.separator)
                + subPath.toString());
            try {
                Files.createDirectories(path);
            }
            catch (IOException e) {
                throw new StorageException("Could not initialize storage", e);
            }
        }
        // check file's legality
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                    "Cannot store file with relative path outside current directory " + filename);
            }

            // rename file with unique string
            String fileSuffix = FileUtils.getFileSuffix(filename);
            filename = UUID.randomUUID() + "." + fileSuffix;
            // save file
            Files.copy(file.getInputStream(), path.resolve(filename),
                StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
        return path.resolve(filename);
    }

    @Override
    public List<Path> loadAll() throws StorageException {
        return null;
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public long available() {
        return properties.getCapacity() - FileUtils.getDirSize(rootLocation.toString());
    }
}
