package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostPage extends ParentPage{

    @FindBy(xpath = ".//div//div[text()='New post successfully created.']")
    private WebElement messageSuccessfully;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public PostPage checkIsRedirectToPostPage(){
        //TODO check url
        Assert.assertTrue("Page Post is not loaded", isElementWasDisplayed(messageSuccessfully));
        return  this;
    }
}
