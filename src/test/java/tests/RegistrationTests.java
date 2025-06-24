package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.AuthentificationScreen;

import java.util.Random;

public class RegistrationTests extends AppiumConfig {


    @Test
    public void registrationSuccess() {
        int i = new Random().nextInt(1000);
        Assert.assertTrue(new AuthentificationScreen(driver)
                .fillEmail("test" + i + "@gmail.com")
                .fillPassword("vilevinQa!1234")
                .submitRegistration()
                .isActivityTitleDisplayed("Contact list"))
        ;
    }

    @Test
    public void regUnsuccessWrongEmail() {

        new AuthentificationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("testgmail.com").password("Zxcv0987!").build())
                .submitRegistUnsuccesful()
                .isErrorMessageHasText("{username=must be a well-formed email address}");
    }

    @Test
    public void regUnsuccessWrongPassword() {

        new AuthentificationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("test@gmail.com").password("zxcvbnm,.").build())
                .submitRegistUnsuccesful()
                .isErrorMessageHasText("{password= At least 8 characters; Must contain at least 1 uppercase letter, 1 lowercase letter, and 1 number; Can contain special characters [@$#^&*!]}");
    }

    @Test
    public void regUnsuccessUserExist() {

        new AuthentificationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("test12@gmail.com").password("vilevinQa!1234").build())
                .submitRegistUnsuccesful()
                .isErrorMessageHasText("User already exists");
    }

}
