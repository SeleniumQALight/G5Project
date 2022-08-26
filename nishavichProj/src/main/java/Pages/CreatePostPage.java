package Pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {
    @FindBy (name = "title")
    private WebElement inputTitle;

    @FindBy(tagName= "select")
    private WebElement dropDownRole;



    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }
    public CreatePostPage checkIsRedirectToCreatePostPage (){
        Assert.assertTrue("", isElementDisplayed (inputTitle));
        return this;
    }
    public CreatePostPage enterTextInputTitle (String title) {
        enterTextIntoElement(inputTitle, title);
        return this;
    }

    public CreatePostPage selectTextInFropdown(String textForSelect) {
        return this;
    }
    public CreatePostPage selectTextInDropDownRole(String textForSelect) {
selectTextInDropDown(dropDownRole,textForSelect);
        return this;
    }

    public CreatePostPage selectValueInDropdownRole(String calueForSelect) {
        selectValueInDropDown (dropDownRole, calueForSelect);
        return this;
    }
}
