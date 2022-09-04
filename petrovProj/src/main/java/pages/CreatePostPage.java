package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage{
    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(tagName="select")
    private WebElement dropDownRole;

    @FindBy(xpath = ".//textarea[@id='post-body']")
    private WebElement textAreaBodyContent;

    @FindBy(xpath = ".//form[@action='/create-post']//button")
    private WebElement createPostButton;

    @FindBy(xpath = ".//option[text()='Групове повідомлення']")
    private WebElement optionGroupMessage;


    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage checkIsRedirectToCreatePage(){
        //TODO check url
        Assert.assertTrue("Page Create post is not loaded", isElementWasDisplayed(inputTitle));
        return  this;
    }

    public CreatePostPage enterTextInInputTitle(String title){
        enterTextIntoElement(inputTitle, title);
        return this;
    }


    public CreatePostPage enterTextInInputBodyContent(String bodyText){
        enterTextIntoElement(textAreaBodyContent, bodyText);
        return this;
    }


    public CreatePostPage selectTextInDropDownRole(String textForSelect) {
        selectTextInDropDown(dropDownRole, textForSelect);
        return this;
    }

    public CreatePostPage selectValueInDropDownRole(String valueForSelect) {
        selectValueInDropDown(dropDownRole, valueForSelect);
        return this;
    }

    public CreatePostPage selectOptionInDropDownByUI(){
        selectTextInDropDownByUI();
        return this;
    }

    public PostPage clickOnCreatePostButton(){
        clickOnElement(createPostButton);
        return new PostPage(webDriver);

    }

}
