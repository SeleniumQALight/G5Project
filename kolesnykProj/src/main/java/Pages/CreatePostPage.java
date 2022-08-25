package Pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage{

    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(name = "body")
    private WebElement inputBodyContent;

    @FindBy(tagName = "select")
    private WebElement dropDownRole;

    public CreatePostPage(WebDriver driver) {
        super(driver);
    }

    public CreatePostPage checkIsRedirectToPostPage(){
        //TODO: check URL
        Assert.assertTrue("Create Post page is not opened",isElementDisplayed(inputTitle));
        return this;
    }

    public CreatePostPage enterTextIntoInputTitle(String title){
        enterTextIntoElement(inputTitle,title);
        return this;
    }

    public CreatePostPage selectTextInDropDownRole(String textForSelect) {
        selectTextInDropDown(dropDownRole, textForSelect);
        return this;
    }

    public CreatePostPage selectValueInDropDownRole(String value) {
        selectValueInDropDown(dropDownRole, value);
        return this;
    }
}
