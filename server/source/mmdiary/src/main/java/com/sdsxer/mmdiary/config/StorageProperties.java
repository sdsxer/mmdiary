package com.sdsxer.mmdiary.config;

import com.sdsxer.mmdiary.common.Constants;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Storage service config
 * Created by leon on 2017/9/26.
 */
@ConfigurationProperties("storage")
public class StorageProperties {

    private String rootLocation = "/usr/local/nginx/html/" + Constants.APP_NAME;
    private int capacity = 1024 * 1024 * 1024; // 1G

    public String getRootLocation() {
        return rootLocation;
    }

    public void setRootLocation(String rootLocation) {
        this.rootLocation = rootLocation;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
