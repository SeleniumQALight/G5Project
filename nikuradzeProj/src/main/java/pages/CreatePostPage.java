package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage{
    // .//*[@name='title']
    @FindBy(name = "title")
    private WebElement inputTitle;
    @FindBy(tagName = "textarea")
    private WebElement inputBodyContent;
    @FindBy(tagName = "select")
    private WebElement dropDownRole;
    @FindBy(xpath = ".//option[text()='Приватне повідомлення']")
    private WebElement privateMessage;
    @FindBy(xpath = ".//*[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage checkIsRedirectToCreatePostPage(){
        //TODO check URL
        Assert.assertTrue("Page CreatePost is not loaded", isElementDisplayed(inputTitle));
        return this;
    }

    public CreatePostPage enterTextInInputTitle(String title){
        enterTextIntoElement(inputTitle, title);
        return this;
    }
    public CreatePostPage enterTextInInputBody(String bodyContent){
        enterTextIntoElement(inputBodyContent, bodyContent);
        return this;
    }

    public CreatePostPage selectTextInDropdownRole(String textForSelect) {
        selectTextInDropDown(dropDownRole, textForSelect);
        return this;
    }
    public CreatePostPage selectTextInDropDownByUIRole(){
        selectTextInDropDownByUI(dropDownRole, privateMessage);
        return this;
    }
    public PostPage clickOnButtonSaveNewPost(){
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }

    public CreatePostPage selectValueInDropDownRole(String valueForSelect) {
        selectValueInDropDown(dropDownRole, valueForSelect);
        return this;
    }

}
