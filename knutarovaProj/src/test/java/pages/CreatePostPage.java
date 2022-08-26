package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {
    //.//*[@name='title']
    @FindBy(name = "title")
    private WebElement inputTitle;
    @FindBy(tagName = "select")
    private WebElement dropDownRole;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage checkIsRedirectToCreatePotsPage() {
        //TODO check url
        Assert.assertTrue("page CreatePost isn't loaded", isElementDisplayed(inputTitle));
        return this;
    }

    /**
     * метод, який заповнить Title
     */
    public CreatePostPage enterTextInInputTitle(String title){
        enterTextIntoElement(inputTitle,title);
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
