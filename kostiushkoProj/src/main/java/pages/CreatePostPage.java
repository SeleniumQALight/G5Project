package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage{
    @FindBy (name = "title")
    private WebElement inputTitle;
    @FindBy (tagName = "select")
    private WebElement dropDownRole;
    @FindBy (id = "post-body")
    private WebElement postBody;
    @FindBy (xpath = ".//button[@class='btn btn-primary']")
    private WebElement buttonSaveNewPost;




    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage chekIsRedirectToCreatePostPage(){
        //TODo check url
        Assert.assertTrue("Page CreatePost is not loaded", isElementDisplayed(inputTitle));
        return this;
    }

    public CreatePostPage enterTextInInputTitle(String title){
        enterTextIntoElement(inputTitle, title);
        return this;
    }

    public CreatePostPage selectTextInDropDownRole(String textForSelect) {
        this.selectTextInDropDown(dropDownRole, textForSelect);
        return this;
    }

    public CreatePostPage selectValueInDropDownRole(String valueForSelect) {
        selectValueInDropDownRole(dropDownRole, valueForSelect);

        return this;
    }

    public CreatePostPage selectTextInDropDownByUI (String valueForSelect) {
        selectTextInDropDownByUI(dropDownRole, valueForSelect);
       return this;
    }

    public CreatePostPage enterTextInTextAreaBodyContent(String text){
        enterTextIntoElement(postBody, text);
        return this;
    }

    /**
     * clicking on the post button.
     * For now, returns the post creation page.
     * @return
     */
        public CreatePostPage clikSaveNewPostButton () {
        clicOnElement(buttonSaveNewPost);
        return this;
    }
}
