package com.epam.moby.testng;

import com.epam.moby.testng.driver.DriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.testng.annotations.AfterMethod;

public class BaseTest {

    @BeforeClass
    public void createSession(){
        DriverManager.getDriver();
    }

    @AfterMethod
    public void resetApp(){
        DriverManager.getDriver().resetApp();
    }

    @AfterClass
    public void closeSession(){
        DriverManager.closeDriver();
        DriverManager.closeAppium();
        DriverManager.closeEmulator();
    }
}
