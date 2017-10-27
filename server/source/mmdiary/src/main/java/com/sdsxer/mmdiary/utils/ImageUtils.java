package com.sdsxer.mmdiary.utils;

import static com.sdsxer.mmdiary.common.Constants.SUPPORTED_IMAGE_FORMAT;

import com.google.common.base.Strings;
import com.sdsxer.mmdiary.common.Constants;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class ImageUtils {

  public static boolean isFormatSupported(String format) {
    boolean supported = false;
    if(!Strings.isNullOrEmpty(format)) {
      for(String supportedFormat : SUPPORTED_IMAGE_FORMAT){
        if(supportedFormat.equalsIgnoreCase(format)) {
          supported = true;
          break;
        }
      }
    }
    return supported;
  }

  public static boolean isImageSizeMatch(int width, int height, InputStream imageInputStream) {
    boolean supported = false;
    if(imageInputStream != null) {
      try {
        BufferedImage bufferedImage = ImageIO.read(imageInputStream);
        return bufferedImage.getWidth() == width
            && bufferedImage.getHeight() == height;
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return supported;
  }
}
