package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {
    @FindBy(id = "post-title")
    private WebElement inputTitle;
    @FindBy(tagName = "select")
    private WebElement dropDownRole;
    @FindBy(name = "body")
    private WebElement inputBodyContent;
    @FindBy(xpath = ".//button[@class='btn btn-primary']")
    private WebElement buttonSaveNewPost;
    @FindBy(xpath = ".//input[@type='checkbox']")
    private WebElement checkBox;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage checkIsRedirectToCreatePostPage() {
        //TODO check url
        Assert.assertTrue("Page CreatePost isn't loaded", isElementDisplayed(inputTitle));
        return this;
    }

    public CreatePostPage enterTextInputTitle(String title) {
        enterTextIntoElement(inputTitle, title);
        return this;
    }

    public CreatePostPage enterTextInputBodyContent(String textBodyContent) {
        enterTextIntoElement(inputBodyContent, textBodyContent);
        return this;
    }

    public PostInfoPage clickSaveNewPost() {
        clickOnElement(buttonSaveNewPost);
        return new PostInfoPage(webDriver);
    }

    public CreatePostPage selectTextInDropDownRole(String textForSelect) {
        selectTextInDropDown(dropDownRole, textForSelect);
        return this;
    }

    public CreatePostPage selectValueInDropDownRole(String valueForSelect) {
        selectValueInDropDown(dropDownRole, valueForSelect);
        return this;
    }

    public CreatePostPage selectTextInDropDownByUI(String textForSelect) {
        selectTextInDropDownByUI(dropDownRole, textForSelect);
        return this;
    }

    public CreatePostPage putCheckBoxIntoRequiredCondition(String condition) {

        if (condition == "check") {
            if (!isCheckCheckBox(checkBox)) {
                clickOnElement(checkBox);
            }
            logger.info("checkBox is 'check'");

        } else if (condition == "uncheck") {
            if (isCheckCheckBox(checkBox)) {
                clickOnElement(checkBox);
            }
            logger.info("checkBox is 'uncheck'");

        } else {
            logger.info("checkBox hasn't condition '" + condition + "'");
        }
        return this;
    }

}
