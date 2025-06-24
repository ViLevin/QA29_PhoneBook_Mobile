package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import screens.AuthentificationScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

public class LoginTests extends AppiumConfig {


    @Test
    public void loginSuccess() {
//        boolean result = new SplashScreen(driver).checkCurrentVersion("Version 1.0.0")
        boolean result = new AuthentificationScreen(driver)
                .fillEmail("test12@gmail.com")
                .fillPassword("vilevinQa!1234")
                .submitLogin()
                .isActivityTitleDisplayed("Contact list");

        Assert.assertTrue(result);
    }

    @Test
    public void loginSuccessModel() {
//        boolean result = new SplashScreen(driver).checkCurrentVersion("Version 1.0.0")
        boolean result = new AuthentificationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("test12@gmail.com").password("vilevinQa!1234").build())
                .submitLogin()
                .isActivityTitleDisplayed("Contact list");
        Assert.assertTrue(result);
    }

    @Test
    public void loginSuccessModel2() {
        Assert.assertTrue(new AuthentificationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("test12@gmail.com").password("vilevinQa!1234").build())
                .submitLogin()
                .isActivityTitleDisplayed("Contact list"));
    }

    @Test
    public void loginWrongEmail() {
        new AuthentificationScreen(driver).fillLoginRegistrationForm(Auth.builder().email("test12gmail.com").password("vilevinQa!1234").build()).submitLoginNegative()
                .isErrorMessageHasText("Login or Password incorrect");
    }

    @Test
    public void loginWrongPassword() {
        new AuthentificationScreen(driver).fillLoginRegistrationForm(Auth.builder().email("test12@gmail.com").password("vilevinQa1234").build()).submitLoginNegative()
                .isErrorMessageHasText("Login or Password incorrect");
    }

    @Test
    public void loginUnregistered() {
        new AuthentificationScreen(driver).fillLoginRegistrationForm(Auth.builder().email("1test12@gmail.com").password("vilevinQa1234").build()).submitLoginNegative()
                .isErrorMessageHasText("Login or Password incorrect");
    }


    @AfterMethod
    public void postCondition() {
        new ContactListScreen(driver).logout();
    }

}
