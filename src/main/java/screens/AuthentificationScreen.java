package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.Auth;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AuthentificationScreen extends BaseScreen {
    public AuthentificationScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(id = "com.sheygam.contactapp:id/inputEmail")
    MobileElement emailEditText;
    @FindBy(id = "com.sheygam.contactapp:id/inputPassword")
    MobileElement passwordEditText;
    //    @FindBy(xpath = "//*[text()='LOGIN']")
    @FindBy(xpath = "//*[@text='LOGIN']")
    MobileElement loginBtn;
    @FindBy(xpath = "com.sheygam.contactapp:id/regBtn")
    MobileElement regBtn;



    public AuthentificationScreen fillEmail(String email) {
//        pause(4000)
        should(emailEditText, 10);
        type(emailEditText, email);
        return this;
    }

    public AuthentificationScreen fillPassword(String password) {
        type(passwordEditText, password);
        return this;
    }

    public ContactListScreen submitLogin() {
        loginBtn.click();
        return new ContactListScreen(driver);
    }

    public AuthentificationScreen submitLoginNegative() {
        loginBtn.click();
        return this;
    }

    public AuthentificationScreen fillLoginRegistrationForm(Auth auth) {
        should(emailEditText, 10);
        type(emailEditText, auth.getEmail());
        type(passwordEditText, auth.getPassword());
        return this;
    }

    public AuthentificationScreen isErrorMessageHasText(String text) {
        Alert alert = new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains(text));
        alert.accept();
        return this;
    }

//    public ContactListScreen submitRegistration() {
//        regBtn.click();
//        return new ContactListScreen(driver);
//    }
}
//test12@gmail.com  vilevinQa!1234