package tests;

import config.AppiumConfig;
import models.Auth;
import models.Contact;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthentificationScreen;
import screens.ContactListScreen;

import java.util.Random;

public class AddNewContactTests extends AppiumConfig {

    @BeforeClass
    public void preCondition() {
        new AuthentificationScreen(driver).fillLoginRegistrationForm(Auth.builder().email("test12@gmail.com").password("vilevinQa!1234").build()).submitLogin();
    }

    @Test
    public void createNewContactSuccess() {
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder().name("Ben").lastName("Adam" + i).email("ben" + i + "adam@gmail.com").phone("123456" + i).address("Earth").description("friend").build();
        new ContactListScreen(driver).openContactForm().fillContactForm(contact).submitContactForm().isContactAddedByName(contact.getName(), contact.getLastName());


    }

    @Test
    public void createNewContactSuccessReq() {

    }

    @Test
    public void createNewContactUnsuccess_EmptyName() {

        Contact contact = Contact.builder().name("").lastName("Adam").email("benadam@gmail.com").phone("1234567890").address("Earth").description("friend").build();
        new ContactListScreen(driver).openContactForm().fillContactForm(contact).submitContactFormNegative().isErrorMessageHasText("{name=must not be blank}");


    }


    @AfterClass(enabled = false)
    public void postCondition() {
        new ContactListScreen(driver).logout();
    }
}