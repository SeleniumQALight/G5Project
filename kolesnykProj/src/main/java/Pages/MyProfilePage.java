package Pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyProfilePage extends ParentPage{

    @FindBy(xpath = "//img[@class='avatar-small']")
    private WebElement avatarElement;
    public MyProfilePage(WebDriver driver) {
        super(driver);
    }

    public MyProfilePage checkIsRedirectToMyProfilePage(){
        waitChatToBeHide();
        Assert.assertTrue("My Profile page is not opened", isElementDisplayed(avatarElement));
        return this;
    }
}
