package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {

    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(tagName = "select")
    private WebElement dropDownRole;

    @FindBy(xpath = " .//*[text()='Групове повідомлення']")
    private WebElement valueInDropDown;

    @FindBy(id = "post-body")
    private WebElement inputBody;

    @FindBy(xpath = ".//button[text()='Save New Post']")
    private WebElement savePostButton;

    @FindBy(xpath = ".//*[@type='checkbox']")
    private WebElement checkboxUniquePost;


    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/create-post";
    }

    public CreatePostPage checkIsRedirectToCreatePostPage() {
        checkUrl();
        Assert.assertTrue("Page Create Post is not loaded", isElementDisplayed(inputTitle));
        return this;
    }

    public CreatePostPage enterTextInputTitle(String title) {
        enterTextIntoElement(inputTitle, title);
        return this;
    }

    public CreatePostPage enterTextInputBody(String body) {
        enterTextIntoElement(inputBody, body);
        return this;
    }

    public CreatePostPage selectTextInDropDownRole(String textForSelect) {
        selectValueInDropDown(dropDownRole, textForSelect);
        return this;
    }

    public CreatePostPage selectValueInDropDownRole(String valueToSelect) {
        selectValueInDropDown(dropDownRole, valueToSelect);
        return this;
    }

    public CreatePostPage selectValueInDropDown() {
        selectValueInDropDown(dropDownRole, valueInDropDown);
        return this;
    }

    public PostPage savePost() {
        clickOnElement(savePostButton);
        return new PostPage(webDriver);
    }

    public CreatePostPage selectValueForCheckBox(String neededValue) {
        selectCheckBoxValue(checkboxUniquePost, neededValue);
        return this;
    }


}
