package com.epam.moby.testng.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends BasePage {

    @AndroidFindBy(id = "userNameInput")
    private MobileElement userNameInput;

    @AndroidFindBy(id = "passwordInput")
    private MobileElement passwordInput;

    @AndroidFindBy(id = "submitButton")
    private MobileElement submitButton;

    @AndroidFindBy(id = "errorText")
    private MobileElement errorMessageText;

    public LoginPage inputUserName(String name) {
        userNameInput.setValue(name);
        return this;
    }

    public LoginPage inputPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    public LoginPage singWithWrongCredentials() {
        submitButton.click();
        return this;
    }

    public String getErrorMessageText() {
        return errorMessageText.getText();
    }
}
