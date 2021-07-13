package com.epam.moby.testng.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class WelcomePage extends BasePage {

  @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"onBoarding-btn-skip\"]")
  private MobileElement skipButton;

  public SingPage clickSkipButton() {
    skipButton.click();
    return new SingPage();
  }
}
