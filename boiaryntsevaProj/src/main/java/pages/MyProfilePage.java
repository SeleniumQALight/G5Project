package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyProfilePage extends ParentPage {
    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = ".//img[@class='avatar-small']")
    private WebElement avatar;

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        waitChatToBeHidden();
        Assert.assertTrue("My profile page is not there", isElementDisplayed(avatar));
        return this;
    }
}
