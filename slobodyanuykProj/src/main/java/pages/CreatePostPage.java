package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage{
    //.//input[@id='post-title']
    @FindBy (xpath = ".//input[@id='post-title']")
    private WebElement inputTitle;
    @FindBy(tagName = "select")
    private WebElement dropDownRole;
    @FindBy (xpath = ".//option[text()='Приватне повідомлення']")
    private WebElement optionElement;
    @FindBy (xpath =".//textarea[@id='post-body']")
    private WebElement inputBody;
    @FindBy (xpath =".//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }
    public CreatePostPage checkIsRedirectToCreatePostPage () {
        //TODO check URL
        Assert.assertTrue("Page Create post is not loaded",isElementDisplayed(inputTitle));
        return this;
    }
    public CreatePostPage enterTextInInputTitle(String title) {
        enterTextIntoElement(inputTitle,title);
        return this;
    }

//    public CreatePostPage selectTextInDropDown(String textForSelect) {
//        selectTextDropDown(dropDownRole, textForSelect);
//        return this;
//    }

    public CreatePostPage selectValueInDropDownRole(String valueForSelect) {
        selectValueDropDown(dropDownRole,valueForSelect);
        return this;
    }
    public CreatePostPage selectTextInDropDown () {
        return this;
    }

    public CreatePostPage selectTextInDropDownUi(String textUi) {
        selectDropDownElementUi(dropDownRole,optionElement);
        return this;
    }

    public CreatePostPage enterTextInInputBody(String textBody) {
             enterTextIntoElement(inputBody,textBody);
        return this;
    }
    public CreatePostPage clickOnButtonSaveNewPost () {
        clickOnElement(buttonSaveNewPost);
        return  this;
    }
}
