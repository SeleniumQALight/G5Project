package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {
    @FindBy(id = "post-title")
    private WebElement inputTitle;
    @FindBy(xpath = ".//textarea[@id='post-body']")
    private WebElement inputBody;
    @FindBy(tagName = "select")
    private WebElement dropDownRole;
    @FindBy(xpath = ".//form[@action='/create-post']//button")
    private WebElement buttonSaveNewPost;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage checkIsRedirectToCreatePostPage() {
        //TODO check_URL
        Assert.assertTrue("CreatePost page does not loaded", isElementDisplayed(inputTitle));
        return this;
    }

    public CreatePostPage enterTextIntoTitleInput(String titleText) {
        enterTextIntoElement(inputTitle, titleText);
        return this;
    }

    public CreatePostPage enterTextIntoBodyInput(String bodyText) {
        enterTextIntoElement(inputBody, bodyText);
        return this;
    }

    public CreatePostPage selectTextInDropDownRole(String textForSelect) {
        selectTextInDropDown(dropDownRole, textForSelect);
        return this;
    }

    public CreatePostPage selectValueInDropDownRole(String valueForSelect) {
        selectValueInDropDown(dropDownRole, valueForSelect);
        return this;
    }

    public CreatePostPage selectTextInDropDownRoleLikeUI(String textForSelect) {
        selectTextInDropDownLikeUI(dropDownRole, textForSelect);
        return this;
    }

    public PostPage clickOnSaveNewPostButton() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }
}
