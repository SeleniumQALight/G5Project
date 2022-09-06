package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage{
    //  .//*[@name='title']
    @FindBy(name = "title" )
    private WebElement inputTitle;

    @FindBy(id="post-body" )
    private WebElement bodyContent;

    @FindBy(name = "select1")
    private WebElement selectValueDropDown;

    @FindBy(xpath = " .//button[text()='Save New Post']")
    private WebElement saveNewPostButton;


    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

   public CreatePostPage checkIsRedirectToCreatePostPage (){
        //TODO checkURL
        Assert.assertTrue("Page CreatePost is not loaded", isElementDisplayed(inputTitle));
        return this;
    }

    public CreatePostPage enterTextIntoTheInputTitle (String title){
        enterTextIntoElement(inputTitle, title);
        return this;
    }

    public CreatePostPage enterTextIntoTheInputBodyContent (String content){
        enterTextIntoElement(bodyContent, content);
        return this;
    }

    public CreatePostPage selectTextInDropDownRole(String textForSelect) {
        selectTextInDropDown(selectValueDropDown, textForSelect);
        return this;
    }

    public CreatePostPage selectValueInDropDownRole(String valueForSelect) {
        selectValueInDropDown(selectValueDropDown, valueForSelect);
        return this;
    }

    public CreatePostPage selectTextInDropDownRoleByUI (String textForSelect){
        selectFoundedTextInDropDown(selectValueDropDown, textForSelect);
        return this;
    }

    public PostPage clickOnTheSaveNewPostButton (){
        clickOnElement(saveNewPostButton);
        return new PostPage(webDriver);
    }


    @FindBy(xpath = ".//input[@type=\"checkbox\"]")
    private WebElement checkboxUniquePost;


    public CreatePostPage checkboxIsPostUniqueState(String state){
        checkboxState(checkboxUniquePost,state);
        return this;
    }




}
