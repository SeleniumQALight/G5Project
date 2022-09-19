package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {
    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(tagName = "select")
    private WebElement dropdownRole;

    @FindBy(xpath = ".//textarea[@id='post-body']")
    private WebElement inputBody;

    @FindBy(xpath = ".//form[@action='/create-post']//button")
    private WebElement buttonSavePost;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/create-post";
    }

    public CreatePostPage checkIsRedirectToCreatePostPage() {
        checkURL();
        Assert.assertTrue("", isElementDisplayed(inputTitle));
        return this;

    }

    public CreatePostPage enterTextInInputTitle(String title) {
        enterTextIntoElement(inputTitle, title);
        return this;
    }

    public CreatePostPage selectTextInDropdownRole(String textForSelect) {
        selectValueInDropdown(dropdownRole, textForSelect);
        return this;
    }

    public CreatePostPage selectValueInDropdownRole(String valueForSelect) {
        selectValueInDropdown(dropdownRole, valueForSelect);
        return this;
    }
    public pages.CreatePostPage enterTextInInputBody(String text){
        enterTextIntoElement(inputBody, text);
        return this;
    }
    public PostPage clickOnSavePostButton(){
        clickOnElement(buttonSavePost);
        return new PostPage(webDriver);
    }

}
