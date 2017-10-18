package com.sdsxer.mmdiary.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Storage service config
 */
@ConfigurationProperties("storage")
public class StorageProperties {

    private String location = "upload-dir";
    private int capacity = 1024 * 1024 * 1024;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
