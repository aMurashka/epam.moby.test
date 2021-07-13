package com.epam.moby.testng;

import com.epam.moby.testng.pages.WelcomePage;
import org.junit.Assert;
import org.junit.Test;

public class LoginNegativeTest extends BaseTest {

  @Test
  public void loginNegativeTest() {
    String expectedErrorMessage = "Incorrect user ID or password. Type the correct user ID and password, and try again.";
    String actualErrorMessage = new WelcomePage().clickSkipButton()
      .clickLoginButton()
      .clickSingWithEpamButton()
      .inputUserName("ivan@epam.com")
      .inputPassword("12345")
      .singWithWrongCredentials()
      .getErrorMessageText();
    Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
  }
}
