package com.epam.moby.testng.configuration;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.*;
import static io.appium.java_client.remote.MobileCapabilityType.APP;
import static io.appium.java_client.remote.MobileCapabilityType.UDID;

public class CapabilitiesConfigurator {

    private CapabilitiesConfigurator() {
    }

    public static DesiredCapabilities getLocalCapabilities() {
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setCapability(UDID, ConfigurationReader.getProperties().getUdid());
        capability.setCapability(AVD, ConfigurationReader.getProperties().getLocalDeviceName());
        capability.setCapability(APP_PACKAGE, ConfigurationReader.getProperties().getAppPackage());
        capability.setCapability(APP_ACTIVITY, ConfigurationReader.getProperties().getAppActivity());
        capability.setCapability(APP, new File(ConfigurationReader.getProperties().getAppPath()).getAbsolutePath());
        return capability;
    }
}
