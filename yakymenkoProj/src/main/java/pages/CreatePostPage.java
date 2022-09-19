package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage { // alt+enter → constructor
    // .//*[@name='title']
    @FindBy(name = "title")
    private WebElement inputTitle;
    @FindBy(tagName = "select")
    private WebElement dropDownRole;
    @FindBy(name = "body")
    private WebElement inputBodyContent;
    @FindBy(xpath = ".//select/option[text()='Групове повідомлення']")
    private WebElement groupMessageValueInDropDown;

    @FindBy(xpath = ".//textarea[@id='post-body']")
    private WebElement inputBody;
    @FindBy(xpath = ".//form[@action='/create-post']//button")
    private WebElement buttonSavePost;
    @FindBy(xpath = ".//input[@type='checkbox' and @name='uniquePost']")
    private WebElement checkBoxPostUnique;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/create-post";
    }

    public CreatePostPage checkIsRedirectToCreatePostPage() {
        checkUrl();
        waitChatToBeHide();
        Assert.assertTrue("Page CreatePost is not loaded", isElementDisplayed(inputTitle));
        return this;
    }

    // Метод який заповнить тайтл
    public CreatePostPage enterTextInInputTitle(String title) {
        enterTextIntoElement(inputTitle, title);
        return this;
    }

    public CreatePostPage selectValueInDropDownRole(String valueForSelect) {
        selectValueInDropDown(dropDownRole, valueForSelect);
        return this;
    }

    public CreatePostPage enterTextInInputBodyContent(String bodyContent) {
        enterTextIntoElement(inputBodyContent, bodyContent);
        return this;
    }

    public CreatePostPage selectValueInDropDownRoleUI(String groupMessageValueInDropDown) {
        selectTextInDropDownUI(dropDownRole, groupMessageValueInDropDown);
        return this;
    }

    public CreatePostPage enterTextInInputBody(String text) {
        enterTextIntoElement(inputBody, text);
        return this;
    }

    public PostPage clickOnSavePostButton() {
        clickOnElement(buttonSavePost);
        return new PostPage(webDriver);
    }

    public CreatePostPage setCheckBoxWithValue(String stateValue) {
        setCheckboxWithStatus(checkBoxPostUnique, stateValue);
        return this;
    }
}
