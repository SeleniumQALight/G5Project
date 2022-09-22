package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPostPage extends ParentPage{

//    @FindBy(xpath = ".//input[@id='post-title']")
//    private WebElement enterPostTitle;
    private String postTitleLocator = ".//*[text()='%s']";
    @FindBy(xpath = ".//button[@class='btn btn-primary']")
    private WebElement buttonSaveUpdates;
    @FindBy(xpath = ".//div[@class='alert alert-success text-center' and text()='Post successfully updated.']")
    private WebElement messagePostUpdated;

    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private WebElement buttonMyProfile;
    @FindBy(name = "title")
    private WebElement inputTitle;

    public EditPostPage(WebDriver webDriver){
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/edit";
    }

    public EditPostPage editTitle(String text) {
        enterTextIntoElement(inputTitle,text);
        return this;
    }

    public EditPostPage clickOnButtonSaveUpdates() {
            clickOnElement(buttonSaveUpdates);
            return this;
    }

    public EditPostPage checkMessagePostUpdated() {
        Assert.assertTrue("Post successfully updated - message is not on display"
                , isElementDisplayed(messagePostUpdated));
        return this;
    }

    public PostPage clickOnMyProfileButton(){
        clickOnElement(buttonMyProfile);
        return new PostPage(webDriver);
    }
}
