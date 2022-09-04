package Pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {
    //xpath=.//*[@name='title']
    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(tagName = "select")
    private WebElement dropDownRole;
    @FindBy(xpath = ".//textarea[@id='post-body']")
    private WebElement inputBody;
    @FindBy(xpath = ".//form[@action='/create-post']//button")
    private WebElement buttonSavedPost;

    @FindBy(xpath = ".//option[text()='Групове повідомлення']")
    private WebElement groupMessage;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    //метод для перевірки чи сторінка відкрилась
    public CreatePostPage checkIsRedirectToCreatePostPage() {
        //TODO checkURL
        Assert.assertTrue("Page CreatePost is not loaded", isElementDisplayed(inputTitle));
        return this;
    }

    public CreatePostPage enterTextInInputTitle(String title) {
        enterTextIntoElement(inputTitle, title);
        return this;
    }

    // dropDownRole - це закритий дропдаун
    public CreatePostPage selectTextInDropDownRole(String textForSelect) {
        selectTextInDropDown(dropDownRole, textForSelect);
        return this;
    }

     public CreatePostPage selectValueInDropDownRole(String valueForSelect) {
        selectValueInDropDown(dropDownRole, valueForSelect);
        return this;

    }

    public CreatePostPage selectValueInDropDownUI (){
        selectTextInDropDownUI(dropDownRole, groupMessage);
        return this;
    }



    public CreatePostPage enterTextInInputBody(String text) {
        enterTextIntoElement(inputBody, text);
        return this;
    }

    public PostPage clickOnSavePostButton() {
        clickOnElement(buttonSavedPost);
        return new PostPage(webDriver);
    }
}