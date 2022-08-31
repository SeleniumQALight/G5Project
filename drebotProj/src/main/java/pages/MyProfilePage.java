package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyProfilePage extends ParentPage {
    @FindBy(xpath = ".//img[@class=\"avatar-small\"]")
    private WebElement avatar;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        waitChatToBeHide();
        Assert.assertTrue("MyProfilePage ia not loaded", isElementDisplayed(avatar));
        return this;
    }

}
