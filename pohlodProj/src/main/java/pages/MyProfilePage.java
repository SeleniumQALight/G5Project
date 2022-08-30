package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyProfilePage extends ParentPage{

    @FindBy(xpath = ".//img[@class=\"avatar-small\"]")
    private WebElement avatarIcon;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage checkIsRedirectToMyProfilePage(){
        //TODO checkURL
        waitChatToBeHide();
        Assert.assertTrue( "MyProfile Page is not loaded", isElementDisplayed(avatarIcon));
        return this;
    }


}
