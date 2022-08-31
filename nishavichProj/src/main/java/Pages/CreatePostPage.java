package Pages;

import Pages.elements.HeaderElement;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {


    private  HeaderElement headerElement = new HeaderElement(webDriver);
    @FindBy (name = "title")
    private WebElement inputTitle;

    @FindBy(tagName= "select")
    private WebElement dropDownRole;
    @FindBy(xpath =  ".//textarea[@id='post-body']")
    private WebElement inputBody;
    @FindBy(xpath = ".//form[@action='/create-post']//button")
    private WebElement buttonSavePost;

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }
    public CreatePostPage checkIsRedirectToCreatePostPage (){
        waitChatToBeHide();
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

    public CreatePostPage enterTextInputBody(String text) {
    enterTextIntoElement(inputBody,text);
        return  this;
    }

    public PostPage clickOnSavePostButton() {
        clickOnElement(buttonSavePost);
        return new PostPage(webDriver);
    }
}
