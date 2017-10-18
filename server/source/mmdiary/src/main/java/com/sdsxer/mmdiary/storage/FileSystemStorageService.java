package com.sdsxer.mmdiary.storage;

import com.sdsxer.mmdiary.config.StorageProperties;
import com.sdsxer.mmdiary.utils.FileUtils;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSystemStorageService implements StorageService {

    private static final Logger logger = LoggerFactory.getLogger(FileSystemStorageService.class);

    private final Path location;

    private StorageProperties properties;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        location = Paths.get(properties.getRootLocation() + File.separator
            + properties.getProjectLocation());
        logger.info("Storage config: location={}, capacity={}", location.toAbsolutePath(),
            properties.getCapacity());
        this.properties = properties;
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(location);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    @Override
    public Path store(MultipartFile file) {
        if(file == null) {
            throw new IllegalArgumentException();
        }
        long available = available();
        if(available < file.getSize()) {
            throw new OverCapacityLimitException("Space available: " + available
                + ", target file size: " + file.getSize());
        }
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
            Files.copy(file.getInputStream(), location.resolve(filename),
                StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
        return location.resolve(filename);
    }

    @Override
    public Path getRelativePath(Path absolutePath) throws StorageException {
        Path relativePath = null;
        if(absolutePath != null) {
            try {
                relativePath = Paths.get(properties.getProjectLocation(),
                    absolutePath.getFileName().toString());
            }
            catch (Exception e) {
                throw new StorageException("Can not covert path", e);
            }
        }
        return relativePath;
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.location, 1)
                    .filter(path -> !path.equals(this.location))
                    .map(path -> this.location.relativize(path));
        }
        catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        Path path = null;
        try {
            path = location.resolve(filename);
        }
        catch (InvalidPathException e) {
            throw new StorageException("Failed to read stored file", e);
        }
        return path;
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);
            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(location.toFile());
    }

    @Override
    public long available() {
        return properties.getCapacity() - FileUtils.getDirSize(location.toAbsolutePath().toString());
    }
}
