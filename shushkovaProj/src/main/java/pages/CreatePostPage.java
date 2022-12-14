package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {
    @FindBy(name ="title")
    private WebElement inputTitle;
    @FindBy(tagName="select")
    private WebElement dropDownRole;
    @FindBy(xpath = ".//textarea[@id='post-body']")
    private WebElement inputBody;
    @FindBy(xpath = ".//form[@action='/create-post']//button")
    private WebElement buttonSavePost;

    @FindBy(id ="select1")
    private WebElement dropDown;

    @FindBy(xpath = ".//option[text()='Приватне повідомлення']")
    private  WebElement optionDropDown;


    public CreatePostPage(WebDriver webDriver){
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/create-post";
    }

    public CreatePostPage checkIsRedirectToCreatePostPage(){
        checkUrl();
        Assert.assertTrue("Page Create Post is not loaded",isElementDisplayed(inputTitle));
        return this;
    }

    public CreatePostPage enterTextInInputTitle(String title){
        enterTextIntoElement(inputTitle,title);
        return this;

    }

    public CreatePostPage selectTextInDropDownRole(String textForSelect) {
        selectTextInDropDown(dropDownRole,textForSelect);
        return this;
    }
    public CreatePostPage selectValueInDropDownRole(String valueForSelect) {
        selectValueInDropDown(dropDownRole,valueForSelect);
        return this;
    }

    public CreatePostPage selectDropdownElementsUI(){
        selectDropdownElementsUI(dropDown,optionDropDown);
        return this;
    }

    public CreatePostPage enterTextInInputBody(String text) {
        enterTextIntoElement(inputBody,text);
        return this;
    }

    public PostPage clickOnSavePostButton() {
        clickOnElement(buttonSavePost);
        return new PostPage(webDriver);

    }
}
