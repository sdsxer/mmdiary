package com.sdsxer.mmdiary.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Storage service config
 * Created by leon on 2017/9/26.
 */
@ConfigurationProperties("storage")
public class StorageProperties {

    private String rootLocation = "/Users/leon/Desktop/tomcat/upload";
    private String projectLocation = "/mmdiary/file";
    private int capacity = 1024 * 1024 * 1024;

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

    public String getProjectLocation() {
        return projectLocation;
    }

    public void setProjectLocation(String projectLocation) {
        this.projectLocation = projectLocation;
    }
}
