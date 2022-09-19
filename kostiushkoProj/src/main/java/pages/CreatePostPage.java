package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {
    @FindBy(name = "title")
    private WebElement inputTitle;
    @FindBy(tagName = "select")
    private WebElement dropDownRole;
    @FindBy(id = "post-body")
    private WebElement postBody;
    @FindBy(xpath = ".//button[@class='btn btn-primary']")
    private WebElement buttonSaveNewPost;
    @FindBy(xpath = ".//* [@class = 'btn btn-primary']")
    private WebElement buttonSaveUpdatesPost;


    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelateUrl() {
        return "/create-post";
    }

    public CreatePostPage chekIsRedirectToCreatePostPage() {
        checkUrl();
        Assert.assertTrue("Page CreatePost is not loaded", isElementDisplayed(inputTitle));
        return this;
    }

    public CreatePostPage enterTextInInputTitle(String title) {
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

    public CreatePostPage selectTextInDropDownByUI(String valueForSelect) {
        selectTextInDropDownByUI(dropDownRole, valueForSelect);
        return this;
    }

    public CreatePostPage enterTextInTextAreaBodyContent(String text) {
        enterTextIntoElement(postBody, text);
        return this;
    }

    /**
     * clicking on the post button.
     *
     * @return
     */
    public PostPage clikSaveNewPostButton() {
        clicOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }

    public CreatePostPage clikSaveUpdatesPostButton() {
    clicOnElement(buttonSaveUpdatesPost);
        return this;
    }

    public CreatePostPage chekIsRedirectToOldPostPage() {
        checkUrlwithPattern();
        Assert.assertTrue("Page CreatePost is not loaded", isElementDisplayed(inputTitle));
        return this;
    }

}
