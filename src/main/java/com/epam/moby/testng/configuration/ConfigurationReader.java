package com.epam.moby.testng.configuration;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    private static final Properties properties = new Properties();
    private static ConfigurationReader instance;

    private ConfigurationReader() {
    }

    public static ConfigurationReader getProperties() {
        if (instance == null) {
            instance = new ConfigurationReader();
            try (var propertyFile = new FileReader("src/main/resources/test.properties")) {
                properties.load(propertyFile);
            } catch (IOException e) {
                //ToDo
                //add log
            }
        }
        return instance;
    }

    public String getEnvironmentType() {
        return properties.getProperty("env.type");
    }

    public String getAppPath() {
        return properties.getProperty("app.path");
    }

    public String getAppPackage() {
        return properties.getProperty("app.package");
    }

    public String getAppActivity() {
        return properties.getProperty("app.activity");
    }

    public String getPlatformName() {
        return properties.getProperty("platform.name");
    }

    public String getPlatformVersion() {
        return properties.getProperty("platform.version");
    }

    public String getLocalDeviceName() {
        return properties.getProperty("local.device.name");
    }

    public String getUdid() {
        return properties.getProperty("udid");
    }

    public String getAppiumAddress() {
        return properties.getProperty("appium.address");
    }

    public String getAppiumPort() {
        return properties.getProperty("appium.port");
    }
}
