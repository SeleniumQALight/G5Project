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
    @FindBy(xpath = ".//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;
    @FindBy(xpath = ".//textarea[@id='post-body']")
    private WebElement inputBody;
    @FindBy(xpath = ".//form[@action='/create-post']//button")
    private WebElement buttonSavePost;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/create-post";
    }

    public CreatePostPage checkIsRedirectToCreatePostPage() {
        checkUrl();
        Assert.assertTrue("Page CreatePost is not loaded", isElementDisplayed(inputTitle));
        return this;
    }

    // Метод який заповнить тайтл
    public CreatePostPage enterTextInInputTitle(String title) {
        enterTextIntoElement(inputTitle, title);
        return this;
    }

    public CreatePostPage selectTextInDropDownRole(String textForSelect) {
        selectTextInDropDown(dropDownRole, textForSelect); // Alt+enter → Create field 'dropDownRole' in 'CreatePostPage'
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

    //    public CreatePostPage selectValueInDropDownRoleUI() {
//        selectTextInDropDownUI(dropDownRole, groupMessageValueInDropDown);
//        return this;
//    }
// ↓↑
    public CreatePostPage selectValueInDropDownRoleUI(String groupMessageValueInDropDown) {
        selectTextInDropDownUI(dropDownRole, groupMessageValueInDropDown);
        return this;
    }

    public CreatePostPage clickOnSaveNewPostButton() {
        clickOnElement(buttonSaveNewPost);
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
}
