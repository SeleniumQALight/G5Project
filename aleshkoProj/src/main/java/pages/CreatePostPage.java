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


    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage checkIsRedirectToCreatePostPage() {
        //TODO check_URL
        Assert.assertTrue("CreatePost page does not loaded", isElementDisplayed(inputTitle));
        return this;
    }

    public CreatePostPage enterTextIntoTitleInput (String title) {
        enterTextIntoElement(inputTitle, title);
        return this;
    }

    public CreatePostPage selectTextInDropDownRole(String textForSelect) {
        selectTextInDropDown(dropDownRole, textForSelect);
        return this;
    }


    public CreatePostPage selectValueInDropDownRole(String valueForSelect) {
        selectValueInDropDown(dropDownRole,valueForSelect);
        return this;
    }
}
