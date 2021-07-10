package com.epam.moby.testng.configuration;

import com.epam.moby.testng.log.Log;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.IOError;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Optional;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.LOG_LEVEL;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.SESSION_OVERRIDE;
import static java.lang.String.format;

public class AddressConfigurator {

    private static AppiumDriverLocalService appiumDriverLocalService;

    private AddressConfigurator() {
    }

    public static AppiumDriverLocalService getAppiumDriverLocalService(int port) {
        if (appiumDriverLocalService == null) startService(port);
        return appiumDriverLocalService;
    }

    public static void startService(int port) {
        freePort(port);
        appiumDriverLocalService = new AppiumServiceBuilder().withIPAddress(ConfigurationReader.getProperties()
                .getAppiumAddress()).usingPort(port).withArgument(SESSION_OVERRIDE)
                .withArgument(LOG_LEVEL, "error").build();
        appiumDriverLocalService.start();
        Log.info(format("Appium service is started on port %d", port));
    }

    public static void stopService() {
        Optional.ofNullable(appiumDriverLocalService).ifPresent(service -> {
            service.stop();
            Log.info("Appium service is stopped");
        });
    }

    private static boolean isPortFree(int port) {
        boolean isFree = true;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Log.info(format("Specified port %d is available", port));
        } catch (IOException exception) {
            isFree = false;
            Log.warn(format("Specified port %d is occupied with a process. Process will be terminated", port));
        }
        return isFree;
    }

    private static void freePort(int port) {
        if (!isPortFree(port)) {
            try {
                Runtime.getRuntime().exec("taskkill /F /IM node.exe");
            } catch (IOException exception) {
                Log.error(format("Error to free port %d", port), exception);
            }
        }
    }
}
