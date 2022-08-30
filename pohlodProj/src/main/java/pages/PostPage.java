package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostPage extends ParentPage{
    @FindBy(xpath = ".//div[contains(text(),'New post successfully created.')]")
    private WebElement newPostCreated;


    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public PostPage checkIsRedirectToPostPage (){
        //TODO checkURL
        Assert.assertTrue("Page Post is not loaded", isElementDisplayed(newPostCreated));
        return this;
    }

}
