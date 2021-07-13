package com.epam.moby.testng.driver;

import com.epam.moby.testng.configuration.AddressConfigurator;
import com.epam.moby.testng.configuration.CapabilitiesConfigurator;
import com.epam.moby.testng.configuration.ConfigurationReader;
import com.epam.moby.testng.configuration.EnvironmentType;
import com.epam.moby.testng.log.Log;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

public class DriverManager {

    private static final EnvironmentType ENVIRONMENT_TYPE = EnvironmentType.valueOf(ConfigurationReader.getProperties()
            .getEnvironmentType().toUpperCase());
    private static AppiumDriver<MobileElement> driver;

    private DriverManager() {
    }

    public static AppiumDriver<MobileElement> getDriver() {
        if (driver == null) {
            driver = initDriver();
            driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        }

        return driver;
    }

    public static void closeDriver() {
        Optional.ofNullable(getDriver()).ifPresent(driverInstance -> {
            driverInstance.quit();
            driver = null;
            Log.info("Driver is closed");
        });
    }

    public static void closeEmulator() {
        try {
            Runtime.getRuntime().exec(format("adb -s %s emu kill", ConfigurationReader.getProperties().getUdid()));
            Log.info("Emulator is closed");
        } catch (IOException exception) {
            Log.error("Error to close emulator", exception);
        }
    }

    public static void closeAppium() {
        AddressConfigurator.stopService();
    }

    private static AppiumDriver<MobileElement> initDriver() {
        switch (ENVIRONMENT_TYPE) {
            case LOCAL:
                driver = new AndroidDriver<>(AddressConfigurator
                        .getAppiumDriverLocalService(ConfigurationReader.getProperties().getAppiumPort()),
                        CapabilitiesConfigurator.getLocalCapabilities());
                break;
            default:
                throw new IllegalArgumentException(format("Unexpected environment type: %s", ENVIRONMENT_TYPE));
        }
        Log.info(format("Driver is created. Environment type is %s", ENVIRONMENT_TYPE));
        return driver;
    }
}
