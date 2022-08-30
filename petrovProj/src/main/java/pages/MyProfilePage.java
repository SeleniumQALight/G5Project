package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyProfilePage extends PostPage{
    @FindBy(xpath = ".//img[@class='avatar-small']")
    private WebElement avatarOnProfilePage;



    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage checkIsRedirectToMyProfilePage(){
        //TODO check url
        waitChatToBeHide();
        Assert.assertTrue(" MyProfilePage is not loaded", isElementDisplayed(avatarOnProfilePage));
        return  this;
    }
}
