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

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage checkIsRedirectToCreatePostPage() {
        //TODO checkURL
        Assert.assertTrue("Page Create Post is not loaded", isElementDisplayed(inputTitle));
        return this;
    }

    public CreatePostPage enterTextInputTitle(String title) {
        enterTextIntoElement(inputTitle, title);
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
}
