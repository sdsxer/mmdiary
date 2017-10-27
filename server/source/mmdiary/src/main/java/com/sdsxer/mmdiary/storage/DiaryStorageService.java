package com.sdsxer.mmdiary.storage;

import com.sdsxer.mmdiary.config.StorageProperties;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Service;

@Service
public class DiaryStorageService extends FileSystemStorageService {

  public DiaryStorageService(StorageProperties properties) {
    super(properties);
  }

  @Override
  public Path getRootLocation() {
    Path parentPath = super.getRootLocation();
    return Paths.get(parentPath.toAbsolutePath() + File.separator + "diary");
  }
}
