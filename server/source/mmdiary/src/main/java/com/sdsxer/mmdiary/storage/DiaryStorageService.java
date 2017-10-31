package com.sdsxer.mmdiary.storage;

import com.sdsxer.mmdiary.config.StorageProperties;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

  @Override
  public long available() {
    return super.available();
  }

  public Path storeCoverImage(long userId, long timestamp, MultipartFile file) {
    return super.store(Paths.get("cover" + File.separator + userId
        + File.separator + timestamp), file);
  }

  public Path storeAttachment(long userId, long timestamp, MultipartFile file) {
    return super.store(Paths.get("attachment" + userId + File.separator
        +  File.separator + timestamp), file);
  }
}
