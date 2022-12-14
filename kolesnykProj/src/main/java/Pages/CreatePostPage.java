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
    @FindBy(name = "body")
    private WebElement inputBody;
    @FindBy(xpath = "//button[@class='btn btn-primary']")
    private WebElement buttonSaveNewPost;

    @FindBy(xpath = "//input[@type='checkbox']")
    private WebElement checkBoxUniquePost;

    public CreatePostPage(WebDriver driver) {
        super(driver);
    }

    @Override
    String getRelativeUrl() {
        return "/create-post";
    }

    public CreatePostPage checkIsRedirectToPostPage(){
        checkUrl();
        Assert.assertTrue("Create Post page is not opened",isElementDisplayed(inputTitle));
        return this;
    }

    public CreatePostPage enterTextIntoInputTitle(String title){
        enterTextIntoElement(inputTitle,title);
        return this;
    }

    public CreatePostPage enterTextIntoInputBody(String text){
        enterTextIntoElement(inputBody, text);
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

    public CreatePostPage selectDropDown(String text){
        selectDropDownByText(dropDownRole,text);
        return this;
    }

    public PostPage clickOnButtonSaveNewPost(){
        clickOnElement(buttonSaveNewPost);
        return new PostPage(driver);
    }

    public PostPage clickOnButtonSaveUpdates(){
        clickOnElement(buttonSaveNewPost);
        return new PostPage(driver);
    }

    public CreatePostPage checkBoxSelectValue(String action){
        actionWithCheckBox(checkBoxUniquePost, action);
        return this;
    }

    public CreatePostPage editPostTitle(String newTitle){
        String title = getTitleText();
        inputTitle.clear();
        inputTitle.sendKeys(String.format(title + newTitle));
        return this;
    }

    private String getTitleText(){
        return inputTitle.getAttribute("value");
    }
}
