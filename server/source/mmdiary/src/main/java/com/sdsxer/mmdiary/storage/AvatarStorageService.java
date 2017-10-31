package com.sdsxer.mmdiary.storage;

import com.sdsxer.mmdiary.config.StorageProperties;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AvatarStorageService extends FileSystemStorageService {

  @Autowired
  public AvatarStorageService(StorageProperties properties) {
    super(properties);
  }

  @Override
  public Path getRootLocation() {
    Path parentPath = super.getRootLocation();
    return Paths.get(parentPath.toAbsolutePath() + File.separator + "avatar");
  }

  @Override
  public long available() {
    return super.available();
  }

  public Path storeAvatarImage(long userId, MultipartFile file) {
    return super.store(Paths.get(String.valueOf(userId)), file);
  }
}
