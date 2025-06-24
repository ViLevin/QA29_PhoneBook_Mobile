package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.AuthentificationScreen;
import screens.SplashScreen;

public class LoginTestsSecond extends AppiumConfig {
    //test12@gmail.com  vilevinQa!1234


    @Test
    public void loginSuccess() {
        new AuthentificationScreen(driver)
                .fillEmail("test12@gmail.com")
                .fillPassword("vilevinQa!1234")
                .submitLogin()
                .isAccountOpened()
                .logout();
    }

    @Test
    public void loginSuccessModel() {
        new AuthentificationScreen(driver).fillLoginRegistrationForm(Auth.builder().email("test12@gmail.com").password("vilevinQa!1234").build())
                .submitLogin()
                .isAccountOpened()
                .logout();
    }

    @Test
    public void loginWrongEmail() {
        new AuthentificationScreen(driver).fillLoginRegistrationForm(Auth.builder().email("test12gmail.com").password("vilevinQa!1234").build()).submitLoginNegative()
                .isErrorMessageHasText("Login or Password incorrect");
    }

}
