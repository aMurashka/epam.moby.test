package com.epam.moby.testng.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends BasePage {

    @AndroidFindBy(xpath = "//*[@resource-id='userNameInput']")
    private MobileElement userNameInput;

    @AndroidFindBy(xpath = "//*[@resource-id='passwordInput']")
    private MobileElement passwordInput;

    @AndroidFindBy(xpath = "//*[@resource-id='submitButton']")
    private MobileElement submitButton;

    @AndroidFindBy(xpath = "//*[@resource-id='errorText']")
    private MobileElement errorMessageText;

    public LoginPage inputUserName(String name) {
        userNameInput.sendKeys(name);
        return this;
    }

    public LoginPage inputPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public LoginPage singWithWrongCredentials() {
        submitButton.click();
        return this;
    }

    public String getErrorMessageText() {
        return errorMessageText.getAttribute("content-desc");
    }
}
