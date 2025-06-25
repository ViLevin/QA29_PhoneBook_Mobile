package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Rectangle;

import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class ContactListScreen extends BaseScreen {
    public ContactListScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    MobileElement activityTextView;

    @FindBy(xpath = "//*[@content-desc ='More options']")//android.widget.ImageView[@content-desc="More options"
    MobileElement menuOptions;
    @FindBy(xpath = "//*[@text = 'Logout']")
    MobileElement logoutBtn;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/add_contact_btn']")
    MobileElement plusBtn;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowName']")
    List<MobileElement> contactNameList;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowContainer']")
    List<MobileElement> contactList;
    @FindBy(xpath = "//*[@resource-id='android:id/button1']")
    MobileElement yesBtn;


    public boolean isActivityTitleDisplayed(String text) {
//        return activityTextView.getText().contains("Contact list");
        return isShouldHave(activityTextView, text, 8);
    }

    public AuthentificationScreen logout() {
        if (activityTextView.getText().equals("Contact list")) {
            menuOptions.click();
            logoutBtn.click();
        }
        return new AuthentificationScreen(driver);
    }

    public ContactListScreen isAccountOpened() {
        Assert.assertTrue(isActivityTitleDisplayed("Contact list"));
        return this;
    }

    public AddNewContactScreen openContactForm() {
        isShouldHave(activityTextView, "Contact list", 5);
        plusBtn.click();
        return new AddNewContactScreen(driver);
    }

    public ContactListScreen isContactAddedByName(String name, String lastName) {
        isShouldHave(activityTextView, "Contact list", 5);
        boolean isPresent = false;
        for (MobileElement mobileElement : contactNameList) {
            if (mobileElement.getText().equals(name + " " + lastName)) {
                isPresent = true;
                break;
            }
        }
        Assert.assertTrue(isPresent);
        return this;
    }

    public ContactListScreen deleteFirstContact() {
        isActivityTitleDisplayed("Contact list");
        MobileElement first = contactList.get(0);
        Rectangle rectangle = first.getRect();
        int xFrom = rectangle.getX() + rectangle.getHeight() / 8;
        int y = rectangle.getY() + rectangle.getHeight() / 2;
        int xTo = rectangle.getWidth() - xFrom;
//        int xTo = rectangle.getWidth()+rectangle.getHeight()/8*7;


        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(xFrom, y)).moveTo(PointOption.point(xTo, y)).release().perform();

        return this;
    }

    public ContactListScreen isListSizeLessOnOne() {
        Assert.assertEquals(countBefore-countAfter, 1);

        return this;
    }
}
