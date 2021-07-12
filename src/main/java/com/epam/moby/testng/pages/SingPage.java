package com.epam.moby.testng.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SingPage extends BasePage {

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"login-btn-signUp\"]")
    private MobileElement signUpButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"login-btn-login\"]")
    private MobileElement loginButton;

    public AccessPage clickLoginButton() {
        loginButton.click();
        return new AccessPage();
    }

}
