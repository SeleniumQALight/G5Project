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

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage checkIsRedirectToCreatePostPage() {
        //TODO check url
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
}
